/**
 * Класс, создающий объект Person с помощью буфера
 * @author Dima Tolochek
 * @version 1.0 Before Check
 */

public class PersonMaker {

    /**
     * Создание объекта Person
     * @return - person
     */
    public static Person MakePersonFromBuffer(){
        if(Buffer.person.equals("null")){
            return null;
        } else {
            Person p = new Person();
            PersonMaker.MakeWeightFromBuffer(p);
            PersonMaker.MakeEyeColorFromBuffer(p);
            PersonMaker.MakeHairColorFromBuffer(p);
            PersonMaker.MakeNationalityFromBuffer(p);
            PersonMaker.MakeLocationFromBuffer(p);
            return p;
        }
    }

    public static void MakeWeightFromBuffer(Person p){
        String w = Buffer.weight;
        p.setWeight(w.equals("") ? null : Double.parseDouble(w));
    }

    public static void MakeEyeColorFromBuffer(Person p){
        p.setEyeColor(Color.getType(Buffer.eyeColor));
    }

    public static void MakeHairColorFromBuffer(Person p){
        p.setHairColor(Color.getType(Buffer.hairColor));
    }

    public static void MakeNationalityFromBuffer(Person p){
        p.setNationality(Country.getType(Buffer.nationality));
    }

    /**
     * Создание объекта Location
     * @param p - person
     */
    public static void MakeLocationFromBuffer(Person p){
        if(Buffer.location.equals("null")){
            p.setLocation(null);
        } else {
            Location loc = new Location();
            loc.setName(Buffer.LocName);
            loc.setX(Long.parseLong(Buffer.LocX));
            loc.setY(Float.parseFloat(Buffer.LocY));
            p.setLocation(loc);
        }
    }
}
