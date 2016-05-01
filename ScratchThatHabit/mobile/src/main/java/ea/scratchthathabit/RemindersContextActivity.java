package ea.scratchthathabit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by Sarah on 4/18/2016.
 */
public class RemindersContextActivity extends Activity {

    String activity = "RemindersContext";

    ReminderClass Reminder;

    EditText nameInput;
    EditText addressInput;

    ImageButton save;
    ImageButton close;

    ImageButton Sunday;
    ImageButton Monday;
    ImageButton Tuesday;
    ImageButton Wednesday;
    ImageButton Thursday;
    ImageButton Friday;
    ImageButton Saturday;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(activity, activity + " activity started.");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reminders_context);

        Reminder = new ReminderClass();

        nameInput = (EditText) findViewById(R.id.RNameInput);
        addressInput = (EditText) findViewById(R.id.address_input);


        //GETDAYS:
//        Sunday = (ImageButton) findViewById(R.id.Sunday);
//        Sunday.setTag(R.drawable.swhite);
//        Monday = (ImageButton) findViewById(R.id.Monday);
//        Monday.setTag(R.drawable.mwhite);
//        Tuesday = (ImageButton) findViewById(R.id.Tuesday);
//        Tuesday.setTag(R.drawable.twhite);
//        Wednesday = (ImageButton) findViewById(R.id.Wednesday);
//        Wednesday.setTag(R.drawable.wwhite);
//        Thursday = (ImageButton) findViewById(R.id.Thursday);
//        Thursday.setTag(R.drawable.twhite);
//        Friday = (ImageButton) findViewById(R.id.Friday);
//        Friday.setTag(R.drawable.fwhite);
//        Saturday = (ImageButton) findViewById(R.id.Saturday);
//        Saturday.setTag(R.drawable.swhite);

        View.OnClickListener SunbuttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(activity, "Sunday clicked");

                if ((Integer) Sunday.getTag() == ((R.drawable.swhite))) {
                    Sunday.setBackgroundResource(R.drawable.sgreen);
                    Sunday.setTag(R.drawable.sgreen);
                    Reminder.addRDay("Sunday");
                } else {
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


}
