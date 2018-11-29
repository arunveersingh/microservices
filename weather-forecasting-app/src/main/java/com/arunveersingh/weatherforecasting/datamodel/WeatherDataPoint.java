package com.arunveersingh.weatherforecasting.datamodel;

import java.io.Serializable;
import java.time.Instant;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Below part of the JSON response has led to the design of this class.
 * {dt=1487246400, main={temp=286.67, temp_min=281.556, temp_max=286.67,
 * pressure=972.73, sea_level=1046.46, grnd_level=972.73, humidity=75,
 * temp_kf=5.11}, weather=[{id=800, main=Clear, description=clear sky,
 * icon=01d}], clouds={all=0}, wind={speed=1.81, deg=247.501}, sys={pod=d},
 * dt_txt=2017-02-16 12:00:00},
 * 
 * @author arunveersingh9@gmail.com
 *
 */
public class WeatherDataPoint implements Serializable {

	private static final long serialVersionUID = 1L;

	private Instant instant;

	private double temperature;

	private double minTemperature;

	// passed as "timestamp" by this app
	@JsonProperty("timestamp")
	public Instant getInstant() {
		return instant;
	}

	// received under "dt" as seconds from api
	@JsonProperty("dt")
	public void setInstant(long instant) {
		this.instant = Instant.ofEpochMilli(instant * 1000);
	}

	public double getTemperature() {
		return temperature;
	}

	@JsonProperty
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(double minTemperature) {
		this.minTemperature = minTemperature;
	}

	@JsonProperty("main")
	public void populateTemperatures(Map<String, Object> main) {
		setTemperature(Double.parseDouble(main.get("temp").toString()));
		setMinTemperature(Double.parseDouble(main.get("temp_min").toString()));
	}

	@Override
	public String toString() {
		return "WeatherDataPoint [instant=" + instant + ", temperature=" + temperature + ", minTemperature="
				+ minTemperature + "]";
	}

}
