package za.co.dvt.jhb.undertheweather.model;

/**
 * Created by josephmokenela on 6/2/17.
 */

public class Weather {

    private Location location;

    private CurrentCondition currentCondition;

    private Temperature temperature;

    private Wind wind;

    private Rain rain;

    private Snow snow;

    private Cloud cloud;

    private byte[] iconData;

    public Weather() {
        this.currentCondition = new CurrentCondition();
        this.temperature = new Temperature();
        this.wind = new Wind();
        this.rain = new Rain();
        this.snow = new Snow();
        this.cloud = new Cloud();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }



    public CurrentCondition getCurrentCondition() {
        return currentCondition;
    }



    public Temperature getTemperature() {
        return temperature;
    }


    public Wind getWind() {
        return wind;
    }



    public Rain getRain() {
        return rain;
    }


    public Snow getSnow() {
        return snow;
    }


    public Cloud getCloud() {
        return cloud;
    }


    public byte[] getIconData() {
        return iconData;
    }

    public void setIconData(byte[] iconData) {
        this.iconData = iconData;
    }
}
