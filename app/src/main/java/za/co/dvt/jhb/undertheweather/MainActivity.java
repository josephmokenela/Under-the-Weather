package za.co.dvt.jhb.undertheweather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.dvt.jhb.undertheweather.model.Weather;
import za.co.dvt.jhb.undertheweather.utils.ConversionUtils;
import za.co.dvt.jhb.undertheweather.utils.DateUtils;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.cityText)
    TextView cityText;

    @BindView(R.id.condDescr)
    TextView condDescr;

    @BindView(R.id.temp)
    TextView temp;

    @BindView(R.id.condIcon)
    ImageView imgView;

    @BindView(R.id.dateText)
    TextView dateText;

    @BindView(R.id.minTempText)
    TextView minimumTemperature;

    @BindView(R.id.maxTempText)
    TextView maximumTemperature;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String city = "Centurion,za";

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ( (new WeatherHttpClient()).getWeatherData(params[0]));

            try {
                weather = JSONWeatherParser.getWeather(data);

                // Let's retrieve the icon
                //byte[] iconData = ( (new WeatherHttpClient()).getImage(weather.getCurrentCondition().getIcon()));
                //weather.setIconData(iconData);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }




        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            if (weather.getIconData() != null && weather.getIconData().length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(weather.getIconData(), 0, weather.getIconData().length);
                imgView.setImageBitmap(img);
            }

            cityText.setText(weather.getLocation().getCity() + ", " + ConversionUtils.getCountryName((weather.getLocation().getCountry())));
            condDescr.setText(weather.getCurrentCondition().getCondition() + "(" + weather.getCurrentCondition().getDescription() + ")");
            temp.setText(ConversionUtils.getCeliusTemperature(weather.getTemperature().getTemperatur()));
            minimumTemperature.setText("Min:  " + ConversionUtils.getCeliusTemperature((weather.getTemperature().getMinimumTemperature())));
            maximumTemperature.setText("Max:  " + ConversionUtils.getCeliusTemperature((weather.getTemperature().getMaximumTemperature())));
            dateText.setText("Today,  " + DateUtils.getTodaysDate());

        }

    }
}
