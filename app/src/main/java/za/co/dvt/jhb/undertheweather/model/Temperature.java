package za.co.dvt.jhb.undertheweather.model;

/**
 * Created by josephmokenela on 6/2/17.
 */

public class Temperature {

    private float temperatur;

    private float minimumTemperature;

    private float maximumTemperature;


    public float getTemperatur() {
        return temperatur;
    }

    public void setTemperatur(float temperatur) {
        this.temperatur = temperatur;
    }

    public float getMinimumTemperature() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(float minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public float getMaximumTemperature() {
        return maximumTemperature;
    }

    public void setMaximumTemperature(float maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }
}
