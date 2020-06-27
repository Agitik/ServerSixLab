/**
 * Класс - буффер между XML представлением билета и Java объектом
 * @author Дмитрий Толочек P3130
 * @version 1.0 Before Check
 */

public class Buffer {
    static String id;                      //ticket
    static String name;                    //ticket
    static String CordX;                   //coordinates
    static String CordY;                   //coordinates
    static String creationDate;            //ticket
    static String price;                   //ticket
    static String refundable;              //ticket
    static String type;                    //ticket
    static String weight;                  //person
    static String eyeColor;                //person
    static String hairColor;               //person
    static String nationality;             //person
    static String LocX;                    //location
    static String LocY;                    //location
    static String LocName;                 //location
    static String initDate = "";
    static String person = "";
    static String location = "";
    static String makingDay = "";

    /**
     * Отчистка буфера
     */
    static void BufferClear (){
        Buffer.id = null;                      //ticket
        Buffer.name = null;                    //ticket
        Buffer.CordX = null;                   //coordinates
        Buffer.CordY = null;                   //coordinates
        Buffer.creationDate = null;            //ticket
        Buffer.price = null;                   //ticket
        Buffer.refundable = null;              //ticket
        Buffer.type = null;                    //ticket
        Buffer.weight = null;                  //person
        Buffer.eyeColor = null;                //person
        Buffer.hairColor = null;               //person
        Buffer.nationality = null;             //person
        Buffer.LocX = null;                    //location
        Buffer.LocY = null;                    //location
        Buffer.LocName = null;                 //location
        Buffer.initDate = null;
        Buffer.person = "";
        Buffer.location = "";
    }
}