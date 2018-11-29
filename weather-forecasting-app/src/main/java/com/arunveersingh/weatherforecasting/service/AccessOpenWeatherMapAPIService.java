package com.arunveersingh.weatherforecasting.service;

import com.arunveersingh.weatherforecasting.datamodel.WeatherData;

public interface AccessOpenWeatherMapAPIService {
    
    WeatherData invokeOpenWeather(String zipcode);

}
