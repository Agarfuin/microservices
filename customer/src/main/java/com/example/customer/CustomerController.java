package com.example.customer;

import com.example.customer.dto.CreateCustomerRequestDto;
import com.example.customer.dto.CreateCustomerResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping("/new")
  public ResponseEntity<CreateCustomerResponseDto> registerCustomer(
      @RequestBody CreateCustomerRequestDto createCustomerRequestDto) {
    customerService.registerCustomer(createCustomerRequestDto);
    log.info("new customer registration {}", createCustomerRequestDto);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(
            CreateCustomerResponseDto.builder().success(true).message("customer created").build());
  }
}
