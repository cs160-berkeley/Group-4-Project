package ea.scratchthathabit;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by Simon Bonzon on 4/20/2016.
 * Taken from my Represent! app, which was taken from CatNip
 */
public class WatchListenerService extends WearableListenerService {
    // In PhoneToWatchService, we passed in a path, either "/FRED" or "/LEXY"
    // These paths serve to differentiate different phone-to-watch messages
    private static final String TIMED = "/Timed";
    private static final String CONTEXT = "/Context";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in WatchListenerService, got: " + messageEvent.getPath());
        //use the 'path' field in sendmessage to differentiate use cases

        /* Following sends intent to RepView, but is unused. Add resources from phone for RepView to use */

        if( messageEvent.getPath().equalsIgnoreCase( TIMED ) ) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, TimedReminder.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            //Log.d("T", "about to start watch RepresentativeView with LOCATION_TYPE: District");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( CONTEXT )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, ContextualReminder.class );
            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            //you need to add this flag since you're starting a new activity from a service
            //Log.d("T", "about to start watch RepresentativeView with LOCATION_TYPE: Zip Code");
            startActivity(intent);
        } else {
            super.onMessageReceived( messageEvent );
        }

    }
}