package com.arunveersingh.microservices;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * This is a feign proxy to invoke restful services.
 * 
 * @author arunveersingh9@gmail.com
 * @see CurrencyConversionController#convertCurrencyFeign(String, String,
 *      java.math.BigDecimal) for usage of feign proxy to invoke the rest
 *      client.
 *
 */
@FeignClient(name = "currency-exchange-server", url = "localhost:8001")
@RibbonClient
public interface CurrencyExchangeServiceProxy {

	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean getCurrencyExchangeValue(@PathVariable("from") String from,
			@PathVariable("to") String to);

}
