package com.example.fraud;

import com.example.fraud.entity.FraudCheckEntity;
import com.example.fraud.repository.FraudCheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FraudCheckService {
  private final FraudCheckRepository fraudCheckRepository;

  public Boolean isFraudulentCustomer(Long customerId) {
    fraudCheckRepository.save(
        FraudCheckEntity.builder().customerId(customerId).isFraudster(false).build());
    return false;
  }
}
