package ea.scratchthathabit;

import android.content.Intent;
import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by Simon Bonzon on 4/20/2016.
 * Taken from my Represent! app, which was taken from CatNip
 *
 * NOTE!!!!! I literally copy-pasted from my Represent! app
 * and changed nothing except what activity the intent starts
 * since we don't launch any activities from wear as of Prog3
 */
public class PhoneListenerService extends WearableListenerService {

    //   WearableListenerServices don't need an iBinder or an onStartCommand: they just need an onMessageReceieved.
//private static final String TOAST = "/send_toast";
    private static final String DISTRICT = "/District";
    private static final String ZIPCODE = "/ZipCode";
    private static final String LEE = "/Barbara Lee";
    private static final String FEINSTEIN = "/Dianne Feinstein";
    private static final String BOXER = "/Barbara Boxer";


    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in PhoneListenerService, got: " + messageEvent.getPath());
        if( messageEvent.getPath().equalsIgnoreCase( DISTRICT ) ) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MainActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("LOCATION_TYPE", "District");
            intent.putExtra("LOCATION", value);
            Log.d("T", "about to start mobile CongressionalView with LOCATION_TYPE: District");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( ZIPCODE )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MainActivity.class );
            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("LOCATION_TYPE", "ZipCode");
            intent.putExtra("LOCATION", value);
            Log.d("T", "about to start mobile CongressionalView with LOCATION_TYPE: Zip Code");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( LEE )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MainActivity.class );
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("PERSON", "Lee");
            Log.d("T", "about to start watch DetailedView with PERSON: Lee");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( FEINSTEIN )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MainActivity.class );
            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("PERSON", "Feinstein");
            Log.d("T", "about to start watch DetailedView with PERSON: Feinstein");
            startActivity(intent);
        } else if (messageEvent.getPath().equalsIgnoreCase( BOXER )) {
            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
            Intent intent = new Intent(this, MainActivity.class );
            intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
            //you need to add this flag since you're starting a new activity from a service
            intent.putExtra("PERSON", "Boxer");
            Log.d("T", "about to start watch DetailedView with PERSON: Boxer");
            startActivity(intent);
        } else {
            super.onMessageReceived( messageEvent );
        }

    }
}