package za.co.dvt.jhb.undertheweather.utils;

import java.util.Locale;

/**
 * Created by josephmokenela on 6/3/17.
 */

public class ConversionUtils {


    public static String getCountryName(String code) {

        Locale loc = new Locale("", code);

        return loc.getDisplayCountry();
    }

    public static String getCeliusTemperature(double temperature) {

       return Math.round((temperature - 273)) + "\u00b0C";
    }
}
