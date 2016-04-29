package ea.scratchthathabit;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import java.util.LinkedHashMap;

//Created by Tiffanie Lo 4/19/16: Functionality: Click brings user to EditListsActivity
//LongClick brings user to Main Activity

public class ListsActivity extends Activity {

    private android.support.v7.widget.Toolbar toolbar;
    private LinearLayout listLayout;
    private LinkedHashMap<String, ItemList> lists;
    private ImageButton addBtn;
    private int requestCode = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        MyApp myApp = (MyApp) getApplicationContext();
        lists = myApp.getLists();
        if (lists == null) {
            lists = new LinkedHashMap<>();
            myApp.setLists(lists);
        }
        listLayout = (LinearLayout) findViewById(R.id.list_layout);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.list_toolbar);

//        setSupportActionBar(toolbar);

        addBtn = (ImageButton) toolbar.findViewById(R.id.toolbar_layout).findViewById(R.id.list_btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditListsActivity.class);
                intent.putExtra("mode", "create");
                startActivityForResult(intent, requestCode);
            }
        });
        populate();

    }

    private void populate() {
        for (String s : lists.keySet()) {
            ItemList itemList = lists.get(s);
            addNameView(itemList);
        }
    }

    private void addNameView(ItemList itemList) {
        ListNameView nameView = new ListNameView(this);
        final String name = itemList.getName();
        nameView.setTxt(name);
        boolean reminder = false;
        if (reminder) {
            nameView.setImg(R.drawable.alarm_fill);
        } else {
            nameView.setImg(R.drawable.alarm_grey);
        }

        nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), EditListsActivity.class);
                intent.putExtra("mode", "edit");
                intent.putExtra("listname", name);
                startActivityForResult(intent, requestCode);
            }
        });
        listLayout.addView(nameView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.requestCode == requestCode) {
            if (resultCode == RESULT_OK) {
                String listname = data.getStringExtra("listname");
                String mode = data.getStringExtra("mode");
                ItemList itemList = lists.get(listname);
                if (mode.equals("create")) {
                    addNameView(itemList);
                }
            }
        }
    }

}
