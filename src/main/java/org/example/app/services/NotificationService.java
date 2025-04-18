package org.example.app.services;

import org.example.app.model.NotificationResponse;
import org.example.app.web.NotificationRequest;

public interface NotificationService {

    NotificationResponse sendNotification(NotificationRequest notificationRequest);

}
