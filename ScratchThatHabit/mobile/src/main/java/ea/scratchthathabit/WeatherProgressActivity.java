package ea.scratchthathabit;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sarah on 4/19/2016.
 */
public class WeatherProgressActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

//    String url = "http://api.wunderground.com/api/959ec4bfbed19093/conditions/q/CA/San_Francisco.json";
//    String API_key = "54e774d44be8257d549b05d639d5af57";
    String mLatitude;
    String mLongitude;


    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_progress);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d("T", "Connected to Google Maps API");
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location != null) {
            mLatitude = String.valueOf(location.getLatitude());
            mLongitude = String.valueOf(location.getLongitude());
            Log.d("T", String.format("Latitude is: %s, longitude is: %s ", mLatitude, mLongitude));
            new getZipTask().execute();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private class getZipTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            String getZipUrl = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + mLatitude + "," + mLongitude;
            System.out.println(getZipUrl);
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(getZipUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                String line;
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
                urlConnection.disconnect();
                String mZip = null;

                JSONObject requestBody = new JSONObject(result.toString());
                JSONArray jsonResults = requestBody.getJSONArray("results");
                JSONArray addressComponents = jsonResults.getJSONObject(0).getJSONArray("address_components");
                for (int i = 0; i < addressComponents.length(); i++) {
                    JSONObject components = addressComponents.getJSONObject(i);
                    JSONArray types = components.getJSONArray("types");

                    if (types.getString(0).equals("postal_code")) {
                        mZip = components.getString("short_name");
                        System.out.println(mZip);
                    }
                }
                if (mZip != null) {
                    String getPollenURL = "https://www.claritin.com/webservice/allergyforecast.php?zip=" + mZip + "&_=1461103389676";
                    System.out.println(getPollenURL);
                    url = new URL(getPollenURL);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    in = new BufferedInputStream(urlConnection.getInputStream());
                    br = new BufferedReader(new InputStreamReader(in));

                    result = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        result.append(line);
                    }
                    urlConnection.disconnect();
                    result.replace(result.lastIndexOf("\""), result.length(), "");
                    result.replace(result.indexOf("\""), result.indexOf("\"") + 1, "");
                    JSONArray array = new JSONArray(result.toString().replaceAll("\\\\", ""));
                    JSONObject object = array.getJSONObject(0);
                    JSONObject obj = object.getJSONObject("pollenForecast");
                    String zip = obj.getString("zip");
                    String allergens = obj.getString("pp");
                    JSONArray forecast = obj.getJSONArray("forecast");
                    String[] info = new String[4];
                    info[0] = zip;
                    info[1] = forecast.getString(0);
                    info[2] = forecast.getString(1);
                    info[3] = allergens;
                    return info;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String[] s) {
            TextView zip = (TextView) findViewById(R.id.zip);
            zip.setText(s[0]);
            TextView today = (TextView) findViewById(R.id.today);
            today.setText(s[1]);
            TextView tomorrow = (TextView) findViewById(R.id.tomorrow);
            tomorrow.setText(s[2]);
            TextView allergens = (TextView) findViewById(R.id.allergens);
            allergens.setText(s[3]);
            super.onPostExecute(s);
        }
    }
}
