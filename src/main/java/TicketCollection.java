/**
 * Класс для хранения самой коллекции
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 */

import java.time.LocalDate;
import java.util.HashMap;

public class TicketCollection {
    static HashMap<Long, Ticket> tickets = new HashMap<>();

    LocalDate makingDay = Buffer.makingDay.equals("") ? LocalDate.now() : LocalDate.parse(Buffer.makingDay);
}
