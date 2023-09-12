package org.com.BroadcastService.Services;

import org.com.BroadcastService.Kafka.Constants.KafkaListenerIds;
import org.com.BroadcastService.Kafka.Constants.KafkaTopics;
import org.com.BroadcastService.Requests.Requests.Receive.BroadcastRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BroadcastRequestListener
{
    private BroadcastServiceProvider serviceProvider;

    @Autowired
    public BroadcastRequestListener(BroadcastServiceProvider provider)
    {
        this.serviceProvider = provider;
    }

    @KafkaListener(id = KafkaListenerIds.BROADCAST_REQUEST_KAFKA_LISTENER_ID,
    topics = KafkaTopics.BROADCAST,
    containerFactory = "broadcastRequestConcurrentKafkaListenerContainerFactory")
    public void listen(BroadcastRequest request)
    {
        System.out.println(request);
        //TODO: broadcast to drivers
        serviceProvider.execute(request);
    }
}
