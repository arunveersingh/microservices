package com.arunveersingh.weatherforecasting.appprops;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This class initializes the reference variables with values from
 * application.properties.
 * 
 * Best Practice: Property in application properties file should be a
 * combination of value passed to {@link ConfigurationProperties} appended with
 * DOT (".") and the reference variable name. e.g.
 * weather-forecasting-app.countryCode, weather-forecasting-app.uri. Even after
 * naming the properties in above manner it may result in initialization issues.
 * I am skipping the deep dive and just leaving a hint that Spring matches the
 * method name to initialize the reference variables.
 * 
 * Some times too much text confuses :) and I understand these lighter notes
 * comments are bad practice, but since it is just a demo app so taking the
 * leverage of writing the things.
 * 
 * @author arunveersingh9@gmail.com
 *
 */
@Component
@ConfigurationProperties(value = "weather-forecasting-app")
public class ApplicationProperties {

	private String countryCode, uri, secret;

	public String getCountryCode() {
		return countryCode;
	}

	public String getUri() {
		return uri;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
