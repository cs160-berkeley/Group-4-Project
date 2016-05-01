package ea.scratchthathabit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Simon Bonzon on 4/20/2016.
 * 4/27 Simon - Impemented interactability
 */
public class GraphActivity extends Activity {

    private Button mDay;
    private Button mMonth;
    private Button mYear;
    private CheckBox mTemperatureBox;
    private CheckBox mScratchingBox;
    private CheckBox mPollenBox;
    private CheckBox mRemindersBox;
    private ImageView mTemperatureLine;
    private ImageView mScratchingLine;
    private ImageView mPollenLine;
    private ImageView mRemindersLine;
    private final String DAY = "DAY";
    private final String MONTH = "MONTH";
    private final String YEAR = "YEAR";
    private final String SELECTED = "#1ab8bf";
    private final String UNSELECTED = "#ffffff";
    private final int VISIBLE = View.VISIBLE;
    private final int INVISIBLE = View.INVISIBLE;
    private String interval;

    private LinearLayout nN;
    private LinearLayout nW;
    private LinearLayout nL;
    private LinearLayout nG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_activity);

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

        nN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reminders(v);
            }
        });


        mDay = (Button) findViewById(R.id.day);
        mMonth = (Button) findViewById(R.id.month);
        mYear = (Button) findViewById(R.id.year);
        mTemperatureBox = (CheckBox) findViewById(R.id.temperatureBox);
        mScratchingBox = (CheckBox) findViewById(R.id.scratchingBox);
        mRemindersBox = (CheckBox) findViewById(R.id.remindersBox);
        mPollenBox = (CheckBox) findViewById(R.id.pollenBox);
        mTemperatureLine = (ImageView) findViewById(R.id.temperatureLine);
        mScratchingLine = (ImageView) findViewById(R.id.scratchingLine);
        mRemindersLine = (ImageView) findViewById(R.id.remindersLine);
        mPollenLine = (ImageView) findViewById(R.id.pollenLine);
        interval = DAY;
        mDay.setBackgroundColor(Color.parseColor(SELECTED));
        mMonth.setBackgroundColor(Color.parseColor(UNSELECTED));
        mYear.setBackgroundColor(Color.parseColor(UNSELECTED));
        mTemperatureBox.setChecked(true);
        mScratchingBox.setChecked(true);
        mRemindersBox.setChecked(true);
        mPollenBox.setChecked(true);
        mTemperatureLine.setImageResource(R.drawable.temperature_day);
        mPollenLine.setImageResource(R.drawable.pollen_day);
        mScratchingLine.setImageResource(R.drawable.scratching_day);
        mRemindersLine.setImageResource(R.drawable.reminders_day);
        mTemperatureLine.setVisibility(VISIBLE);
        mPollenLine.setVisibility(VISIBLE);
        mScratchingLine.setVisibility(VISIBLE);
        mRemindersLine.setVisibility(VISIBLE);
        mDay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                interval = DAY;
                mDay.setBackgroundColor(Color.parseColor(SELECTED));
                mMonth.setBackgroundColor(Color.parseColor(UNSELECTED));
                mYear.setBackgroundColor(Color.parseColor(UNSELECTED));
                mTemperatureLine.setImageResource(R.drawable.temperature_day);
                mPollenLine.setImageResource(R.drawable.pollen_day);
                mScratchingLine.setImageResource(R.drawable.scratching_day);
                mRemindersLine.setImageResource(R.drawable.reminders_day);
            }
        });
        mMonth.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                interval = MONTH;
                mDay.setBackgroundColor(Color.parseColor(UNSELECTED));
                mMonth.setBackgroundColor(Color.parseColor(SELECTED));
                mYear.setBackgroundColor(Color.parseColor(UNSELECTED));
                mTemperatureLine.setImageResource(R.drawable.temperature_month);
                mPollenLine.setImageResource(R.drawable.pollen_month);
                mScratchingLine.setImageResource(R.drawable.scratching_month);
                mRemindersLine.setImageResource(R.drawable.reminders_month);
            }
        });
        mYear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                interval = YEAR;
                mDay.setBackgroundColor(Color.parseColor(UNSELECTED));
                mMonth.setBackgroundColor(Color.parseColor(UNSELECTED));
                mYear.setBackgroundColor(Color.parseColor(SELECTED));
                mTemperatureLine.setImageResource(R.drawable.temperature_year);
                mPollenLine.setImageResource(R.drawable.pollen_year);
                mScratchingLine.setImageResource(R.drawable.scratching_year);
                mRemindersLine.setImageResource(R.drawable.reminders_year);
            }
        });
        mTemperatureBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mTemperatureBox.isChecked()) {
                    mTemperatureLine.setVisibility(VISIBLE);
                } else {
                    mTemperatureLine.setVisibility(INVISIBLE);
                }
            }
        });
        mScratchingBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mScratchingBox.isChecked()) {
                    mScratchingLine.setVisibility(VISIBLE);
                } else {
                    mScratchingLine.setVisibility(INVISIBLE);
                }
            }
        });
        mRemindersBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mRemindersBox.isChecked()) {
                    mRemindersLine.setVisibility(VISIBLE);
                } else {
                    mRemindersLine.setVisibility(INVISIBLE);
                }
            }
        });
        mPollenBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mPollenBox.isChecked()) {
                    mPollenLine.setVisibility(VISIBLE);
                } else {
                    mPollenLine.setVisibility(INVISIBLE);
                }
            }
        });
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
