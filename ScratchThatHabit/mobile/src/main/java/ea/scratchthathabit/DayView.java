package ea.scratchthathabit;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Sarah on 4/28/2016.
 */
public class DayView extends RelativeLayout {

    private TextView day;
    private ImageView weatherImg;
    private TextView temperature;
    private ImageView humidityImage;
    private TextView humidityTxt;
    private ImageView pollenImage;
    private TextView pollenTxt;

    public DayView(Context ctx) {
        super(ctx);
        View.inflate(ctx, R.layout.dayview, this);

        day = (TextView) findViewById(R.id.day);
        weatherImg = (ImageView) findViewById(R.id.weather);
        temperature = (TextView) findViewById(R.id.day_temperature);
        humidityImage = (ImageView) findViewById(R.id.humidity_img);
        humidityTxt = (TextView) findViewById(R.id.humidity_txt);
        pollenImage = (ImageView) findViewById(R.id.pollen_img);
        pollenTxt = (TextView) findViewById(R.id.pollen_txt);
    }

    public void set(String day, String weather, int temp, int humidity, float pollenCount) {
        setDay(day);
        setWeather(weather);
        setTemperature(temp);
        setHumidity(humidity);
        setPollen(pollenCount);
    }

    public void setDay(String dayOfWeek) {
        day.setText(dayOfWeek.substring(0,3));
    }

    public void setWeather(String weather) {
        if (weather.equals("Sunny")) {
            weatherImg.setImageResource(R.drawable.sunny);
        } else if (weather.equals("Mostly Sunny")) {
            weatherImg.setImageResource(R.drawable.mostly_sunny);
        } else {
            weatherImg.setImageResource(R.drawable.cloudy);
        }
    }

    public void setTemperature(int temp) {
        String degree = "\u00b0";
        temperature.setText(String.valueOf(temp) + " " + degree + "F");
    }

    public void setHumidity(int humidity) {
        humidityTxt.setText(String.valueOf(humidity) + "%");
        if (humidity > 70) {
            humidityImage.setImageResource(R.drawable.humidity_high);
        } else if (humidity < 40) {
            humidityImage.setImageResource(R.drawable.humidity_low);
        } else {
            humidityImage.setImageResource(R.drawable.humidity_medium);
        }
    }

    public void setPollen(float count) {
        pollenTxt.setText(String.valueOf(count));
        pollenImage.setImageResource(R.drawable.pollen);
        if (count > 9.7) {
//            too high
        } else if (count > 7.3) {

        } else if (count > 4.9) {

        } else if (count > 2.5) {

        } else {

        }
    }

}
