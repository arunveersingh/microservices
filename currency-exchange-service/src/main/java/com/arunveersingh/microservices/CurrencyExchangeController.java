package com.arunveersingh.microservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeValueRepository repo;

	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyExchangeValue getCurrencyExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		//CurrencyExchangeValue currencyExchangeValue = new CurrencyExchangeValue(1L, from, to, BigDecimal.valueOf(75));
		
		CurrencyExchangeValue currencyExchangeValue = repo.findByFromAndTo(from, to);
		
		currencyExchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		return currencyExchangeValue;

	}

}
