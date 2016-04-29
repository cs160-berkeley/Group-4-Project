package ea.scratchthathabit;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.wearable.activity.WearableActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends WearableActivity {

    //vars for changing mSleep/mDay mode
    //use mode to determine if scratch alarm should push notification or alarm - Simon
    private ImageButton mSleep;
    private ImageButton mDay;
    private static final String SLEEP = "Sleep";
    private static final String DAY = "Day";
    private String mode;
    //for testing
    private ImageView landing;

    // The following are used for the shake detection
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mode = DAY;
        mSleep = (ImageButton) findViewById(R.id.sleep);
        mDay = (ImageButton) findViewById(R.id.day);
        mSleep.setImageResource(R.drawable.sleep_off);
        mDay.setImageResource(R.drawable.day_on);
        mSleep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mode = SLEEP;
                mSleep.setImageResource(R.drawable.sleep_on);
                mDay.setImageResource(R.drawable.day_off);
                setMargins(mSleep, 105, 0, 0, 29);
            }
        });
        mDay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mode = DAY;
                mSleep.setImageResource(R.drawable.sleep_off);
                mDay.setImageResource(R.drawable.day_on);
                setMargins(mSleep, 105, 0, 0, 30);
            }
        });

        //for testing

        landing = (ImageView) findViewById(R.id.logo);
        landing.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sendWearIntent = new Intent(getBaseContext(), ScratchingAlert.class);
                startActivity(sendWearIntent);
            }
        });

        // ShakeDetector initialization
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            @Override
            public void onShake(int count) {
				Intent intent = new Intent(getBaseContext(), ScratchingAlert.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }


    // from http://stackoverflow.com/a/4472612
    // used to fix mSleep button - it moves slightly when turning on/off
    // b/c drawable images are off - Simon
    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}
