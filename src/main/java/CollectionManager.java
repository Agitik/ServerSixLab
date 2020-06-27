
/**
 * Класс - менеджер коллекции.
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 */

public class CollectionManager {
    /**
     * Добавить билет в коллекцию из буфера
     */
    public static void addTicketFromBuffer(){
        TicketCollection.tickets.put(Long.parseLong(Buffer.id)-1, TicketMaker.MakeTicketFromBuffer());
    }
}
