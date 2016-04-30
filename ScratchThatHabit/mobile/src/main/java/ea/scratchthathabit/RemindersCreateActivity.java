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
import android.widget.NumberPicker;
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

    int colorPrimaryDark;
    int colorWhite;
    int colorText;

    ImageButton save;
    ImageButton close;

    CheckBox vibration;
    CheckBox sound;

//    day buttons
    ImageButton Sunday;
    ImageButton Monday;
    ImageButton Tuesday;
    ImageButton Wednesday;
    ImageButton Thursday;
    ImageButton Friday;
    ImageButton Saturday;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reminders_create);

        MyApp myApp = (MyApp) getApplicationContext();
        reminders = myApp.getReminders();
        if (reminders == null) {
            reminders = new LinkedHashMap<>();
            myApp.setReminders(reminders);
        }

        nameInput = (EditText) findViewById(R.id.RNameInput);
        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        currentView = viewFlipper.findViewById(R.id.reminder_time);

        Intent intent = new Intent();
        mode = intent.getStringExtra("mode");
        if (mode != null && mode.equals("edit")) {
            String rName = intent.getStringExtra("remindername");
            Reminder = reminders.get(rName);
            String rType = Reminder.getType();
            if (rType.equals("time")) {
                setTimeLayout();
            } else {
                setContextLayout();
            }
            populate();
        } else {
            Reminder = new ReminderClass();
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
                Log.d(activity, "Context mode started");
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

    public void setTimeLayout() {
        Log.d(activity, "Setting time layout");
        findDays();
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
    }

    public void findAlarmType() {
        vibration = (CheckBox) currentView.findViewById(R.id.vibration);
        sound = (CheckBox) currentView.findViewById(R.id.sound);
    }

    public void setContextLayout() {
        Log.d(activity, "Setting context layout");
        findDays();
        findAlarmType();

        addressInput = (EditText) currentView.findViewById(R.id.address_input);
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
        Log.d(activity, "mode: " + intent.getStringExtra("mode") + "\nremindername: " + intent.getStringExtra("remindername"));
        finish();
    }

    public void findDays() {
        //GETDAYS:
        Sunday = (ImageButton) currentView.findViewById(R.id.Sunday);
        Sunday.setTag(R.drawable.swhite);
        Monday = (ImageButton) currentView.findViewById(R.id.Monday);
        Monday.setTag(R.drawable.mwhite);
        Tuesday = (ImageButton) currentView.findViewById(R.id.Tuesday);
        Tuesday.setTag(R.drawable.twhite);
        Wednesday = (ImageButton) currentView.findViewById(R.id.Wednesday);
        Wednesday.setTag(R.drawable.wwhite);
        Thursday = (ImageButton) currentView.findViewById(R.id.Thursday);
        Thursday.setTag(R.drawable.twhite);
        Friday = (ImageButton) currentView.findViewById(R.id.Friday);
        Friday.setTag(R.drawable.fwhite);
        Saturday = (ImageButton) currentView.findViewById(R.id.Saturday);
        Saturday.setTag(R.drawable.swhite);

        View.OnClickListener SunbuttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((Integer)Sunday.getTag() == ((R.drawable.swhite))) {
                    Sunday.setBackgroundResource(R.drawable.sgreen);
                    Sunday.setTag(R.drawable.sgreen);
                    Reminder.addRDay("Sunday");
                }
                else {
                    Sunday.setBackgroundResource(R.drawable.swhite);
                    Sunday.setTag(R.drawable.swhite);
                    Reminder.removeRDay("Sunday");
                }
            }
        };
        View.OnClickListener MBL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((Integer)Monday.getTag() == ((R.drawable.mwhite))) {
                    Monday.setBackgroundResource(R.drawable.mgreen);
                    Monday.setTag(R.drawable.mgreen);
                    Reminder.addRDay("Monday");
                }
                else {
                    Monday.setBackgroundResource(R.drawable.mwhite);
                    Monday.setTag(R.drawable.mwhite);
                    Reminder.removeRDay("Monday");
                }
            }
        };
        View.OnClickListener TuBL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((Integer)Tuesday.getTag() == ((R.drawable.twhite))) {
                    Tuesday.setBackgroundResource(R.drawable.tgreen);
                    Tuesday.setTag(R.drawable.tgreen);
                    Reminder.addRDay("Tuesday");
                }
                else {
                    Tuesday.setBackgroundResource(R.drawable.twhite);
                    Tuesday.setTag(R.drawable.twhite);
                    Reminder.removeRDay("Tuesday");
                }
            }
        };
        View.OnClickListener WBL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((Integer)Wednesday.getTag() == ((R.drawable.wwhite))) {
                    Wednesday.setBackgroundResource(R.drawable.wgreen);
                    Wednesday.setTag(R.drawable.wgreen);
                    Reminder.addRDay("Wednesday");
                }
                else {
                    Wednesday.setBackgroundResource(R.drawable.wwhite);
                    Wednesday.setTag(R.drawable.wwhite);
                    Reminder.removeRDay("Wednesday");
                }
            }
        };
        View.OnClickListener THBL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((Integer)Thursday.getTag() == ((R.drawable.twhite))) {
                    Thursday.setBackgroundResource(R.drawable.tgreen);
                    Thursday.setTag(R.drawable.tgreen);
                    Reminder.addRDay("Thursday");
                }
                else {
                    Thursday.setBackgroundResource(R.drawable.twhite);
                    Thursday.setTag(R.drawable.twhite);
                    Reminder.removeRDay("Thursday");
                }
            }
        };
        View.OnClickListener FBL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((Integer)Friday.getTag() == ((R.drawable.fwhite))) {
                    Friday.setBackgroundResource(R.drawable.fgreen);
                    Friday.setTag(R.drawable.fgreen);
                    Reminder.addRDay("Friday");
                }
                else {
                    Friday.setBackgroundResource(R.drawable.fwhite);
                    Friday.setTag(R.drawable.fwhite);
                    Reminder.removeRDay("Friday");
                }
            }
        };
        View.OnClickListener SatBL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((Integer)Saturday.getTag() == ((R.drawable.swhite))) {
                    Saturday.setBackgroundResource(R.drawable.sgreen);
                    Saturday.setTag(R.drawable.sgreen);
                    Reminder.addRDay("Saturday");
                }
                else {
                    Saturday.setBackgroundResource(R.drawable.swhite);
                    Saturday.setTag(R.drawable.swhite);
                    Reminder.removeRDay("Saturday");
                }
            }
        };
        Sunday.setOnClickListener(SunbuttonListener);
        Monday.setOnClickListener(MBL);
        Tuesday.setOnClickListener(TuBL);
        Wednesday.setOnClickListener(WBL);
        Thursday.setOnClickListener(THBL);
        Friday.setOnClickListener(FBL);
        Saturday.setOnClickListener(SatBL);
    }

    public void populate() {

    }

}
