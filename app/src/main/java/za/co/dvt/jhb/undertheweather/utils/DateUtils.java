package za.co.dvt.jhb.undertheweather.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by josephmokenela on 6/2/17.
 */

public class DateUtils {

    public static String getTodaysDate() {

        String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        GregorianCalendar gregorianCalendar = new GregorianCalendar();


        String today = gregorianCalendar.get(Calendar.DATE) + " " + months[gregorianCalendar.get(Calendar.MONTH)] + " " + gregorianCalendar.get(Calendar.YEAR);


        return today;
    }
}
