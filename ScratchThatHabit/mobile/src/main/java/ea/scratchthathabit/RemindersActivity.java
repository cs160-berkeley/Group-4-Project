package ea.scratchthathabit;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import java.util.LinkedHashMap;

/**
 * Created by Sarah on 4/18/2016.
 * Edited by Tiffanie on 4/19/2016 - added functionality: Long Click brings user back to MainActivity
 * Edited by Simon 4/20 early morning
 * Was too lazy to figure out how to work with Tiffanie's implementation, so I
 * copy-pasted mine, sorry. But I started implementing sending reminders to wear.
 * 4/27, Simon: Implemented reminder pushing, including data passing. Just need to make sure it
 * works once reminders/lists fully implemented. Also created switches for use during demo.
 */

public class RemindersActivity extends Activity {

    private GestureDetectorCompat mDetector;
    // following button and switches are for use during demo
    // mReminder will be replaced once ReminderActivity is implemented
    private Button mReminder;
    // toggle to push reminder to wear instead of opening edit view
    private boolean pushReminder;
    private Switch mPush;
    // toggle to push timed(FALSE)/contextual(TRUE) reminder
    private boolean pushType;
    private Switch mType;


    LinkedHashMap<String, ReminderClass> reminders;

    private LinearLayout remindersLayout;
    ImageButton addBtn;
    private int requestCode = 1;

    NotificationCompat.Builder mBuilder;
    NotificationManager mNotificationManager;
    int mId;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        MyApp myApp = (MyApp) getApplicationContext();
        reminders = myApp.getReminders();
        if (reminders == null) {
            reminders = new LinkedHashMap<>();
            myApp.setReminders(reminders);
        }

        pushReminder = false;
        mPush = (Switch) findViewById(R.id.push);
        mPush.setChecked(false);
        mPush.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (pushReminder) {
                    mPush.setText("Push: Off");
                    pushReminder = false;
                } else {
                    mPush.setText("Push: On");
                    pushReminder = true;
                }
            }
        });
        pushType = false;
        mType = (Switch) findViewById(R.id.type);
        mType.setChecked(false);
        mType.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (pushType) {
                    mType.setText("Type: Timed");
                    pushType = false;
                } else {
                    mType.setText("Type: Contextual");
                    pushType = true;
                }
            }
        });
        mReminder = (Button) findViewById(R.id.reminder);
        mReminder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (pushReminder) {
                    if (pushType) {
                        Intent sendWearIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                        sendWearIntent.putExtra("TYPE", "Context");
                        sendWearIntent.putExtra("LIST", "Backpack");
                        sendWearIntent.putExtra("ITEMS", "Water Bottle\nVaseline\nAllergy Pills");
                        startService(sendWearIntent);
                    } else {
                        Intent sendWearIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                        sendWearIntent.putExtra("TYPE", "Timed");
                        sendWearIntent.putExtra("REMINDER", "Drink Water");
                        sendWearIntent.putExtra("TIME", "2:00 PM");
                        startService(sendWearIntent);
                    }
                } else {
                    Intent sendIntent = new Intent(getBaseContext(), RemindersTimeActivity.class);
                    startActivity(sendIntent);
                }
            }
        });

        addBtn = (ImageButton) findViewById(R.id.btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RemindersCreateActivity.class);
                intent.putExtra("mode", "create");
                startActivityForResult(intent, requestCode);
            }
        });

        remindersLayout = (LinearLayout) findViewById(R.id.reminders_layout);
        populate();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (this.requestCode == requestCode) {
            if (resultCode == RESULT_OK) {
                String reminderName = data.getStringExtra("remindername");
                String mode = data.getStringExtra("mode");
                ReminderClass reminder = reminders.get(reminderName);
                if (mode.equals("create")) {
                    addNameView(reminder);
                }
            }
        }
    }

    private void populate() {
        for (String s : reminders.keySet()) {
            ReminderClass reminder = reminders.get(s);
            addNameView(reminder);
        }
    }

    private void addNameView(ReminderClass reminder) {
        ReminderNameView reminderNameView = new ReminderNameView(this);
        final String name = reminder.getRName();
        reminderNameView.setReminderName(name);
        reminderNameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), RemindersCreateActivity.class);
                intent.putExtra("mode", "edit");
                intent.putExtra("remindername", name);
                startActivityForResult(intent, requestCode);
            }
        });
        remindersLayout.addView(reminderNameView);
    }

}

