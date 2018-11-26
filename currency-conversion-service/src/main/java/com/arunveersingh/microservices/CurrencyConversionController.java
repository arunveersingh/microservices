package com.arunveersingh.microservices;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonAnySetter;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	
	@Autowired
	private CurrencyExchangeServiceProxyWithRibbon proxyWithRibbon;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		//==== Code to invoke Currency Exchange Service =======
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
				"http://localhost:8001/currency-exchange/from/{from}/to/{to}", CurrencyConversionBean.class,
				uriVariables);

		CurrencyConversionBean entity = responseEntity.getBody();
		//======================================================

		return new CurrencyConversionBean(entity.getId(), from, to, entity.getConversionMultiple(), quantity,
				quantity.multiply(entity.getConversionMultiple()), entity.getPort());
	}
	/**
	 * This method used feign rest client to invoke the restful services.
	 * @param from
	 * @param to
	 * @param quantity
	 * @return
	 * @see CurrencyExchangeServiceProxy
	 */ 
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		//==== Code to invoke Currency Exchange Service Using Feign and Ribbon =======
		CurrencyConversionBean  entity = proxy.getCurrencyExchangeValue(from, to);
		//============================================================================

		return new CurrencyConversionBean(entity.getId(), from, to, entity.getConversionMultiple(), quantity,
				quantity.multiply(entity.getConversionMultiple()), entity.getPort());
	}
	
	/**
	 * This method used feign rest client and Ribbon to invoke the restful services.
	 * Try this method with multiple instance of currency-exchange-service to see load balancing working.
	 * @param from
	 * @param to
	 * @param quantity
	 * @return
	 * @see CurrencyExchangeServiceProxyWithRibbon
	 * @see application.properties for currency-exchange-service.ribbon.listOfServers
	 */ 
	@GetMapping("/currency-converter-feign-ribbon/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversionBean convertCurrencyFeignRibbon(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		//==== Code to invoke Currency Exchange Service Using Feign =======
		CurrencyConversionBean  entity = proxyWithRibbon.getCurrencyExchangeValue(from, to);
		//=================================================================

		return new CurrencyConversionBean(entity.getId(), from, to, entity.getConversionMultiple(), quantity,
				quantity.multiply(entity.getConversionMultiple()), entity.getPort());
	}

}
