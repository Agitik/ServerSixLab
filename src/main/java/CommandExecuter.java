import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс, содержащий методы - реализации всех команд
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 */

public class CommandExecuter {
    /**
     * Файл по умолчанию
     */
    public static String filePath = "target\\classes\\sFile.xml";

    /**
     * Реализация команды "info"
     * @return ответ от сервера
     */
    public static AnswerPack info(){
        AnswerPack ap = new AnswerPack();
        ap.answer.add("Тип коллекции: HashTable");
        ap.answer.add("Тип хранимых объектов: Tickets");
        ap.answer.add("Колличество элементов: " + TicketCollection.tickets.size());
        ap.answer.add("Дата инициализации коллекции: " + HelpCommands.ParseDate(Buffer.makingDay).toString());
        return ap;
    }

    /**
     * Реализация команды "show"
     * @return ответ от сервера
     */
    public static AnswerPack show(){
        AnswerPack ap = new AnswerPack();
        Comparator<Ticket> AlpComp = ((o2, o1) -> {
            ArrayList<String> buf = new ArrayList<>();
            int ret;
            buf.add(o1.getName());
            buf.add(o2.getName());
            Collections.sort(buf);
            if (buf.get(0).equals(o1.getName())) {
                ret = 1;
            } else if (buf.get(0).equals(buf.get(1))) {
                ret = 0;
            } else {
                ret = -1;
            }
            return ret;
        });
        TicketCollection.tickets.values().stream().sorted(AlpComp).forEach(ticket -> ap.answer.add(ticket.showInfo()));
        return ap;
    }

    /**
     * Реализация команды "clear"
     * @return ответ от сервера
     */
    public static AnswerPack clear(){
        AnswerPack ap = new AnswerPack();
        TicketCollection.tickets.clear();
        ap.answer.add("Все объекты коллекции удалены.");
        return ap;
    }

    /**
     * Реализация команды "insert_null"
     * @return ответ от сервера
     */
    public static AnswerPack insert_null(QuestionPack pack){
        AnswerPack ap = new AnswerPack();
        boolean notConsist = TicketCollection.tickets.keySet().stream()
                .noneMatch(x -> x.equals(pack.arg.key));
        if(!notConsist){
            ap.answer.add("Ошибка: Билет с таким key уже существует!");
        } else {
            Ticket nt = pack.arg.ticket;
            nt.setId((long) pack.arg.key + 1);
            nt.setDefaultCreationDate();
            TicketCollection.tickets.put(pack.arg.key, nt);
            ap.answer.add("Элемент добавлен в коллекцию!");
        }
        return ap;
    }

    /**
     * Реализация команды "update_id"
     * @return ответ от сервера
     */
    public static AnswerPack update_id(QuestionPack pack){
        AnswerPack ap = new AnswerPack();
        boolean isIDreal = TicketCollection.tickets.values().stream()
                .anyMatch(ticket -> ticket.getId().equals(pack.arg.id));
        if(isIDreal){
            Ticket t = pack.arg.ticket;
            t.setId(pack.arg.id);
            t.setDefaultCreationDate();
            TicketCollection.tickets.replace(pack.arg.id - 1, t);
            ap.answer.add("Билет успешно изменен!");
        } else {
            ap.answer.add("Билета с таким ID не существует!");
            ap.answer.add("Используйте Show для просмотра всех элементов коллекции");
        }
        return ap;
    }

    /**
     * Реализация команды "remove_key"
     * @return ответ от сервера
     */
    public static AnswerPack remove_key(QuestionPack pack){
        AnswerPack ap = new AnswerPack();
        Map<Long, Ticket> res = TicketCollection.tickets.values().stream()
                .filter(ticket -> ticket.getId()-1 != pack.arg.key)
                .collect(Collectors.toMap(l -> l.getId()-1, t -> t));
        TicketCollection.tickets =  new HashMap<Long, Ticket>(res);
        ap.answer.add("Команда выполнена!");
        return ap;
    }

    /**
     * Реализация команды "replace_if_greater"
     * @return ответ от сервера
     */
    public static AnswerPack replace_if_greater(QuestionPack pack){
        AnswerPack ap = new AnswerPack();
        Ticket rep = TicketCollection.tickets.get(pack.arg.key);
        Map<Long, Ticket> res = TicketCollection.tickets.values().stream()
                .filter(ticket -> ticket.compareTo(rep) > 0)
                .collect(Collectors.toMap(l -> l.getId()-1, t -> t));
        TicketCollection.tickets =  new HashMap<Long, Ticket>(res);
        ap.answer.add("\nКоманда выполнена успешно!");
        return ap;
    }

    /**
     * Реализация команды "remove_greater_keys"
     * @return ответ от сервера
     */
    public static AnswerPack remove_greater_keys(QuestionPack pack){
        AnswerPack ap = new AnswerPack();
        Map<Long, Ticket> res = TicketCollection.tickets.values().stream()
                .filter(ticket -> ticket.getId()-1 <= pack.arg.key)
                .collect(Collectors.toMap(l -> l.getId()-1, t -> t));
        TicketCollection.tickets =  new HashMap<Long, Ticket>(res);
        ap.answer.add("\nКоманда выполнена!");
        return ap;
    }

    /**
     * Реализация команды "remove_lower_keys"
     * @return ответ от сервера
     */
    public static AnswerPack remove_lower_keys(QuestionPack pack){
        AnswerPack ap = new AnswerPack();
        Map<Long, Ticket> res = TicketCollection.tickets.values().stream()
                .filter(ticket -> ticket.getId()-1 >= pack.arg.key)
                .collect(Collectors.toMap(l -> l.getId()-1, t -> t));
        TicketCollection.tickets =  new HashMap<Long, Ticket>(res);
        ap.answer.add("\nКоманда выполнена!");
        return ap;
    }

    /**
     * Реализация команды "count_less_then_type"
     * @return ответ от сервера
     */
    public static AnswerPack count_less_then_type(QuestionPack pack){
        AnswerPack ap = new AnswerPack();
        int vt = pack.arg.type.ordinal();
        long rez = TicketCollection.tickets.values().stream()
                .filter(ticket -> ticket.getType().ordinal() < vt)
                .count();
        ap.answer.add("\nResult: " + rez);
        return ap;
    }

    /**
     * Реализация команды "print_field_ascending_person"
     * @return ответ от сервера
     */
    public static AnswerPack print_field_ascending_person(){
        AnswerPack ap = new AnswerPack();
        List<Person> realPeople = new ArrayList<>();
        TicketCollection.tickets.values().stream()
                .filter(ticket -> ticket.getPerson() != null)
                .forEach(ticket -> realPeople.add(ticket.getPerson()));
        realPeople.stream().sorted().forEach(person -> ap.answer.add("\n"+person.showInfo()+"\n"));
        return ap;
    }

    /**
     * Реализация команды "print_field_descending_type"
     * @return ответ от сервера
     */
    public static AnswerPack print_field_descending_type(){
        AnswerPack ap = new AnswerPack();
        Comparator<Ticket> comparator = (o1, o2) -> o2.getType().ordinal() - o1.getType().ordinal();
        List<Ticket> ticketTypeSorted = TicketCollection.tickets.values().stream()
                .sorted(comparator).collect(Collectors.toList());
        for (Ticket t : ticketTypeSorted){
            ap.answer.add(t.getType().toString());
        }
        return ap;
    }

    /**
     * Реализация команды "execute_script"
     * @return ответ от сервера
     */
    public static AnswerPack execute_script(QuestionPack pack){
        AnswerPack ap = new AnswerPack();
        for (QuestionPack p : pack.arg.script) {
            AnswerPack asp = UnPacker.UnPackAndExecute(p);
            ap.answer.addAll(asp.answer);
        }
        return ap;
    }

    /**
     * Реализация команды "client_exit"
     * @return ответ от сервера
     */
    public static AnswerPack client_exit(){
        AnswerPack ap = new AnswerPack();
        ap.answer.add("Инициирован выход клиента. Коллекция сохранена");
        TicketSaver.save(TicketCollection.tickets, filePath);
        return ap;
    }
}
