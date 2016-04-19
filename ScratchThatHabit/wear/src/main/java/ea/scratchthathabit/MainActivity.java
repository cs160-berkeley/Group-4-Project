package ea.scratchthathabit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.BoxInsetLayout;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import ea.scratchthathabit.R;

public class MainActivity extends WearableActivity {

    private GestureDetectorCompat mDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event){
        int action = MotionEventCompat.getActionMasked(event);
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
            Intent sendWearIntent = new Intent(getBaseContext(), ScratchingAlert.class);
            startActivity(sendWearIntent);
            return true;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent ev) {
            Intent sendWearIntent = new Intent(getBaseContext(), ScratchingAlert.class);
            startActivity(sendWearIntent);
            return true;
        }
        @Override
        public void onShowPress(MotionEvent ev) {
            Log.d("onShowPress",ev.toString());
        }
        @Override
        public void onLongPress(MotionEvent ev) {
            Log.d("onLongPress",ev.toString());
        }
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Intent sendWearIntent = new Intent(getBaseContext(), ScratchingAlert.class);
            startActivity(sendWearIntent);
            return true;
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Intent sendWearIntent = new Intent(getBaseContext(), ScratchingAlert.class);
            startActivity(sendWearIntent);
            return true;
        }
    }
}
