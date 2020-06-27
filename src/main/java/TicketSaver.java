import java.util.HashMap;

/**
 * Класс для сохранения коллекции билетов в XML виде.
 * @author Dima Tolochek
 * @version 1.0 Before Check
 */

public class TicketSaver {

    /**
     * Реализация команды "save"
     * @param tc - коллекция билетов
     * @param f - файл, куда будет идти сохранение
     */
    public static void save(HashMap<Long, Ticket> tc, String f){
        ParserWriter.cleanFile(f);
        ParserWriter.addParseStrToFile(f, ParserWriter.addZeroLvlStartElement("tickets"));
        tc.values().forEach(ticket -> TicketSaver.saveTicket(ticket, f));
        ParserWriter.addParseStrToFile(f, ParserWriter.addZeroLvlEndElement("tickets"));
    }

    /**
     * Сохранение 1 билета
     */
    public static void saveTicket(Ticket t, String f){
        ParserWriter.addParseStrToFile(f, ParserWriter.addStartElement("ticket",0));
        TicketSaver.saveTicketID(t, f);
        TicketSaver.saveTicketName(t, f);
        TicketSaver.saveTicketCordX(t, f);
        TicketSaver.saveTicketCordY(t, f);
        TicketSaver.saveTicketCreationTime(t, f);
        TicketSaver.saveTicketPrice(t, f);
        TicketSaver.saveTicketRefundable(t, f);
        TicketSaver.saveTicketType(t, f);
        TicketSaver.savePerson(t, f);
        ParserWriter.addParseStrToFile(f, ParserWriter.addEndElement("ticket",0));
    }

    /**
     * Сохранение 1 person
     * @param t - билет
     * @param f - файл
     */
    public static void savePerson(Ticket t, String f){
        if(t.getPerson() == null){
            ParserWriter.addParseStrToFile(f, ParserWriter.addElementWithValue("person", "null", 1));
        } else {
            ParserWriter.addParseStrToFile(f, ParserWriter.addStartElement("person",1));
            TicketSaver.savePersonWeight(t.getPerson(),f);
            TicketSaver.savePersonEyeColor(t.getPerson(),f);
            TicketSaver.savePersonHairColor(t.getPerson(),f);
            TicketSaver.savePersonNationality(t.getPerson(),f);
            TicketSaver.savePersonLocation(t.getPerson(),f);
            ParserWriter.addParseStrToFile(f, ParserWriter.addEndElement("person",1));
        }
    }

    public static void savePersonLocation(Person p, String f){
        if(p.getLocation() == null){
            ParserWriter.addParseStrToFile(f, ParserWriter.addElementWithValue("location", "null",2));
        } else {
            TicketSaver.saveLocX(p.getLocation(),f);
            TicketSaver.saveLocY(p.getLocation(),f);
            TicketSaver.saveLocName(p.getLocation(),f);
        }
    }

    public static void saveTicketID(Ticket t, String f){
        ParserWriter.addParseStrToFile(f, ParserWriter.addElementWithValue("id",t.getId().toString(),1));
    }

    public static void saveTicketName(Ticket t, String f){
        ParserWriter.addParseStrToFile(f, ParserWriter.addElementWithValue("name",t.getName().toString(),1));
    }

    public static void saveTicketCordX(Ticket t, String f){
        ParserWriter.addParseStrToFile(f,
                ParserWriter.addElementWithValue("cordX", Integer.toString(t.getCoordinates().getX()), 1));
    }

    public static void saveTicketCordY(Ticket t, String f){
        ParserWriter.addParseStrToFile(f,
                ParserWriter.addElementWithValue("cordY", Integer.toString(t.getCoordinates().getY()), 1));
    }

    public static void saveTicketCreationTime(Ticket t, String f){
        ParserWriter.addParseStrToFile(f, ParserWriter.addElementWithValue("creationDate",
                t.getCreationDate().toString(),1));
    }

    public static void saveTicketPrice(Ticket t, String f){
        ParserWriter.addParseStrToFile(f, ParserWriter.addElementWithValue("price",
                Float.toString(t.getPrice()),1));
    }

    public static void saveTicketRefundable(Ticket t, String f){
        String ref = t.getRefundable() == null ? "null" : Boolean.toString(t.getRefundable());
        ParserWriter.addParseStrToFile(f, ParserWriter.addElementWithValue("refundable", ref, 1));
    }

    public static void saveTicketType(Ticket t, String f){
        ParserWriter.addParseStrToFile(f, ParserWriter.addElementWithValue("type",
                t.getType().toString(), 1));
    }

    //PERSON
    public static void savePersonWeight(Person p, String f){
        String w = p.getWeight() == null ? "null" : Double.toString(p.getWeight());
        ParserWriter.addParseStrToFile(f, ParserWriter.addElementWithValue("weight", w, 2));
    }

    public static void savePersonEyeColor(Person p, String f){
        ParserWriter.addParseStrToFile(f, ParserWriter.addElementWithValue("eyeColor",
                p.getEyeColor().toString(),2));
    }

    public static void savePersonHairColor(Person p, String f){
        String ref = p.getHairColor() == null ? "null" : p.getHairColor().toString();
        ParserWriter.addParseStrToFile(f, ParserWriter.addElementWithValue("hairColor",
                ref,2));
    }

    public static void savePersonNationality(Person p, String f){
        String ref = p.getNationality() == null ? "null" : p.getNationality().toString();
        ParserWriter.addParseStrToFile(f, ParserWriter.addElementWithValue("nationality",
                ref,2));
    }

    //LOCATION  locX
    public static void saveLocX(Location l, String f){
        ParserWriter.addParseStrToFile(f, ParserWriter.addElementWithValue("locX",
                l.getX().toString(),3));
    }

    public static void saveLocY(Location l, String f){
        ParserWriter.addParseStrToFile(f, ParserWriter.addElementWithValue("locY",
               Float.toString(l.getY()),3));
    }

    public static void saveLocName(Location l, String f){
        ParserWriter.addParseStrToFile(f, ParserWriter.addElementWithValue("locName",
                l.getName(), 3));
    }
}
