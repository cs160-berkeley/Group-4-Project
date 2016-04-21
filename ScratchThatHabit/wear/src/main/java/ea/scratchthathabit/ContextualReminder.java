package ea.scratchthathabit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by Simon Bonzon on 4/19/2016.
 */
public class ContextualReminder extends MainActivity {

    private GestureDetectorCompat mDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contextual_reminder);
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
            Intent sendWearIntent = new Intent(getBaseContext(), ItemList.class);
            startActivity(sendWearIntent);
            Intent sendMobileIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
            sendMobileIntent.putExtra("TYPE", "List");
            startService(sendMobileIntent);
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
