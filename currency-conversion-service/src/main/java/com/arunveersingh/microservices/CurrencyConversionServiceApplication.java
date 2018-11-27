package com.arunveersingh.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import brave.sampler.Sampler;

/**
 * 
 * @author arunveersingh9@gmail.com
 * ==== FEIGN is used ===
 * @see CurrencyExchangeServiceFeignProxy
 * @see CurrencyConversionController#convertCurrencyFeign(String, String, java.math.BigDecimal)
 *
 */
@SpringBootApplication
// Annotation is required to enable feign
@EnableFeignClients("com.arunveersingh.microservices")
@EnableDiscoveryClient
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}
	
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
