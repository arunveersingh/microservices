package com.arunveersingh.microservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * To invoke this service through Zull API gateway use URL ->
 * http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR
 * instead of http://localhost:8001/currency-exchange/from/USD/to/INR
 * 
 * In URL
 * http://localhost:8765/currency-exchange-service/currency-exchange/from/USD/to/INR,
 * 8765 is port of Zull Server and currency-exchange-service is the name with
 * which service is registered in Eureka Naming Server.
 * 
 * @author arunveersingh9@gmail.com
 *
 */
@RestController
public class CurrencyExchangeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Environment environment;

	@Autowired
	private CurrencyExchangeValueRepository repo;

	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyExchangeValue getCurrencyExchangeValue(@PathVariable String from, @PathVariable String to) {

		// CurrencyExchangeValue currencyExchangeValue = new CurrencyExchangeValue(1L,
		// from, to, BigDecimal.valueOf(75));

		CurrencyExchangeValue currencyExchangeValue = repo.findByFromAndTo(from, to);

		currencyExchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		logger.info("{}", currencyExchangeValue);

		return currencyExchangeValue;

	}

}
