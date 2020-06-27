import java.time.LocalDate;

/**
 * Класс, создающий билет из буфера
 * @author Dima Tolocheck
 * @version 1.0 Before Check
 */

public class TicketMaker {
    /**
     * Создать билет из буффера
     * @return - объект Ticket
     */
    public static Ticket MakeTicketFromBuffer(){
        Ticket nt = new Ticket(Long.parseLong(Buffer.id));
        TicketMaker.MakeNameFromBuffer(nt);
        TicketMaker.MakeCoordinatesFromBuffer(nt);
        TicketMaker.MakeCreationDateFromBuffer(nt);
        TicketMaker.MakePriceFromBuffer(nt);
        TicketMaker.MakeRefundableFromBuffer(nt);
        TicketMaker.MakeTicketTypeFromBuffer(nt);
        if(!Buffer.person.equals("null")){
            nt.setPerson(PersonMaker.MakePersonFromBuffer());
        }
        return nt;
    }


    public static void MakeNameFromBuffer(Ticket t){
        t.setName(Buffer.name);
    }

    public static void MakeCoordinatesFromBuffer(Ticket t){
        Coordinates cor = new Coordinates();
        cor.setX(Integer.parseInt(Buffer.CordX));
        cor.setY(Integer.parseInt(Buffer.CordY));
        t.setCoordinates(cor);
    }

    public static void MakeCreationDateFromBuffer(Ticket t){
       LocalDate lc = LocalDate.parse(Buffer.creationDate);
       t.setCreationDate(lc);
    }

    public static void MakePriceFromBuffer(Ticket t){
        t.setPrice(Float.parseFloat(Buffer.price));
    }

    public static void MakeRefundableFromBuffer(Ticket t){
        String ref = Buffer.refundable;
        t.setRefundable(ref.equals("") ? null : Boolean.parseBoolean(ref));
    }

    public static void MakeTicketTypeFromBuffer(Ticket t){
        t.setType(TicketType.getType(Buffer.type));
    }


}
