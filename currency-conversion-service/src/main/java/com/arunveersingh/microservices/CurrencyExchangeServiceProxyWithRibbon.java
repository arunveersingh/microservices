package com.arunveersingh.microservices;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * This is a feign proxy to invoke restful services using Ribbon for load balancing.
 * Since Ribbon is handling the load balancing {@link FeignClient#url()} not required.
 * 
 * @author arunveersingh9@gmail.com
 * @see CurrencyConversionController#convertCurrencyFeign(String, String,
 *      java.math.BigDecimal) for usage of feign proxy to invoke the rest
 *      client.
 *
 */
//@FeignClient(name = "currency-exchange-service", url = "localhost:8001")
@FeignClient(name = "currency-exchange-service")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxyWithRibbon {

	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean getCurrencyExchangeValue(@PathVariable("from") String from,
			@PathVariable("to") String to);

}