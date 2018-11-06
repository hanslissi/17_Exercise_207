/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

/**
 *
 * @author johannesriedmueller
 */
public class WeatherStation {

    private String place;
    private int seaLevel;
    private double temperature;
    private int humidity;

    public WeatherStation(String place, int seaLevel, double temperature, int humidity) throws Exception {
        this.place = place;
        this.seaLevel = seaLevel;
        setHumidity(humidity);
        setTemperature(temperature);
    }

    public String getPlace() {
        return place;
    }

    public int getSeaLevel() {
        return seaLevel;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setTemperature(double temperature) throws Exception {
        if (temperature >= -35 && temperature <= 45) {
            this.temperature = temperature;
        } else {
            throw new Exception("Temperature isn't valid.");
        }
    }

    public void setHumidity(int humidity) throws Exception {
        if (humidity >= 0 && humidity <= 100) {
            this.humidity = humidity;
        } else {
            throw new Exception("Humidity isn't valid.");
        }
    }

}
