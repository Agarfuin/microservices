package com.example.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification", url = "${clients.notification.url}")
public interface NotificationClient {

  @PostMapping("/api/v1/notification")
  void sendNotification(@RequestBody NotificationRequestDto notificationRequestDto);
}
