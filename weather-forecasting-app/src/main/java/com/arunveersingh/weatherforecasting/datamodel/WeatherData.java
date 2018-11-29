package com.arunveersingh.weatherforecasting.datamodel;

import java.io.Serializable;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Pojo to carry the information of the city, minimum temperature for the day,
 * and list of data points having temperature data for intervals for requested
 * zipcode.
 * 
 * @author arunveersingh9@gmail.com
 *
 */
public class WeatherData implements Serializable {

    private static final long serialVersionUID = 1L;

    // Response from
    private ArrayList<WeatherDataPoint> list = new ArrayList<>();

    private City city;

    private Double minTemperatureOfTheDay;

    @JsonProperty("dataPoints")
    public ArrayList<WeatherDataPoint> getList() {
	return list;
    }

    @JsonSetter("list")
    public void setList(ArrayList<WeatherDataPoint> list) {
	this.list = list;
    }

    public City getCityObject() {
	return city;
    }

    @JsonProperty("city")
    public void setCity(City cityObject) {
	this.city = cityObject;
    }

    public void setMinTemperatureOfTheDay(Double minTemperature) {
	this.minTemperatureOfTheDay = minTemperature;
    }

    @JsonProperty("minTempOfTheDay")
    public Double getMinTemperatureOfTheDay() {
	return minTemperatureOfTheDay;
    }

    @Override
    public String toString() {
	return "WeatherData [list=" + list + ", city=" + city
		+ ", minTemperatureOfTheDay=" + minTemperatureOfTheDay + "]";
    }
}
