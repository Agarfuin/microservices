package com.example.notification;

import com.example.clients.notification.NotificationRequestDto;
import com.example.notification.entity.NotificationEntity;
import com.example.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

  private final NotificationRepository notificationRepository;

  public void sendNotification(NotificationRequestDto notificationRequestDto) {
    notificationRepository.save(
        NotificationEntity.builder()
            .toCustomerId(notificationRequestDto.getToCustomerId())
            .toCustomerEmail(notificationRequestDto.getToCustomerName())
            .sender("Microservices Example: Notification Microservice")
            .message(notificationRequestDto.getMessage())
            .build());
  }
}
