package ea.scratchthathabit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;

/**
 * Created by Sarah on 4/18/2016.
 * Edited by Tiffanie on 4/19/2016 - added functionality: Long Click brings user back to MainActivity
 * Edited by Simon 4/20 early morning
 * Was too lazy to figure out how to work with Tiffanie's implementation, so I
 * copy-pasted mine, sorry. But I started implementing sending reminders to wear.
 * 4/27, Simon: Implemented reminder pushing, including data passing. Just need to make sure it
 * works once reminders/lists fully implemented. Also created switches for use during demo.
 */

public class RemindersActivity extends Activity /*implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener*/ {

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

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);
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

        //mDetector = new GestureDetectorCompat(this, new MyGestureListener());
    }
/**
    @Override
    public boolean onTouchEvent(MotionEvent event) {
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
            Intent sendIntent = new Intent(getBaseContext(), RemindersTimeActivity.class);
            startActivity(sendIntent);
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Intent sendWearIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
            sendWearIntent.putExtra("TYPE", "Timed");
            startService(sendWearIntent);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Intent sendWearIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
            sendWearIntent.putExtra("TYPE", "Context");
            startService(sendWearIntent);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent ev) {
            Intent sendIntent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(sendIntent);
        }
    }
        */
}

/**
public class RemindersActivity extends AppCompatActivity implements GestureDetector.OnDoubleTapListener {

    private ImageButton btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Reminders activity started");

        setContentView(R.layout.activity_reminders);
        btn = (ImageButton) findViewById(R.id.btn);
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onClick3(v);
                return false;
            }
        });
    }

    public void onClick(View view ) {
        Intent intent = new Intent(this, RemindersTimeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void onClick3(View view ) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}
*/