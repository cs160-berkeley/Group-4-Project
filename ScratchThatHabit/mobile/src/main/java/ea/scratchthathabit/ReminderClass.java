package ea.scratchthathabit;

import java.util.ArrayList;

/**
 * Created by tiffanielo on 4/26/16.
 */
public class ReminderClass {
    String RName;
    ArrayList<String> RDays = new ArrayList<String>();
    String type;
    int RHour;
    int RMinute;
    boolean weekly;
    boolean sound;
    boolean vibration;
    boolean arriving;
    boolean leaving;
    ItemList itemList;
    String address;

    public ReminderClass() {
    }

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

    public void setItemList(ItemList itemList) {
        this.itemList = itemList;
    }

    public ItemList getItemList() {
        return itemList;
    }

    public boolean hasItemList() {
        return itemList != null;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setArriving(boolean arriving) {
        this.arriving = arriving;
    }

    public void setLeaving(boolean leaving) {
        this.leaving = leaving;
    }

    public boolean isArriving() {
        return arriving;
    }

    public boolean isLeaving() {
        return leaving;
    }

    public boolean isSound() {
        return sound;
    }

    public boolean isVibration() {
        return vibration;
    }

    public void setWeekly(boolean weekly) {
        this.weekly = weekly;
    }

    public boolean isWeekly() {
        return weekly;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
