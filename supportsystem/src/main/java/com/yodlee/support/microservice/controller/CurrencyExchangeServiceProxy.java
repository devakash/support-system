package com.yodlee.support.microservice.controller;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.yodlee.support.microservice.entity.ToolInfo;

@FeignClient(name="currency-conversion-service")
@RibbonClient(name = "currency-conversion-service")
public interface CurrencyExchangeServiceProxy {
	
  @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/75")
  public ToolInfo retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}