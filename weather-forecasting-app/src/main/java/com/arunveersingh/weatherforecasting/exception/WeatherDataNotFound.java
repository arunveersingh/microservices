package com.arunveersingh.weatherforecasting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom Runtime exception. Though Spring Boot do a lot of things to handle the
 * handle error response but in case a detailed exception is required the we can
 * use such exceptions. e.g. When service call is successful and not data is
 * returned because of lack of availability there is a possibility of HTTP
 * response code 200 OK, which does not convey the right message, so here we are
 * setting NOT_FOUND(404, "Not Found")
 * 
 * @author arunveersingh9@gmail.com
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class WeatherDataNotFound extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public WeatherDataNotFound(String message) {
	super(message);
    }

}
