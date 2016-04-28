package ea.scratchthathabit;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;

//Edited by Tiffanie Lo 4/19/2016 - Added Functionality: Click brings user to ListsActivity
//Long Click brings user to Reminders
//Edited by Simon 4/20 early morning
//Was too lazy to figure out how to work with Tiffanie's implementation, so I
//copy-pasted mine, sorry. But I implemented transitions to the graph/weather activities.
public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener /*implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener*/{

    private GestureDetectorCompat mDetector;
    private GoogleApiClient mGoogleApiClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(LocationServices.API)
                    .addApi(Wearable.API)  // used for data layer API
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        Log.d("Gestures", "in onTouchEvent");
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {}

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {}

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG, "onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            Intent sendIntent = new Intent(getBaseContext(), ListsActivity.class);
            startActivity(sendIntent);
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Intent sendIntent = new Intent(getBaseContext(), GraphActivity.class);
            startActivity(sendIntent);
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Intent sendIntent = new Intent(getBaseContext(), WeatherActivity.class);
            startActivity(sendIntent);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent ev) {
            Intent sendIntent = new Intent(getBaseContext(), RemindersActivity.class);
            startActivity(sendIntent);
        }
    }

    /**
    ImageButton btn;
    GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDetector = new GestureDetectorCompat(this,this);
        mDetector.setOnDoubleTapListener(this);
        btn = (ImageButton) findViewById(R.id.landingscreenbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick2(v);
            }
        });
        btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onClick3(v);
                return false;
            }
        });


    }
    public void onClick2(View view ) {
        System.out.println("ListsActivity Activity started from Main Activity");
        Intent intent = new Intent(this, ListsActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void onClick3(View view ) {
        System.out.println("Reminders Activity started from Main Activity");
        Intent intent = new Intent(this, RemindersActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        return false;
    }

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        return true;
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

*/
}