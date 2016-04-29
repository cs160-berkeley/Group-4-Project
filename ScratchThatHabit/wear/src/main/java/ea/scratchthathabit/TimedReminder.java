package ea.scratchthathabit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ea.scratchthathabit.R;
import ea.scratchthathabit.ScratchingAlert;
import ea.scratchthathabit.ScratchingDistraction;
import ea.scratchthathabit.ScratchingResolution;

/**
 * Created by Simon Bonzon on 4/18/2016.
 */
public class TimedReminder extends MainActivity {

    private TextView mReminder;
    private TextView mTime;
    private Button mDone;
    private Button mLater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timed_reminder);
        mReminder = (TextView) findViewById(R.id.reminder);
        mTime = (TextView) findViewById(R.id.time);
        Intent intent = getIntent();
        final Bundle extras = intent.getExtras();
        if (extras != null) {
            String reminder = extras.getString("REMINDER");
            String time = extras.getString("TIME");
            mReminder.setText(reminder);
            mTime.setText(time);
        }
        mDone = (Button) findViewById(R.id.done);
        mLater = (Button) findViewById(R.id.later);
        mDone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sendWearIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(sendWearIntent);
            }
        });
        mLater.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sendWearIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(sendWearIntent);
            }
        });
    }
}
