package com.arunveersingh.microservices;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Major intent of this class is to demonstrate the request through API Gateway
 * (Zuul Server)
 * 
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
 * Config required for the request to pass through Zull 1. Feign Client should
 * annnotation should be @FeignClient(name = "netflix-zuul-api-gateway-server")
 * instead @FeignClient(name = "currency-exchange-service")
 * 2. @GetMapping("currency-exchange/from/{from}/to/{to}") should be changed
 * to @GetMapping("currency-exchange-service/currency-exchange/from/{from}/to/{to}"),
 * "currency-exchange-service" is the name of the service registered on Naming
 * Server
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
@FeignClient(name = "currency-exchange-service")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceFeignProxyWithRibbon {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversionBean getCurrencyExchangeValue(@PathVariable("from") String from,
			@PathVariable("to") String to);

}