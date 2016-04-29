package ea.scratchthathabit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import ea.scratchthathabit.R;
import ea.scratchthathabit.ScratchingAlert;
import ea.scratchthathabit.ScratchingDistraction;
import ea.scratchthathabit.ScratchingResolution;

/**
 * Created by Simon Bonzon on 4/18/2016.
 */
public class ScratchingSolution extends ScratchingAlert {

    private Button mMoisturize;
    private Button mDistract;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scratching_solution);
        mMoisturize = (Button) findViewById(R.id.moisturize);
        mDistract = (Button) findViewById(R.id.distract);
        mMoisturize.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sendWearIntent = new Intent(getBaseContext(), ScratchingResolution.class);
                startActivity(sendWearIntent);
            }
        });
        mDistract.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sendWearIntent = new Intent(getBaseContext(), ScratchingDistraction.class);
                startActivity(sendWearIntent);
            }
        });
    }
}
