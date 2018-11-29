package com.arunveersingh.weatherforecasting.datamodel;

/**
 * POJO to contain Coordinate Info. Jackson leverage it to convert the json to Java
 * and vice versa.
 * @author arunveersingh9@gmail.com
 *
 */
public class Coordinate {
	private float lat;
	private float lon;

	public float getLat() {
	  return lat;
	}

	public float getLon() {
	  return lon;
	}

	public void setLat( float lat ) {
	  this.lat = lat;
	}

	public void setLon( float lon ) {
	  this.lon = lon;
	}
}
