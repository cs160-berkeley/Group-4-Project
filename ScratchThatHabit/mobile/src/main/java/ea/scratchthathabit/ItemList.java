package ea.scratchthathabit;

import java.util.ArrayList;

/**
 * Created by Sarah on 4/25/2016.
 */
public class ItemList {

    private String name;
    private ArrayList<String> list;

    public ItemList(String name, ArrayList<String> list) {
        this.name = name;
        this.list = list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public String getName() {
        return name;
    }
}
