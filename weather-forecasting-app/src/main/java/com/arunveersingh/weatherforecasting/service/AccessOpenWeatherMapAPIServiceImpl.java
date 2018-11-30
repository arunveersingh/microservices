package com.arunveersingh.weatherforecasting.service;

import static com.arunveersingh.weatherforecasting.utils.DateUtils.getLocalDateForTomorrow;
import static com.arunveersingh.weatherforecasting.utils.DateUtils.getNow;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.arunveersingh.weatherforecasting.appprops.ApplicationProperties;
import com.arunveersingh.weatherforecasting.datamodel.WeatherData;
import com.arunveersingh.weatherforecasting.datamodel.WeatherDataPoint;
import com.arunveersingh.weatherforecasting.exception.WeatherDataNotFound;

@Component
public class AccessOpenWeatherMapAPIServiceImpl
	implements AccessOpenWeatherMapAPIService {

    @Autowired
    ApplicationProperties props;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public WeatherData invokeOpenWeather(String zipcode) {
	LocalDate today = getNow();

	logger.info("Weather forecast requested for Zip Code {}", zipcode);

	Map<String, String> uriVariables = new HashMap<>();
	uriVariables.put("zipcode", zipcode);
	uriVariables.put("country", props.getCountryCode());
	uriVariables.put("appid", props.getSecret());

	ResponseEntity<WeatherData> responseEntity = new RestTemplate()
		.getForEntity(props.getUri(), WeatherData.class, uriVariables);

	if (responseEntity == null || responseEntity.getBody() == null) {
	    logger.error(
		    "Weather forecast requested for zipcode {} is having issues,"
			    + " response from openweathermap is {} ",
		    zipcode, responseEntity);
	    new WeatherDataNotFound(
		    "Weather Data is not avaialable right now, please try again"
			    + " after some time.");
	}

	WeatherData weatherDataResponse = ensureOnlyNextDayData(responseEntity,
		today);
	weatherDataResponse = populateMinTempOfTheDay(weatherDataResponse);
	return weatherDataResponse;
    }

    /**
     * Helper method. This method evaluates the minimum data of the day by
     * processing the minTemerature values received in the response from
     * openweathermap.
     * 
     * @param weatherDataResponse
     * @return {@link WeatherData} after setting the min temp value
     *         {@link WeatherData#setMinTemperatureOfTheDay(Double)}
     */
    private WeatherData populateMinTempOfTheDay(
	    WeatherData weatherDataResponse) {

	// sorting
	weatherDataResponse.getList().sort(Comparator
		.comparingDouble(WeatherDataPoint::getMinTemperature));

	// populating minimum temperature for the day
	weatherDataResponse.setMinTemperatureOfTheDay(
		weatherDataResponse.getList().get(0).getMinTemperature());
	
	// Coolest Interval of the day
	weatherDataResponse.setCoolestDataPoint(weatherDataResponse.getList().get(0));

	return weatherDataResponse;
    }

    /**
     * Helper Method. The openweathermap API returns data of 5 days but we need
     * data only for the first day. We could have picked up first records but
     * that logic will fail in case API changes the interval duration, that's
     * the reason we took the date related approach.
     * 
     * @param responseEntity
     * @param today
     * @return
     */
    private WeatherData ensureOnlyNextDayData(
	    ResponseEntity<WeatherData> responseEntity, LocalDate today) {

	LocalDate nextDay = getLocalDateForTomorrow(today);

	List<WeatherDataPoint> filteredList = responseEntity.getBody().getList()
		.parallelStream().filter(dataPoint -> {
		    return LocalDate
			    .from(dataPoint.getInstant()
				    .atZone(ZoneId.of("GMT+0")))
			    .equals(nextDay);
		}).collect(Collectors.toList());

	responseEntity.getBody().getList().clear();
	responseEntity.getBody().getList().addAll(filteredList);

	return responseEntity.getBody();
    }

}
