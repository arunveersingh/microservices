package com.arunveersingh.weatherforecasting;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.arunveersingh.weatherforecasting.appprops.ApplicationProperties;
import com.arunveersingh.weatherforecasting.datamodel.City;
import com.arunveersingh.weatherforecasting.datamodel.WeatherData;
import com.arunveersingh.weatherforecasting.service.AccessOpenWeatherMapAPIService;
import com.arunveersingh.weatherforecasting.utils.DateUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherForecastingAppApplicationTests {

    @Mock
    AccessOpenWeatherMapAPIService openWeatherServiceMock;

    @Autowired
    AccessOpenWeatherMapAPIService openWeatherService;
    
    @Autowired
    ApplicationProperties props;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testService() {

	WeatherData weatherDataResponse = openWeatherService
		.invokeOpenWeather("94040");
	Assert.assertNotNull(weatherDataResponse);
	Assert.assertTrue(weatherDataResponse.getCityObject().getName()
		.equals("Mountain View"));
	Assert.assertEquals("US",
		weatherDataResponse.getCityObject().getCountry());
	Assert.assertNotNull(weatherDataResponse.getList());
	Assert.assertNotEquals(9, weatherDataResponse.getList().size());
	Assert.assertEquals(8, weatherDataResponse.getList().size());
	
	Assert.assertNotNull(weatherDataResponse.getMinTemperatureOfTheDay());

    }
    

    // Not much relevant in out scenario
    @Test
    public void testStubbing() {

	WeatherData weatherData = new WeatherData();
	City city = new City();
	city.setName("Noida");
	weatherData.setCity(city);

	when(openWeatherServiceMock.invokeOpenWeather(Mockito.anyString()))
		.thenReturn(weatherData);

	WeatherData weatherDataResponse = openWeatherServiceMock
		.invokeOpenWeather("12345");

	Assert.assertTrue(weatherDataResponse.getCityObject().getName()
		.equals(city.getName()));

	verify(openWeatherServiceMock, times(1)).invokeOpenWeather("12345");
    }
    
    @Test
    public void testPropertiesAreLoadedCorrectly(){
	Assert.assertEquals("us", props.getCountryCode());
    }

    @Test
    public void testDateUtils() {
	LocalDate today = LocalDate.now(); 
	LocalDate tomorrow = DateUtils.getLocalDateForTomorrow(today);
	
	Assert.assertTrue(tomorrow.isAfter(today));
    }
    
}
