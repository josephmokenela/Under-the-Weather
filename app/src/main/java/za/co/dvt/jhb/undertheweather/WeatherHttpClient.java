package za.co.dvt.jhb.undertheweather;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by josephmokenela on 6/2/17.
 */

public class WeatherHttpClient {


    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";

    private static String IMG_URL = "http://api.openweathermap.org/img/w/";

    private static String API_KEY = "&appid=49eb872346728aa89ade4566165d2539";

    private static String IMAGE_EXTENSION = ".png";

    private final String TAG = getClass().getSimpleName();

    private final String LAT = "lat=";

    private final String LOG = "&lon=";



    public String getWeatherData(Double... coordinates) {
        HttpURLConnection con = null ;
        InputStream is = null;

        double latitude = coordinates[0];
        double longitude = coordinates[1];



        try {
            con = (HttpURLConnection) ( new URL(BASE_URL + LAT + latitude + LOG + longitude + API_KEY)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;

    }

    public String getImageAddress(String code) {

        return IMG_URL + code + IMAGE_EXTENSION;
    }

}
