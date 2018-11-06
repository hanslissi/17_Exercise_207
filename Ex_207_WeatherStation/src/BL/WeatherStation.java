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

    public WeatherStation(String place, int seaLevel, double temperature, int humidity) {
        this.place = place;
        this.seaLevel = seaLevel;
        this.temperature = temperature;
        this.humidity = humidity;
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
    
    
}
