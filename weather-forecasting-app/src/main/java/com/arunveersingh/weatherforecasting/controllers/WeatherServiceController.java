package com.arunveersingh.weatherforecasting.controllers;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import com.arunveersingh.weatherforecasting.datamodel.WeatherData;
import com.arunveersingh.weatherforecasting.service.AccessOpenWeatherMapAPIService;

/**
 * This controller exposes the rest end points which further interacts with
 * openweathermap.
 * {@link WeatherServiceController#weatherDataForNextDay(String)} is end point
 * to be used by the end user.
 * 
 * @see <a href= "http://localhost:8080/weatherDataForNextDay/94040">Sample hit
 *      to the service</a>
 * 
 *      Note: This controller has methods other than
 *      WeatherServiceController#fiveDaysThreeHoursWeatherData(String) but the
 *      only intent was to show case the concept of service version-ing using
 *      headers.
 * 
 * 
 * @author arunveersingh9@gmail.com
 *
 */
@RestController
public class WeatherServiceController {

    @Autowired
    AccessOpenWeatherMapAPIService openWeatherService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Method returns the weather data for next day and minimum temperature for
     * that day.
     * 
     * @param zipcode
     * @return {@link WeatherData}
     */
    @GetMapping(value = "/weatherDataForNextDay/{zipcode}", produces = {
	    MediaType.APPLICATION_JSON_VALUE })
    public WeatherData weatherDataForNextDay(@PathVariable String zipcode) {
	WeatherData weatherData = openWeatherService.invokeOpenWeather(zipcode);
	logger.info("Response : {}", weatherData);
	return weatherData;
    }

    /**
     * CAUTION: DO not use this method
     * =============> This method is present here JUST to demonstrate the usage
     * of headers for versiong of the service. <================
     * {@link WeatherServiceController#fiveDaysThreeHoursWeatherDataWithHeaderVersioning1(String)}
     * and
     * {@link WeatherServiceController#fiveDaysThreeHoursWeatherDataWithHeaderVersioning2(String)}
     * has the same rest endpoint but there is a difference in headers. For
     * first it is X-API-VERSION=1 and for later X-API-VERSION=2
     * 
     * IDEALLY, major code of these methods should be moved to Service class but
     * since the INTENT is just to demonstrate versioning, so leaving them here
     * for now.
     * 
     * @param zipcode
     * @return {@link WeatherData}
     */
    @GetMapping(value = "/fiveDaysThreeHoursWeatherDataWithHeaderVersioning/{zipcode}", headers = "X-API-VERSION=1")
    public WeatherData fiveDaysThreeHoursWeatherDataWithHeaderVersioning1(
	    @PathVariable String zipcode) {

	URI url = new UriTemplate(
		"https://samples.openweathermap.org/data/2.5/forecast?"
			+ "q={zipcode},{countrycode}&appid={key}")
				.expand(zipcode, "us", "arunveersingh9");

	RequestEntity<?> request = RequestEntity.get(url)
		.accept(MediaType.APPLICATION_JSON).build();

	ResponseEntity<WeatherData> exchange = new RestTemplate()
		.exchange(request, WeatherData.class);

	return exchange.getBody();
    }

    /**
     * CAUTION: DO not use this method
     * =============> This method is present here JUST to demonstrate the usage
     * of headers for versiong of the service. <================
     * {@link WeatherServiceController#fiveDaysThreeHoursWeatherDataWithHeaderVersioning1(String)}
     * and
     * {@link WeatherServiceController#fiveDaysThreeHoursWeatherDataWithHeaderVersioning2(String)}
     * has the same rest endpoint but there is a difference in headers. For
     * first it is X-API-VERSION=1 and for later X-API-VERSION=2
     * 
     * IDEALLY, major code of these methods should be moved to Service class but
     * since the INTENT is just to demonstrate versioning, so leaving them here
     * for now.
     * 
     * @param zipcode
     * @return {@link WeatherData}
     */
    @GetMapping(value = "/fiveDaysThreeHoursWeatherDataWithHeaderVersioning/{zipcode}", headers = "X-API-VERSION=2")
    public WeatherData fiveDaysThreeHoursWeatherDataWithHeaderVersioning2(
	    @PathVariable String zipcode) {

	URI url = new UriTemplate(
		"https://samples.openweathermap.org/data/2.5/forecast?q={zipcode},{countrycode}&appid={key}")
			.expand(zipcode, "us", "arunveersingh9");

	RequestEntity<?> request = RequestEntity.get(url)
		.accept(MediaType.APPLICATION_JSON).build();

	ResponseEntity<WeatherData> exchange = new RestTemplate()
		.exchange(request, WeatherData.class);

	return exchange.getBody();
    }

}
