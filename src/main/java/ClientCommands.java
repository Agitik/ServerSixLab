import java.io.Serializable;

/**
 * Перечисление команд, доступных клиенту для отправки на сервер.
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 */
public enum ClientCommands implements Serializable {
    HELP,
    INFO,
    SHOW,
    INSERT_NULL, //ready
    UPDATE_ID, //ready
    REMOVE_KEY, //ready
    CLEAR,  //ready
    EXECUTE_SCRIPT,
    EXIT,   //ready
    REPLACE_IF_GREATER, //ready
    REMOVE_GREATER_KEY, //ready
    REMOVE_LOWER_KEY, //ready
    COUNT_LESS_THAN_TYPE, //ready
    PRINT_FIELD_ASCENDING_PERSON, //ready
    PRINT_FIELD_DESCENDING_TYPE; //ready

    /**
     * Массив хранит названия команд, не нуждающихся в аргументе.
     */
    public static String[] nonArgs = {"help", "info", "show", "clear", "exit",
            "print_field_ascending_person", "print_field_descending_type"};
    private static final long serialVersionUID = 0;
}