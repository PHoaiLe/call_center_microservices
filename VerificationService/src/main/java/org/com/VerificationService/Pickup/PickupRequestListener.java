package org.com.VerificationService.Pickup;

import org.com.VerificationService.Kafka.Constants.KafkaListenerIds;
import org.com.VerificationService.Kafka.Constants.KafkaTopics;
import org.com.VerificationService.Request.CallCenterPickupRequest;
import org.com.VerificationService.Request.ClientAppPickupRequest;

import org.com.VerificationService.Request.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PickupRequestListener
{
    private final PickupService pickupService;

    @Autowired
    public PickupRequestListener(PickupService pickupService)
    {
        this.pickupService = pickupService;
    }

    //receive PickupRequest message from topic "data_room"

//    @KafkaListener(topics = KafkaTopics.DATA_ROOM,
//    id = KafkaListenerIds.CLIENT_APP_PICKUP_REQUEST_LISTENER_ID,
//    containerFactory = "clientAppPickupRequestConcurrentKafkaListenerContainerFactory")
//    public void clientAppPickupRequestListener(ClientAppPickupRequest clientAppPickupRequest)
//    {
//        System.out.println("Client consumer: " + clientAppPickupRequest);
//        if(clientAppPickupRequest == null)
//        {
//            return;
//        }
//        pickupService.sendToLocationDefineService(clientAppPickupRequest);
//    }
//
//    @KafkaListener(topics = KafkaTopics.DATA_ROOM,
//    id = KafkaListenerIds.CALL_CENTER_PICKUP_REQUEST_LISTENER_ID,
//    containerFactory = "callCenterPickupRequestConcurrentKafkaListenerContainerFactory")
//    public void callCenterPickupRequestListener(CallCenterPickupRequest callCenterPickupRequest)
//    {
//        System.out.println("Center consumer: " + callCenterPickupRequest);
//        if(callCenterPickupRequest == null)
//        {
//            return;
//        }
//    }

    @KafkaListener(topics = KafkaTopics.DATA_ROOM,
    id = KafkaListenerIds.DATA_ROOM_REQUEST_WRAPPER_LISTENER_ID,
    containerFactory = "requestWrapperConcurrentKafkaListenerContainerFactory")
    public void dataRoomListener(RequestWrapper requestWrapper)
    {
        System.out.println("Data-room consumer: " + requestWrapper);

    }

}
