import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Server {
    public static boolean ExitCode = false;
    public static void server(int PORT) throws IOException, ClassNotFoundException {
        Logger logger = LoggerFactory.getLogger(Server.class);
        logger.info("Сервер запущен!");
        Selector selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", PORT));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
        Scanner scr = new Scanner(System.in);
        while (!ExitCode){
            if (System.in.available() > 0) {
                if (scr.next().equals("save")){
                    logger.info("Коллекция сохранена через сервер!");
                    ServerCommands.save(TicketCollection.tickets, CommandExecuter.filePath);
                }
            }
            selector.select(2000);
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext() & !ExitCode) {
                SelectionKey key = iter.next();
                int interestOps = key.interestOps();
                if (key.isAcceptable() & !ExitCode) {
                    logger.info("Клиент подключился к серверу!");
                    SocketChannel client = serverSocket.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable() & !ExitCode) {
                    SocketChannel client = (SocketChannel) key.channel();
                    client.read(buffer);

                    //Обработка вопроса

                    ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    QuestionPack qp = (QuestionPack) ois.readObject();
                    logger.info("Вопрос получен!");
                    AnswerPack ap = UnPacker.UnPackAndExecute(qp);
                    logger.info("Ответ подготовлен");

                    //Подготовка ответа
                    logger.info("Сервер начинает отправку ответ!");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(ap);
                    logger.info("Ответ отправлен!");

                    byte[] data = baos.toByteArray();

                    client.write(ByteBuffer.wrap(data));
                    buffer.clear();
                    key.cancel();
                }
                iter.remove();
            }
        }
    }
}
