package com.arunveersingh.weatherforecasting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arunveersingh.weatherforecasting.appprops.ApplicationProperties;
import com.arunveersingh.weatherforecasting.controllers.WeatherServiceController;

/**
 * This application helps user in pulling the weather details from
 * http://api.openweathermap.org
 * 
 * Just to give an heads up to the person having a look at code, this is not
 * very much production ready code, it can be optimized a lot.
 * 
 * @author arunveersingh9@gmail.com
 * 
 * @see {@link WeatherServiceController} for the rest endpoints this application is
 *      exposing. ALternatively, I have done minimal integration of swagger so
 *      Swagger UI can also assist in having a look at the rest end points exposed.
 * @see <a href="http://localhost:8080/swagger-ui.html">Swagger UI</a> 
 * 
 * @see {@link ApplicationProperties} for configuring the properties.
 *
 */
@SpringBootApplication
public class WeatherForecastingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherForecastingAppApplication.class, args);
	}
}
