package org.com.NotificationService.Services;

import org.com.NotificationService.Kafka.Constants.KafkaListenerIds;
import org.com.NotificationService.Kafka.Constants.KafkaTopics;
import org.com.NotificationService.Requests.NotificationWrapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationRequestListener
{

    @KafkaListener(id = KafkaListenerIds.NOTIFICATION_REQUEST_KAFKA_LISTENER_ID,
    topics = KafkaTopics.NOTIFICATION,
    containerFactory = "notificationWrapperConcurrentKafkaListenerContainerFactory")
    public void notificationRequestListen(NotificationWrapper wrapper)
    {
        System.out.println("notification: " + wrapper);

    }
}