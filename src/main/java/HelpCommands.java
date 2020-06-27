import java.time.LocalDate;

/**
 * Класс, хранящий дополнительные команды
 * @author Dima Tolochek
 * @version 1.0 Before Check
 */
public class HelpCommands {
    /**
     * Устанавливает текущее время
     * @param in - строка для парсинга
     * @return объект "localDate"
     */
    public static LocalDate ParseDate(String in){
        LocalDate ld = LocalDate.now();
        if(!in.equals("")){
            ld = LocalDate.parse(in);
        }
        return ld;
    }
}
