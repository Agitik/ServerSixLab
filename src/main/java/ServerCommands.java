import java.util.HashMap;

public class ServerCommands {
    public static void save(HashMap <Long, Ticket> tc, String path){
        TicketSaver.save(tc, path);
        System.out.println("Команда выполнена! Коллекция сохранена");
    }

    public static void exit(){
        System.out.println("Инициирован выход из сервера");
        Server.ExitCode = true;
    }
}
