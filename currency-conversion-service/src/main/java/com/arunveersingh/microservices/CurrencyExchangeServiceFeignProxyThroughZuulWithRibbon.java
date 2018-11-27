package com.arunveersingh.microservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * This is a feign proxy to invoke restful services using Ribbon for load
 * balancing. Since Ribbon is handling the load balancing
 * {@link FeignClient#url()} not required with {@link FeignClient} annotation.
 * e.g. @FeignClient(name = "currency-exchange-service", url = "localhost:8001")
 * vs @FeignClient(name = "currency-exchange-service")
 * 
 * In case Eureka Naming server is not configured Ribbon use application
 * property "currency-exchange-service.ribbon.listOfServers". e.g.
 * currency-exchange-service.ribbon.listOfServers=http://localhost:8000,
 * http://localhost:8001 in application.properties. After naming server
 * configuration Ribbon will not pick configuration from application.properties.
 * 
 * @see netflix-eureka-naming-server for more details on how to configure Naming
 *      Server.
 * 
 * @author arunveersingh9@gmail.com
 * @see CurrencyConversionController#convertCurrencyFeign(String, String,
 *      java.math.BigDecimal) for usage of feign proxy to invoke the rest
 *      client.
 *
 */
// @FeignClient(name = "currency-exchange-service", url = "localhost:8001")
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceFeignProxyThroughZuulWithRibbon {
	

	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean getCurrencyExchangeValue(@PathVariable("from") String from,
			@PathVariable("to") String to);

}