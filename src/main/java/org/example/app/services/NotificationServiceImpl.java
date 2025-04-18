package org.example.app.services;

import lombok.extern.slf4j.Slf4j;
import org.example.app.model.Notification;
import org.example.app.model.NotificationResponse;
import org.example.app.model.NotificationStatus;
import org.example.app.repository.NotificationRepository;
import org.example.app.web.NotificationRequest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {


    private final NotificationRepository notificationRepository;
    private final MailSender mailSender;

    public NotificationServiceImpl(NotificationRepository notificationRepository, MailSender mailSender) {
        this.notificationRepository = notificationRepository;
        this.mailSender = mailSender;
    }

    @Override
    public NotificationResponse sendNotification(NotificationRequest notificationRequest) {

        String subject = notificationRequest.getSubject();
        String body = notificationRequest.getBody();
        String email = notificationRequest.getUserEmail();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);

        Notification notification = Notification.builder()
                .subject(subject)
                .body(body)
                .createdOn(LocalDateTime.now())
                .userEmail(email)
                .build();


        try {
            mailSender.send(message);
            notification.setStatus(NotificationStatus.SUCCEEDED);
        } catch (Exception e) {
            notification.setStatus(NotificationStatus.FAILED);
            log.warn("There was an issue sending an email to %s.".formatted(email));
        }

        notificationRepository.save(notification);


        return NotificationResponse.builder()
                .subject(subject)
                .status(notification.getStatus())
                .createdOn(notification.getCreatedOn()).build();


    }
}
