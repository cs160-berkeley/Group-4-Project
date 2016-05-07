package ea.scratchthathabit;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

// Created by Tiffanie Lo on 4/19/2016: Functionality: Click brings user back to ListsActivity
public class EditListsActivity extends Activity {

    private String activity = "EditList";
    private LinkedHashMap<String, ItemList> lists;
    private LinkedHashMap<String, ReminderClass> reminders;
    private EditText enterListName;
    private EditText enterItem;
    private LinearLayout listItems;
    private ArrayList<String> items;
    private ItemList itemList;
    private ImageButton addItemBtn;
    private ImageButton attachListBtn;
    private ImageButton saveBtn;
    private ImageButton closeBtn;
    private String mode;
    private int requestCode = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        mode = intent.getStringExtra("mode");

        MyApp myApp = (MyApp) getApplicationContext();
        lists = myApp.getLists();
        reminders = myApp.getReminders();
        if (lists == null) {
            lists = new LinkedHashMap<>();
            myApp.setLists(lists);
        }
        if (reminders == null) {
            reminders = new LinkedHashMap<>();
            myApp.setReminders(reminders);
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

        attachListBtn = (ImageButton) findViewById(R.id.attach_reminder_button);
        attachListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RemindersActivity.class);
                intent.putExtra("mode", "attach");
                startActivityForResult(intent, requestCode);
            }
        });

        itemList = new ItemList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.requestCode == requestCode) {
            if (resultCode == RESULT_OK) {
                String reminderName = data.getStringExtra("remindername");
                Log.d(activity, "reminder " + reminderName + " attached");
                ReminderClass reminderClass = reminders.get(reminderName);
                itemList.setReminder(reminderClass);

//                change ui to indicate attached reminder

            }
        }
    }

    public void populate() {
        enterListName.setText(itemList.getName());
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
        Intent intent = new Intent();
        if (name.equals("")) {
            return;
        }
        itemList.setName(name);
        itemList.setList(items);
        lists.put(name, itemList);
        intent.putExtra("listname", name);
        intent.putExtra("mode", mode);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onClose() {
        finish();
    }

}
