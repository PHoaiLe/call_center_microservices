package org.com.Receiver.Pickup;

import org.com.Receiver.Kafka.Constants.KafkaTopics;
import org.com.Receiver.Request.Requests.CallCenterPickupRequest;
import org.com.Receiver.Request.Requests.ClientAppPickupRequest;
import org.com.Receiver.Request.RequestStrategy.RequestConverterHandler;
import org.com.Receiver.Request.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PickupServices {
    @Autowired
    private KafkaTemplate<String, ClientAppPickupRequest> clientAppNewRequestKafkaTemplate;
    @Autowired
    private KafkaTemplate<String, CallCenterPickupRequest> callCenterNewRequestKafkaTemplate;
    @Autowired
    private KafkaTemplate<String, RequestWrapper> requestWrapperKafkaTemplate;

    public void sendToDataRoom(ClientAppPickupRequest clientAppPickupRequest)
    {
        try
        {
            if(clientAppPickupRequest == null)
            {
                return;
            }
            System.out.println(clientAppPickupRequest);
            RequestWrapper wrapper = new RequestWrapper();
            wrapper.setRequestType(clientAppPickupRequest.getRequestType());

            RequestConverterHandler handler = new RequestConverterHandler();
            handler.setClientAppPickupRequestConverterStrategy();
            wrapper.setPayload(handler.fromObjectToBytes(clientAppPickupRequest));

            requestWrapperKafkaTemplate.send(KafkaTopics.DATA_ROOM, wrapper);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void sendToDataRoom(CallCenterPickupRequest callCenterPickupRequest)
    {
        try
        {
            if(callCenterPickupRequest == null)
            {
                return;
            }
            System.out.println(callCenterPickupRequest);

            RequestWrapper wrapper = new RequestWrapper();
            wrapper.setRequestType(callCenterPickupRequest.getRequestType());

            RequestConverterHandler handler = new RequestConverterHandler();
            handler.setCallCenterPickupRequestConverterStrategy();
            wrapper.setPayload(handler.fromObjectToBytes(callCenterPickupRequest));

            requestWrapperKafkaTemplate.send(KafkaTopics.DATA_ROOM, wrapper);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }


}
