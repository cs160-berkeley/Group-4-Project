package ea.scratchthathabit;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Simon Bonzon on 4/18/2016.
 */
public class ScratchingDistraction extends ScratchingSolution {

    private Button mNext;
    private Button mBack;
    private int step;
    private ImageView mImage;
    private TextView mStep;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scratching_distraction);
        mNext = (Button) findViewById(R.id.next);
        mBack = (Button) findViewById(R.id.back);
        mImage = (ImageView) findViewById(R.id.instruction);
        mStep = (TextView) findViewById(R.id.step);
        step = 1;
        mNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(step == 17) {
                    Intent sendWearIntent = new Intent(getBaseContext(), ScratchingResolution.class);
                    startActivity(sendWearIntent);
                } else {
                    step++;
                    String imageName = "step_" + step;
                    int id = getResources().getIdentifier("ea.scratchthathabit:drawable/" + imageName,
                            null, null);
                    mImage.setImageResource(id);
                    mStep.setText("Step " + step + ":");
                }
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(step == 1) {
                    Intent sendWearIntent = new Intent(getBaseContext(), ScratchingSolution.class);
                    startActivity(sendWearIntent);
                } else {
                    step--;
                    String imageName = "step_" + step;
                    int id = getResources().getIdentifier("ea.scratchthathabit:drawable/" + imageName,
                            null, null);
                    mImage.setImageResource(id);
                    mStep.setText("Step " + step + ":");
                }
            }
        });
    }
}
