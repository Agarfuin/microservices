package com.example.notification.kafka;

import com.example.clients.notification.NotificationRequestDto;
import com.example.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaListeners {

  private final NotificationService notificationService;

  @KafkaListener(topics = "example", groupId = "groupId")
  public void listener(NotificationRequestDto notificationRequestDto) {
    log.info("Listener received: {}", notificationRequestDto);
    notificationService.sendNotification(notificationRequestDto);
  }
}
