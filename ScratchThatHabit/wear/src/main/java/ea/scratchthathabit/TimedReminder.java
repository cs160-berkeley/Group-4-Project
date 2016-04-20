package ea.scratchthathabit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import ea.scratchthathabit.R;
import ea.scratchthathabit.ScratchingAlert;
import ea.scratchthathabit.ScratchingDistraction;
import ea.scratchthathabit.ScratchingResolution;

/**
 * Created by Simon Bonzon on 4/18/2016.
 */
public class TimedReminder extends MainActivity {

    private GestureDetectorCompat mDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timed_reminder);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
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
        public boolean onDoubleTap(MotionEvent event) {
            Intent sendWearIntent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(sendWearIntent);
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            Intent sendWearIntent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(sendWearIntent);
            return true;
        }
    }
}
