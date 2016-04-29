package ea.scratchthathabit;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Sarah on 4/26/2016.
 */
public class MyApp extends Application {

    private LinkedHashMap<String, ItemList> lists;


    public LinkedHashMap<String, ItemList> getLists() {
        return lists;
    }

    public void setLists(LinkedHashMap<String, ItemList> lists) {
        this.lists = lists;
    }
}
