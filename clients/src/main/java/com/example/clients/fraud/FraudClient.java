package com.example.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "fraud")
public interface FraudClient {

  @GetMapping(path = "/api/v1/fraud-check/{customerId}")
  FraudCheckResponseDto isFraudster(@PathVariable("customerId") Long customerId);
}
