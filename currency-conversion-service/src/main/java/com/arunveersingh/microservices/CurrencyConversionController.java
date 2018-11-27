package com.arunveersingh.microservices;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * This class has exposed rest end points for the currency-conversion service.
 * This class demonstrate multiple ways of creating rest clients for
 * communication with restful service e.g. RestTemplate, Feign, Feign + Ribbon.
 * 
 * @author arunveersingh9@gmail.com
 *
 */
@RestController
public class CurrencyConversionController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CurrencyExchangeServiceFeignProxy feignProxy;

	@Autowired
	private CurrencyExchangeServiceFeignProxyWithRibbon feignProxyWithRibbon;

	@Autowired
	private CurrencyExchangeServiceFeignProxyThroughZuulWithRibbon feignProxyThroughZuulWithRibbon;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		// ==== Code to invoke Currency Exchange Service =======
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8001/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);

		CurrencyConversionBean entity = responseEntity.getBody();
		// ======================================================

		return new CurrencyConversionBean(entity.getId(), from, to, entity.getConversionMultiple(), quantity,
				quantity.multiply(entity.getConversionMultiple()), entity.getPort());
	}

	/**
	 * This method used feign rest client to invoke the restful services.
	 * 
	 * @param from
	 * @param to
	 * @param quantity
	 * @return
	 * @see CurrencyExchangeServiceFeignProxy
	 */
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		// ==== Code to invoke Currency Exchange Service Using Feign and Ribbon =======
		CurrencyConversionBean entity = feignProxy.getCurrencyExchangeValue(from, to);
		// ============================================================================

		return new CurrencyConversionBean(entity.getId(), from, to, entity.getConversionMultiple(), quantity,
				quantity.multiply(entity.getConversionMultiple()), entity.getPort());
	}

	/**
	 * This method used feign rest client and Ribbon to invoke the restful services.
	 * Try this method with multiple instance of currency-exchange-service to see
	 * load balancing working.
	 * 
	 * @param from
	 * @param to
	 * @param quantity
	 * @return
	 * @see CurrencyExchangeServiceFeignProxyWithRibbon
	 * @see application.properties for
	 *      currency-exchange-service.ribbon.listOfServers
	 */
	@GetMapping("/currency-converter-feign-ribbon/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeignRibbon(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		// ==== Code to invoke Currency Exchange Service Using Feign =======
		CurrencyConversionBean entity = feignProxyWithRibbon.getCurrencyExchangeValue(from, to);
		// =================================================================

		return new CurrencyConversionBean(entity.getId(), from, to, entity.getConversionMultiple(), quantity,
				quantity.multiply(entity.getConversionMultiple()), entity.getPort());
	}

	/**
	 * This method used feign rest client and Ribbon to invoke the restful services.
	 * Try this method with multiple instance of currency-exchange-service to see
	 * load balancing working.
	 * 
	 * URI
	 * http://localhost:8100/currency-converter-feign-ribbon-zuul/from/USD/to/INR/quantity/1000
	 * 
	 * @param from
	 * @param to
	 * @param quantity
	 * @return
	 * @see CurrencyExchangeServiceFeignProxyWithRibbon
	 * @see application.properties for
	 *      currency-exchange-service.ribbon.listOfServers
	 * @see CurrencyExchangeServiceFeignProxyThroughZuulWithRibbon
	 */
	@GetMapping("/currency-converter-feign-ribbon-zuul/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeignZuulRibbon(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		// ==== Code to invoke Currency Exchange Service Using Feign =======
		CurrencyConversionBean entity = feignProxyThroughZuulWithRibbon.getCurrencyExchangeValue(from, to);
		// =================================================================

		return new CurrencyConversionBean(entity.getId(), from, to, entity.getConversionMultiple(), quantity,
				quantity.multiply(entity.getConversionMultiple()), entity.getPort());
	}

	/**
	 * This method used feign rest client and Ribbon to invoke the restful services.
	 * Try this method with multiple instance of currency-exchange-service to see
	 * load balancing working.
	 * 
	 * URI
	 * http://localhost:8765/currency-conversion-service/currency-converter-feign-ribbon-zuul/from/USD/to/INR/quantity/1000
	 * 
	 * @param from
	 * @param to
	 * @param quantity
	 * @return
	 * @see CurrencyExchangeServiceFeignProxyWithRibbon
	 * @see application.properties for
	 *      currency-exchange-service.ribbon.listOfServers
	 * @see CurrencyExchangeServiceFeignProxyThroughZuulWithRibbon
	 */
	@GetMapping("/currency-conversion-service/currency-converter-feign-ribbon-zuul/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyInvokedThroughZuulUsingFurtherFeignZuulRibbon(
			@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
		

		// ==== Code to invoke Currency Exchange Service Using Feign =======
		CurrencyConversionBean entity = feignProxyThroughZuulWithRibbon.getCurrencyExchangeValue(from, to);
		// =================================================================
		
		logger.info("{}", entity);

		return new CurrencyConversionBean(entity.getId(), from, to, entity.getConversionMultiple(), quantity,
				quantity.multiply(entity.getConversionMultiple()), entity.getPort());
	}

}
