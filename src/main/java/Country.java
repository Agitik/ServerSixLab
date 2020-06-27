import java.io.Serializable;

/**
 * Перечисление стран, граждане которых могут купить билет.
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 * @see Person
 */

public enum Country implements Serializable {
    USA,
    GERMANY,
    SOUTH_KOREA;

    /**
     * Получение страны из строкового представления
     * @param c - строковое представление строки
     * @return "country" объект
     */
    public static Country getType(String c){
        if ("USA".equals(c.toUpperCase().trim())) {
            return USA;
        } else if ("GERMANY".equals(c.toUpperCase().trim())) {
            return GERMANY;
        } else if ("SOUTH_KOREA".equals(c.toUpperCase().trim())) {
            return SOUTH_KOREA;
        }
        return null;
    }

    private static final long serialVersionUID = 0;
}