package com.adek.microservices.currencyexchangeservice;

import java.math.BigDecimal;import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

	@Autowired
	private Environment environtment;
	
	@Autowired
	ExchangeValueRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from,@PathVariable String to) {
		
		ExchangeValue exchangeValue= repository.findByFromAndTo(from, to);
		//ExchangeValue exchangeValue = new ExchangeValue(1L,from,to,BigDecimal.valueOf(10));
		//set dinamic port
		exchangeValue.setPort(
				Integer.parseInt(environtment.getProperty("local.server.port")));
		return exchangeValue;
	}
}
