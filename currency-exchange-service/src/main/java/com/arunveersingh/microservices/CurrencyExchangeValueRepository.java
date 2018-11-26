package com.arunveersingh.microservices;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeValueRepository extends JpaRepository<CurrencyExchangeValue, Long>{

	CurrencyExchangeValue findByFromAndTo(String from, String to);

}
