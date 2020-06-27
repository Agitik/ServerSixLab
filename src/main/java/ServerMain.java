import java.io.IOException;

public class ServerMain {
    public static int PORT = 9000;
    public static void main(String[] args) {
        try {
            if(args[0] != null){
                CommandExecuter.filePath = args[0];
            }
            PORT = Integer.parseInt(args[1]);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Файл не найден. За основу взят внуренний системный файл");
        } catch (NumberFormatException e){
            System.out.println("Порт не обнаружен. Взят 8080 как базовый.");
        }
        TicketParserReader.Read(CommandExecuter.filePath);
        start();
    }

    public static void start(){
        try {
            Server.server(PORT);
        } catch (IOException e) {
            System.out.println("Ошибка на сервере");
            System.out.println("Пробую использовать другой порт");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException interruptedException) {
                System.out.println("Не получилось подождать");
            }
            PORT--;
            System.out.println("Пробую порт: " + PORT);
            start();
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка чтения запроса");
        }
    }
}
