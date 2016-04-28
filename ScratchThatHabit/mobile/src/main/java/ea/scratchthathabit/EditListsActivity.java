package ea.scratchthathabit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

// Created by Tiffanie Lo on 4/19/2016: Functionality: Click brings user back to ListsActivity
public class EditListsActivity extends AppCompatActivity {

    private LinkedHashMap<String, ItemList> lists;
    private EditText enterListName;
    private EditText enterItem;
    private LinearLayout listItems;
    private ArrayList<String> items;
    private ItemList itemList;
    private ImageButton addItemBtn;
    private ImageButton saveBtn;
    private ImageButton closeBtn;
    private String mode;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        mode = intent.getStringExtra("mode");

        MyApp myApp = (MyApp) getApplicationContext();
        lists = myApp.getLists();
        if (lists == null) {
            lists = new LinkedHashMap<>();
            myApp.setLists(lists);
        }

        setContentView(R.layout.activity_edit_lists);

        enterListName = (EditText) findViewById(R.id.enter_list_name);
        enterItem = (EditText) findViewById(R.id.enter_item);
        listItems = (LinearLayout) findViewById(R.id.list_items);
        addItemBtn = (ImageButton) findViewById(R.id.add_item_btn);
        saveBtn = (ImageButton) findViewById(R.id.btn_save);
        closeBtn = (ImageButton) findViewById(R.id.btn_close);
        if (mode != null && mode.equals("create")) {
            items = new ArrayList<>();
        } else if (mode != null && mode.equals("edit")) {
            String listName = intent.getStringExtra("listname");
            itemList = lists.get(listName);
            items = itemList.getList();
            populate();
        }
        items = new ArrayList<>();
        super.onCreate(savedInstanceState);

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSave();
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClose();
            }
        });
    }

    public void populate() {
        for (String s : items) {
            addItemView(s);
        }
    }


    public void addItem() {
        String item = enterItem.getText().toString();
        if (!item.equals("")) {
            items.add(item);
            addItemView(item);
        }
    }

    public void addItemView(String item) {
        ListItemView itemView = new ListItemView(this);
        itemView.setText(item);
        itemView.setList(items);
        listItems.addView(itemView);

    }

    public void onSave() {
        String name = enterListName.getText().toString();
        if (name.equals("")) {
            return;
        }
        if (mode.equals("create")) {
            itemList = new ItemList(name, items);
            lists.put(name, itemList);
        } else {
            String oldName = itemList.getName();
            if (!oldName.equals(name)) {
                lists.remove(oldName);
                itemList.setName(name);
                lists.put(name, itemList);
            }
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse(name));
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onClose() {
        finish();
    }

}
