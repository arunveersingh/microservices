package com.arunveersingh.weatherforecasting.datamodel;

/**
 * POJO to contain City Info. Jackson leverage it to convert the json to Java
 * and vice versa.
 * 
 * @author arunveersingh9@gmail.com
 *
 */
public class City {
    private float id;
    private String name;
    private Coordinate coordinate;
    private String country;

    public float getId() {
	return id;
    }

    public String getName() {
	return name;
    }

    public Coordinate getCoord() {
	return coordinate;
    }

    public String getCountry() {
	return country;
    }

    public void setId(float id) {
	this.id = id;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setCoord(Coordinate coordObject) {
	this.coordinate = coordObject;
    }

    public void setCountry(String country) {
	this.country = country;
    }

    @Override
    public String toString() {
	return "City [id=" + id + ", name=" + name + ", coordinate="
		+ coordinate + ", country=" + country + "]";
    }
}
