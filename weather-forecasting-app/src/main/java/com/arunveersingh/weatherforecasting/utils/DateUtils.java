package com.arunveersingh.weatherforecasting.utils;

import java.time.LocalDate;

/**
 * 
 * @author arunveersingh9@gmail.com 
 *
 */
public class DateUtils {
	
	public static LocalDate getNow() {
		return LocalDate.now();
	}
	public static LocalDate getLocalDateForTomorrow(LocalDate today) {
		return today.plusDays(1);
	}

}
