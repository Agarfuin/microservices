package com.example.customer;

import com.example.amqp.RabbitMQMessageProducer;
import com.example.clients.fraud.FraudCheckResponseDto;
import com.example.clients.fraud.FraudClient;
import com.example.clients.notification.NotificationRequestDto;
import com.example.customer.entity.CustomerEntity;
import com.example.customer.dto.CreateCustomerRequestDto;
import com.example.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
  private final CustomerRepository customerRepository;
  private final FraudClient fraudClient;
  private final RabbitMQMessageProducer rabbitMQMessageProducer;

  public void registerCustomer(CreateCustomerRequestDto createCustomerRequestDto) {
    CustomerEntity customerEntity =
        CustomerEntity.builder()
            .firstName(createCustomerRequestDto.getFirstName())
            .lastName(createCustomerRequestDto.getLastName())
            .email(createCustomerRequestDto.getEmail())
            .build();
    customerRepository.saveAndFlush(customerEntity);

    FraudCheckResponseDto fraudCheckResponseDto = fraudClient.isFraudster(customerEntity.getId());

    if (Boolean.TRUE.equals(fraudCheckResponseDto.getIsFraudster())) {
      throw new IllegalStateException(
          String.format("Customer with id %d is Fraudster", customerEntity.getId()));
    }

    NotificationRequestDto notificationRequestDto =
        NotificationRequestDto.builder()
            .toCustomerId(customerEntity.getId())
            .toCustomerName(customerEntity.getEmail())
            .message(
                String.format(
                    "Hi %s!%nWelcome to Microservices, in order to activate your account please click the link below:%n%nHERE IS THE LINK...",
                    customerEntity.getFirstName()))
            .build();

    rabbitMQMessageProducer.publish(
        notificationRequestDto, "internal.exchange", "internal.notification.routing-key");

    // TODO: Validations...
  }
}
