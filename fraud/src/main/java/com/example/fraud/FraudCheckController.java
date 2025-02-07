package com.example.fraud;

import com.example.fraud.dto.FraudCheckResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fraud-check")
@RequiredArgsConstructor
@Slf4j
public class FraudCheckController {
  private final FraudCheckService fraudCheckService;

  @GetMapping("/{customerId}")
  public ResponseEntity<FraudCheckResponseDto> isFraudster(
      @PathVariable("customerId") Long customerId) {
    Boolean isFraudulentCustomer = fraudCheckService.isFraudulentCustomer(customerId);
    log.info("fraud check request for customer id: {}", customerId);

    return ResponseEntity.status(HttpStatus.OK)
        .body(
            FraudCheckResponseDto.builder()
                .success(true)
                .isFraudster(isFraudulentCustomer)
                .build());
  }
}
