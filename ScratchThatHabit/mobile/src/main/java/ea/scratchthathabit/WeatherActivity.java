package ea.scratchthathabit;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Simon Bonzon on 4/20/2016.
 */
public class WeatherActivity extends Activity implements  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private String activity = "WEATHER";
    private String mLatitude;
    private String mLongitude;
    private String mZip;
    private String clientId = "BuF6EeszylxunlDSj3SV3";
    private String clientSecret = "ej2hArpuadP9hsK9AbCPuE3ufNrYRKjM5RDp99Yg";
    private String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private int day;
    private int[] humidityIds = {};


    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_activity);

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
        day = calendar.getTime().getDay();

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
        Log.d(activity, "Connected to Google Maps API");
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location != null) {
            mLatitude = String.valueOf(location.getLatitude());
            mLongitude = String.valueOf(location.getLongitude());
            Log.d(activity, String.format("Latitude is: %s, longitude is: %s ", mLatitude, mLongitude));
            new getZipTask().execute();
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private class getZipTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String getZipUrl = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + mLatitude + "," + mLongitude;
            Log.d(activity, getZipUrl);
            StringBuilder result = new StringBuilder();
            JSONObject allForecast = new JSONObject();
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

                JSONObject requestBody = new JSONObject(result.toString());
                JSONArray jsonResults = requestBody.getJSONArray("results");
                JSONArray addressComponents = jsonResults.getJSONObject(0).getJSONArray("address_components");
                for (int i = 0; i < addressComponents.length(); i++) {
                    JSONObject components = addressComponents.getJSONObject(i);
                    JSONArray types = components.getJSONArray("types");

                    if (types.getString(0).equals("postal_code")) {
                        mZip = components.getString("short_name");
                        Log.d(activity, mZip);
                    }
                }
                if (mZip != null) {
                    String getPollenURL = "https://www.claritin.com/webservice/allergyforecast.php?zip=" + mZip + "&_=1461103389676";
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

                    allForecast.put("pollen", forecast);
                    allForecast.put("allergens", allergens);


//                    get forecast from aerisweather
                    url = new URL(getForecastURL());
                    Log.d(activity, url.toString());
                    urlConnection = (HttpURLConnection) url.openConnection();
                    in = new BufferedInputStream(urlConnection.getInputStream());
                    br = new BufferedReader(new InputStreamReader(in));
                    result = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        result.append(line);
                    }
                    urlConnection.disconnect();
                    JSONObject forecastObject = new JSONObject(result.toString());
                    JSONArray responseArray = forecastObject.getJSONArray("response");
                    JSONArray periodArray = responseArray.getJSONObject(0).getJSONArray("periods");


                    JSONArray humidity = new JSONArray();
                    JSONArray temperature = new JSONArray();
                    JSONArray weather = new JSONArray();
                    for (int i = 0; i < periodArray.length(); i++) {
                        JSONObject day = periodArray.getJSONObject(i);
                        humidity.put(i, day.get("humidity"));
                        temperature.put(i, day.get("avgTempF"));
                        weather.put(i, day.get("weather"));
                    }

                    allForecast.put("humidity", humidity);
                    allForecast.put("temperature", temperature);
                    allForecast.put("weather", weather);

                    Log.d(activity, allForecast.toString());
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return allForecast.toString();
            }
        }


        @Override
        protected void onPostExecute(String s) {
            if (s == null) {
                return;
            }
            try {
                JSONObject obj = new JSONObject(s);
                JSONArray pollen = obj.getJSONArray("pollen");
                String allergens = obj.getString("allergens");
                JSONArray humidity = obj.getJSONArray("humidity");
                JSONArray temperature = obj.getJSONArray("temperature");
                JSONArray weather = obj.getJSONArray("weather");

                for (int i = 0; i < 4; i++) {
                    setForecast(i, weather.getString(i), (float)pollen.getDouble(i), humidity.getInt(i), temperature.getInt(i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            super.onPostExecute(s);
        }
    }


    private void setForecast(int i, String weather, float pollen, int humidity, int temperature) {
        String dayOfWeek = days[(day + i) % 7];
        if (i == 0) {
            TextView today = (TextView) findViewById(R.id.today);
            today.setText(dayOfWeek);
            ImageView weatherImage = (ImageView) findViewById(R.id.today_weather);
            TextView temperatureText = (TextView) findViewById(R.id.today_temp);
            temperatureText.setText(String.valueOf(temperature));
            TextView humidityText = (TextView) findViewById(R.id.humidity);
            humidityText.setText(String.valueOf(humidity) + " %");
            ImageView humidityImage = (ImageView) findViewById(R.id.humidity_img);
            if (humidity > 70) {
                humidityImage.setImageResource(R.drawable.humidity_high);
            } else if (humidity < 40) {
                humidityImage.setImageResource(R.drawable.humidity_low);
            } else {
                humidityImage.setImageResource(R.drawable.humidity_medium);
            }
            ImageView pollenImage = (ImageView) findViewById(R.id.pollen_img);
            pollenImage.setImageResource(R.drawable.pollen);
            TextView pollenText = (TextView) findViewById(R.id.pollen);
            pollenText.setText(String.valueOf(pollen));
            if (weather.equals("Sunny")) {
                weatherImage.setImageResource(R.drawable.sunny);
            } else if (weather.equals("Mostly Sunny")) {
                weatherImage.setImageResource(R.drawable.mostly_sunny);
            } else {
                weatherImage.setImageResource(R.drawable.cloudy);
            }

        } else {
            LinearLayout future = (LinearLayout) findViewById(R.id.future);
            DayView dayView = new DayView(this);
            dayView.set(dayOfWeek, weather, temperature, humidity, pollen);
            future.addView(dayView);
        }

    }

    private String getForecastURL() {
        return "https://api.aerisapi.com/forecasts/" + mZip + "?" + "client_id=" + clientId + "&client_secret=" + clientSecret + "&from=" + days[day] + "&to=+3days";
    }

}
