package com.unreallabss.carrent.service;


import com.unreallabss.carrent.domain.NotificationMassage;

public interface FirebaseMessagingService {

    String sendNotificationByToken(NotificationMassage notificationMassage);
}
