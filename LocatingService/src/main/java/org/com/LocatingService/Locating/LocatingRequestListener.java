package org.com.LocatingService.Locating;

import org.com.LocatingService.Kafka.Constants.KafkaListenerIds;
import org.com.LocatingService.Kafka.Constants.KafkaTopics;
import org.com.LocatingService.Request.Receive.LocatingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LocatingRequestListener
{
    private LocatingServiceProvider serviceProvider;

    @Autowired
    public LocatingRequestListener(LocatingServiceProvider serviceProvider)
    {
        this.serviceProvider = serviceProvider;
    }

    @KafkaListener(topics = KafkaTopics.LOCATING,
    id = KafkaListenerIds.LOCATING_REQUEST_KAFKA_LISTENER_ID,
    containerFactory = "locatingRequestConcurrentKafkaListenerContainerFactory")
    public void locatingRequestListener(LocatingRequest request)
    {
        System.out.println("Listener: " + request);
        serviceProvider.execute(request);
    }
}
