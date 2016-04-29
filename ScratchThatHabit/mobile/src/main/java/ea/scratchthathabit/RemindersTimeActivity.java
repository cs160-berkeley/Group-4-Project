package ea.scratchthathabit;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;


/**
 * Created by Sarah on 4/18/2016.
 * Edited by Tiffanie on 4/26/2016.
 */
public class RemindersTimeActivity extends Activity {

    private GestureDetectorCompat mDetector;
    ReminderClass Reminder;
    EditText ReminderNameInput;
    NumberPicker HourPicker;
    NumberPicker MinutePicker;
    NumberPicker AMPMPicker;
    String[] arrayString;

    ImageButton Sunday;
    ImageButton Monday;
    ImageButton Tuesday;
    ImageButton Wednesday;
    ImageButton Thursday;
    ImageButton Friday;
    ImageButton Saturday;

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders_time);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
        //implemented ReminderClass that represents a reminder
        //Use HourPicker:MinutePicker for time reminder is set off
        //Use AMPMPicker to choose AM/PM
        //Calendar?? for dates... no built in android widget for days of week/onofftoggle buttons with Text


        ReminderNameInput = (EditText) findViewById(R.id.RNameInput);
        String RName = ReminderNameInput.getText().toString();
        Reminder = new ReminderClass(RName);
        //System.out.printf("EXCUSE ME: %s", Reminder.getRName());
        System.out.println("HEYGOTHERE");
        Reminder.setRName(RName);
        String theName = Reminder.getRName();
        System.out.printf("%s", theName);
        HourPicker = (NumberPicker) findViewById(R.id.HourPicker);
        HourPicker.setMaxValue(12);
        HourPicker.setMinValue(1);
        int Hour = HourPicker.getValue();
        Reminder.setRHour(Hour);
        MinutePicker = (NumberPicker) findViewById(R.id.MinutePicker);
        MinutePicker.setMaxValue(59);
        MinutePicker.setMinValue(0);
        int Min = MinutePicker.getValue();
        Reminder.setRMinute(Min);

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

        //How to set off the alarm? (For each reminder)..?

        //GETDAYS:
        Sunday = (ImageButton) findViewById(R.id.Sunday);
        Sunday.setTag(R.drawable.swhite);
        Monday = (ImageButton) findViewById(R.id.Monday);
        Monday.setTag(R.drawable.mwhite);
        Tuesday = (ImageButton) findViewById(R.id.Tuesday);
        Tuesday.setTag(R.drawable.twhite);
        Wednesday = (ImageButton) findViewById(R.id.Wednesday);
        Wednesday.setTag(R.drawable.wwhite);
        Thursday = (ImageButton) findViewById(R.id.Thursday);
        Thursday.setTag(R.drawable.twhite);
        Friday = (ImageButton) findViewById(R.id.Friday);
        Friday.setTag(R.drawable.fwhite);
        Saturday = (ImageButton) findViewById(R.id.Saturday);
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

    @Override
    public boolean onTouchEvent(MotionEvent event){
        Log.d("Gestures", "in onTouchEvent");
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG, "onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            Intent sendIntent = new Intent(getBaseContext(), RemindersActivity.class);
            startActivity(sendIntent);
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Intent sendIntent = new Intent(getBaseContext(), ListsActivity.class);
            startActivity(sendIntent);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return true;
        }

        @Override
        public void onLongPress(MotionEvent ev) {
            Intent sendIntent = new Intent(getBaseContext(), RemindersContextActivity.class);
            startActivity(sendIntent);
        }
    }
}
