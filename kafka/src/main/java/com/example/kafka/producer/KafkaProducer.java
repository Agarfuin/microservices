package com.example.kafka.producer;

import com.example.clients.notification.NotificationRequestDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaProducer {

  private final KafkaTemplate<String, NotificationRequestDto> kafkaTemplate;

  public void sendMessage(String topic, NotificationRequestDto payload) {
    log.info("Sending message to topic {}. Payload: {}", topic, payload);
    kafkaTemplate.send(topic, payload);
    log.info("Sent message to topic {}. Payload: {}", topic, payload);
  }
}
