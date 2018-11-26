package com.arunveersingh.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 
 * @author arunveersingh9@gmail.com
 * ==== FEIGN is used ===
 * @see CurrencyExchangeServiceProxy
 * @see CurrencyConversionController#convertCurrencyFeign(String, String, java.math.BigDecimal)
 *
 */
@SpringBootApplication
// Annotation is required to enable feign
@EnableFeignClients("com.arunveersingh.microservices")
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}
}
