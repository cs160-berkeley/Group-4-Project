package ea.scratchthathabit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Sarah on 4/18/2016.
 */
public class RemindersContextActivity extends AppCompatActivity {

    ImageButton btn;
    Context ctx;
    private GestureDetectorCompat mDetector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("Reminders context activity started");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reminders_context);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, RemindersActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
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
            Intent sendIntent = new Intent(getBaseContext(), RemindersActivity.class);
            startActivity(sendIntent);
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Intent sendIntent = new Intent(getBaseContext(), ListsActivity.class);
            startActivity(sendIntent);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            Intent sendIntent = new Intent(getBaseContext(), WeatherActivity.class);
//            startActivity(sendIntent);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent ev) {
            Intent sendIntent = new Intent(getBaseContext(), RemindersTimeActivity.class);
            startActivity(sendIntent);
        }
    }

}
