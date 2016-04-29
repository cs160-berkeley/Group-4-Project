package ea.scratchthathabit;

import java.util.ArrayList;

/**
 * Created by tiffanielo on 4/26/16.
 */
public class ReminderClass {
    String RName;
    ArrayList<String> RDays = new ArrayList<String>();
    int RHour;
    int RMinute;

    public ReminderClass(String RName){
        // This constructor has one parameter, name.
        System.out.println("Passed Name is :" + RName );
        this.RName = RName;
    }

    public void setRName(String name) {
        RName = name;
    }

    public String getRName() {
        System.out.println("Reminder name :" + RName);
        return RName;
    }

    public void addRDay(String day) {
        RDays.add(day);
        System.out.println("CurrentListOfDays:" + "\n");
        for (int i = 0; i <= RDays.size()-1; i++) {
            System.out.println(RDays.get(i));
        }
    }

    public void removeRDay(String day) {
        RDays.remove(day);
        System.out.println("CurrentListOfDays:" + "\n");
        for (int i = 0; i <= RDays.size()-1; i++) {
            System.out.println(RDays.get(i));
        }
    }

    public ArrayList<String> getRDays() {
        for (int i = 0; i <= RDays.size()-1; i++) {
            System.out.println("Reminder set to go off at days :" + RDays.get(i));
        }
        return RDays;
    }

    public void setRHour( int hour ){
        RHour = hour;
    }

    public int getRHour( ){
        System.out.println("Reminder set to go off at hour :" + RHour );
        return RHour;
    }

    public void setRMinute( int minute ){
        RMinute = minute;
    }

    public int getAge( ){
        System.out.println("Reminder set to go off at minute :" + RMinute );
        return RMinute;
    }

}
