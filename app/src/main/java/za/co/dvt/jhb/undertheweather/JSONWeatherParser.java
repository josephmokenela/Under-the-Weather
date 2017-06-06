package za.co.dvt.jhb.undertheweather;

import android.util.Log;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import za.co.dvt.jhb.undertheweather.model.CurrentCondition;
import za.co.dvt.jhb.undertheweather.model.Location;
import za.co.dvt.jhb.undertheweather.model.Weather;

/**
 * Created by josephmokenela on 6/2/17.
 */

public class JSONWeatherParser {

    private final static String TAG = "JSONWeatherParser.class";


    public static Weather getWeather(String data) throws JSONException {
        Weather weather = new Weather();

        Log.i(TAG, "The data " + data);

        // We create out JSONObject from the data
        JSONObject jObj = new JSONObject(data);

        // We start extracting the info
        Location loc = new Location();

        JSONObject coordObj = getObject("coord", jObj);
        loc.setLatitude(getFloat("lat", coordObj));
        loc.setLongitude(getFloat("lon", coordObj));

        JSONObject sysObj = getObject("sys", jObj);
        loc.setCountry(getString("country", sysObj));
        loc.setSunrise(getInt("sunrise", sysObj));
        loc.setSunset(getInt("sunset", sysObj));
        loc.setCity(getString("name", jObj));
        weather.setLocation(loc);

        // We get weather info (This is an array)
        JSONArray jArr = jObj.getJSONArray("weather");

        // We use only the first value
        JSONObject JSONWeather = jArr.getJSONObject(0);
        weather.getCurrentCondition().setWeatherId(getInt("id", JSONWeather));
        weather.getCurrentCondition().setDescription(getString("description", JSONWeather));
        weather.getCurrentCondition().setCondition(getString("main", JSONWeather));
        weather.getCurrentCondition().setIcon(getString("icon", JSONWeather));

        JSONObject mainObj = getObject("main", jObj);
        weather.getCurrentCondition().setHumidity(getInt("humidity", mainObj));
        weather.getCurrentCondition().setPressure(getInt("pressure", mainObj));
        weather.getTemperature().setMaximumTemperature(getFloat("temp_max", mainObj));
        weather.getTemperature().setMinimumTemperature(getFloat("temp_min", mainObj));
        weather.getTemperature().setTemperatur(getFloat("temp", mainObj));

        // Wind
        JSONObject wObj = getObject("wind", jObj);
        weather.getWind().setSpeed(getFloat("speed", wObj));
        //weather.getWind().setDegree(getFloat("deg", wObj));

        // Clouds
        JSONObject cObj = getObject("clouds", jObj);
        weather.getCloud().setPercentage(getInt("all", cObj));

        // We download the icon to show


        return weather;
    }


    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

}
