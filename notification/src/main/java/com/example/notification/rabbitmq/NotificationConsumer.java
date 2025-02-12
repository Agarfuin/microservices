package com.example.notification.rabbitmq;

import com.example.clients.notification.NotificationRequestDto;
import com.example.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

  private final NotificationService notificationService;

  @RabbitListener(queues = "${rabbitmq.queues.notification}")
  public void consumer(NotificationRequestDto notificationRequestDto) {
    log.info("Consumed {} from queue", notificationRequestDto);
    notificationService.sendNotification(notificationRequestDto);
  }
}
