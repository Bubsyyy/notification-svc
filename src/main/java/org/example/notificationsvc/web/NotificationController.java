package org.example.notificationsvc.web;

import org.example.notificationsvc.model.Notification;
import org.example.notificationsvc.model.NotificationResponse;
import org.example.notificationsvc.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @PostMapping
    public ResponseEntity<NotificationResponse> sendNotification(@RequestBody NotificationRequest notificationRequest) {

        NotificationResponse response = notificationService.sendNotification(notificationRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
