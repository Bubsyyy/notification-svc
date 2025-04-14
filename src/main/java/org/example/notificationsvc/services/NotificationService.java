package org.example.notificationsvc.services;

import org.example.notificationsvc.model.NotificationResponse;
import org.example.notificationsvc.web.NotificationRequest;

public interface NotificationService {

    NotificationResponse sendNotification(NotificationRequest notificationRequest);

}
