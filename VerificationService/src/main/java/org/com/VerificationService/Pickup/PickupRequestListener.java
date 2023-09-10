package org.com.VerificationService.Pickup;


import org.com.VerificationService.Kafka.Constants.KafkaListenerIds;
import org.com.VerificationService.Kafka.Constants.KafkaTopics;
import org.com.VerificationService.Request.RequestStrategy.RequestConverterStrategyProvider;
import org.com.VerificationService.Request.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PickupRequestListener
{
    private PickupServiceProvider pickupServiceProvider;

    @Autowired
    public PickupRequestListener(PickupServiceProvider service)
    {
        pickupServiceProvider = service;
    }

    //receive PickupRequest message from topic "data_room"
    @KafkaListener(topics = KafkaTopics.DATA_ROOM,
    id = KafkaListenerIds.DATA_ROOM_REQUEST_WRAPPER_LISTENER_ID,
    containerFactory = "requestWrapperConcurrentKafkaListenerContainerFactory")
    public void dataRoomListener(RequestWrapper requestWrapper)
    {
        if(requestWrapper == null)
        {
            System.out.println("dataRoomListener: null");
            return;
        }
        System.out.println(requestWrapper);
        pickupServiceProvider.execute(requestWrapper.getRequestType(), requestWrapper.getPayload());
    }

}
