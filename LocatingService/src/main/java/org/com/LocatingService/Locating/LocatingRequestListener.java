package org.com.LocatingService.Locating;

import org.com.LocatingService.Kafka.Constants.KafkaListenerIds;
import org.com.LocatingService.Kafka.Constants.KafkaTopics;
import org.com.LocatingService.Request.LocatingRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LocatingRequestListener
{
    @KafkaListener(topics = KafkaTopics.LOCATING,
    id = KafkaListenerIds.LOCATING_REQUEST_KAFKA_LISTENER_ID,
    containerFactory = "locatingRequestConcurrentKafkaListenerContainerFactory")
    public void locatingRequestListener(LocatingRequest request)
    {
        System.out.println(request);
        //TODO: using LocatingServiceProvider to get the result of Geocode API and Direction API
        //TODO: push the result into Contact
    }
}
