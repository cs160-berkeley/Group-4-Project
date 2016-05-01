package ea.scratchthathabit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by Sarah on 4/29/2016.
 */
public class RemindersCreateActivity extends Activity {


    String mode;
    String activity = "ReminderCreate";
    ViewFlipper viewFlipper;
    EditText nameInput;
    Button timeToggle;
    Button contextToggle;
    String type = "time";
    View currentView;
    LinkedHashMap<String, ReminderClass> reminders;
    LinkedHashMap<String, ItemList> lists;

    int colorPrimaryDark;
    int colorWhite;
    int colorText;

    ImageButton save;
    ImageButton close;

    CheckBox vibration;
    CheckBox sound;

    //    day buttons
    LinearLayout daysPanel;

//    reminder data
    ReminderClass Reminder;
    String[] arrayString;

//    time specific
    NumberPicker HourPicker;
    NumberPicker MinutePicker;
    NumberPicker AMPMPicker;

    //    context specific
    CheckBox arriving;
    CheckBox leaving;
    EditText addressInput;

    ImageButton attachList;

    int requestCode = 1;

    private LinearLayout nN;
    private LinearLayout nW;
    private LinearLayout nL;
    private LinearLayout nG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reminders_create);

        nN = (LinearLayout) findViewById(R.id.nag_notifcations);
        nW = (LinearLayout) findViewById(R.id.nag_weather);
        nL = (LinearLayout) findViewById(R.id.nag_lists);
        nG = (LinearLayout) findViewById(R.id.nag_graphs);


        nW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weather(v);
            }
        });

        nL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lists(v);
            }
        });


        nG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graphs(v);
            }
        });


        MyApp myApp = (MyApp) getApplicationContext();
        reminders = myApp.getReminders();
        lists = myApp.getLists();
        if (reminders == null) {
            reminders = new LinkedHashMap<>();
            myApp.setReminders(reminders);
        }
        if (lists == null) {
            lists = new LinkedHashMap<>();
            myApp.setLists(lists);
        }

        nameInput = (EditText) findViewById(R.id.RNameInput);
        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);

        Intent intent = getIntent();
        mode = intent.getStringExtra("mode");
        if (mode != null && mode.equals("edit")) {
            String rName = intent.getStringExtra("remindername");
            Reminder = reminders.get(rName);
            String rType = Reminder.getType();
            if (rType.equals("time")) {
                currentView = viewFlipper.findViewById(R.id.reminder_time);
                setTimeLayout();
            } else {
                currentView = viewFlipper.findViewById(R.id.reminder_context);
                setContextLayout();
            }
            populate();
        } else {
            Reminder = new ReminderClass();
            currentView = viewFlipper.findViewById(R.id.reminder_time);
            setTimeLayout();
        }


        save = (ImageButton) findViewById(R.id.btn_save);
        close = (ImageButton) findViewById(R.id.btn_close);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSave();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        timeToggle = (Button) findViewById(R.id.time_toggle);
        contextToggle = (Button) findViewById(R.id.context_toggle);

        colorPrimaryDark = ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark);
        colorWhite = ContextCompat.getColor(getBaseContext(), R.color.white);
        colorText = ContextCompat.getColor(getBaseContext(), R.color.text);

        timeToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(activity, "Time mode started");
                if (!type.equals("time")) {
                    viewFlipper.setDisplayedChild(0);
                    currentView = viewFlipper.findViewById(R.id.reminder_time);
                    type = "time";
                    timeToggle.getBackground().setColorFilter(colorPrimaryDark, PorterDuff.Mode.MULTIPLY);
                    timeToggle.setTextColor(colorWhite);
                    contextToggle.getBackground().setColorFilter(colorWhite, PorterDuff.Mode.MULTIPLY);
                    contextToggle.setTextColor(colorText);
                    setTimeLayout();
                }
            }
        });

        contextToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!type.equals(("context"))) {
                    viewFlipper.setDisplayedChild(1);
                    currentView = viewFlipper.findViewById(R.id.reminder_context);
                    type = "context";
                    timeToggle.getBackground().setColorFilter(colorWhite, PorterDuff.Mode.MULTIPLY);
                    timeToggle.setTextColor(colorText);
                    contextToggle.getBackground().setColorFilter(colorPrimaryDark, PorterDuff.Mode.MULTIPLY);
                    contextToggle.setTextColor(colorWhite);
                    setContextLayout();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.requestCode == requestCode) {
            if (resultCode == RESULT_OK) {
                String listname = data.getStringExtra("listname");
                Log.d(activity, listname + " attached");
                ItemList itemList = lists.get(listname);
                Reminder.setItemList(itemList);

                //change attachment text/icon here
                final TextView attachListText = (TextView) currentView.findViewById(R.id.attach_list_text);
                attachListText.setText("List " + listname + "attached");

                attachList.setBackground(getDrawable(R.drawable.close_button));
            }
        }
    }

    public void setTimeLayout() {
        setDays();
        findAlarmType();

        HourPicker = (NumberPicker) findViewById(R.id.HourPicker);
        HourPicker.setMaxValue(12);
        HourPicker.setMinValue(1);

        MinutePicker = (NumberPicker) findViewById(R.id.MinutePicker);
        MinutePicker.setMaxValue(59);
        MinutePicker.setMinValue(0);

        AMPMPicker = (NumberPicker) findViewById(R.id.AMPMPicker);
        arrayString = new String[]{"AM", "PM"};
        AMPMPicker.setMinValue(0);
        AMPMPicker.setMaxValue(arrayString.length - 1);
        AMPMPicker.setFormatter(new NumberPicker.Formatter() {

            @Override
            public String format(int value) {
                // TODO Auto-generated method stub
                return arrayString[value];
            }
        });

        attachList = (ImageButton) currentView.findViewById(R.id.attach_list_button);
        attachList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ListsActivity.class);
                intent.putExtra("mode", "attach");
                startActivityForResult(intent, requestCode);

            }
        });
    }

    public void findAlarmType() {
        vibration = (CheckBox) currentView.findViewById(R.id.vibration);
        sound = (CheckBox) currentView.findViewById(R.id.sound);
    }

    public void setContextLayout() {
        setDays();
        findAlarmType();

        addressInput = (EditText) currentView.findViewById(R.id.address_input);

        attachList = (ImageButton) currentView.findViewById(R.id.attach_list_button);
        attachList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ListsActivity.class);
                intent.putExtra("mode", "attach");
                startActivityForResult(intent, requestCode);
            }
        });
    }

    public void onSave() {
        String name = nameInput.getText().toString();
        if (name.equals("")) {
            return;
        }
        Reminder.setRName(name);
        Reminder.setType(type);

        reminders.put(name, Reminder);

        Intent intent = new Intent();

        intent.putExtra("mode", mode);
        intent.putExtra("remindername", name);


        setResult(RESULT_OK, intent);
        finish();
    }

    public void setDays() {
        daysPanel = (LinearLayout) currentView.findViewById(R.id.days_panel);
        Log.d(activity,"" + daysPanel.getChildCount());
        for (int i = 0; i < daysPanel.getChildCount(); i++) {
            final LinearLayout dayLayout = (LinearLayout) daysPanel.getChildAt(i);
            final TextView dayText = (TextView) dayLayout.getChildAt(0);
            dayLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tag = (String) dayLayout.getTag();
                    String color = tag.substring(0, 5);
                    String day = tag.substring(5, tag.length());
                    if (color.equals("white")) {
                        dayLayout.setBackgroundColor(colorPrimaryDark);
                        dayLayout.setTag("green" + day);
                        dayText.setTextColor(colorWhite);
                        Reminder.addRDay(day);
                    } else {
                        dayLayout.setBackgroundColor(colorWhite);
                        dayLayout.setTag("white" + day);
                        dayText.setTextColor(colorText);
                        Reminder.removeRDay(day);
                    }
                }
            });
        }
    }


    public void populate() {
        nameInput.setText(Reminder.getRName());
    }

    public void weather(View view ) {
        Intent intent = new Intent(this, WeatherActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void lists(View view ) {
        Intent intent = new Intent(this, ListsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void graphs(View view ) {
        Intent intent = new Intent(this, GraphActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void reminders(View view ) {
        Intent intent = new Intent(this, RemindersActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
