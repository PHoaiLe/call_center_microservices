package org.com.Receiver.Pickup;

import org.com.Receiver.Kafka.Constants.KafkaTopics;
import org.com.Receiver.Request.ExternalRequests.*;
import org.com.Receiver.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.Receiver.Request.Requests.*;
import org.com.Receiver.Request.RequestStrategy.RequestConverterHandler;
import org.com.Receiver.Request.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PickupServices {
    @Autowired
    private KafkaTemplate<String, RequestWrapper> requestWrapperKafkaTemplate;


    public void sendToDataRoom(ExternalClientAppPickupRequest clientAppPickupRequest)
    {
        try
        {
            if(clientAppPickupRequest == null)
            {
                return;
            }

            ClientAppPickupRequest request = new ClientAppPickupRequest(clientAppPickupRequest);

            RequestWrapper wrapper = new RequestWrapper();
            wrapper.setRequestType(request.getRequestType());

            RequestConverterHandler handler = new RequestConverterHandler();
            handler.setClientAppPickupRequestConverterStrategy();
            wrapper.setPayload(handler.fromObjectToBytes(request));

            requestWrapperKafkaTemplate.send(KafkaTopics.DATA_ROOM, wrapper);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void sendToDataRoom(ExternalCallCenterPickupRequest callCenterPickupRequest)
    {
        try
        {
            if(callCenterPickupRequest == null)
            {
                return;
            }

            CallCenterPickupRequest request = new CallCenterPickupRequest(callCenterPickupRequest);

            RequestWrapper wrapper = new RequestWrapper();
            wrapper.setRequestType(request.getRequestType());

            RequestConverterHandler handler = new RequestConverterHandler();
            handler.setCallCenterPickupRequestConverterStrategy();
            wrapper.setPayload(handler.fromObjectToBytes(request));

            requestWrapperKafkaTemplate.send(KafkaTopics.DATA_ROOM, wrapper);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public void sendToDataRoom(ExternalGetCostRequest request)
    {
        if(request == null)
        {
            return;
        }

        GetCostRequest costRequest = new GetCostRequest(request);

        RequestWrapper requestWrapper = new RequestWrapper();
        requestWrapper.setRequestType(costRequest.getRequestType());

        RequestConverterHandler converterHandler = new RequestConverterHandler();
        converterHandler.setGetCostRequestConverterStrategy();
        requestWrapper.setPayload(converterHandler.fromObjectToBytes(costRequest));

        requestWrapperKafkaTemplate.send(KafkaTopics.DATA_ROOM, requestWrapper);
        System.out.println("after sending ...");
    }

    public void sendToDataRoom(ExternalUpdateFCMToken request)
    {
        if(request == null)
        {
            return;
        }

        UpdateFCMToken fcmTokenRequest = new UpdateFCMToken(request);
        RequestWrapper wrapper = new RequestWrapper();

        wrapper.setRequestType(fcmTokenRequest.getRequestType());

        RequestConverterHandler converterHandler = new RequestConverterHandler();
        converterHandler.setUpdateFCMTokenRequestConverterStrategy();

        wrapper.setPayload(converterHandler.fromObjectToBytes(fcmTokenRequest));

        requestWrapperKafkaTemplate.send(KafkaTopics.DATA_ROOM, wrapper);
        System.out.println("after sending update fcm token...");
    }

    public void sendToDataRoom(ExternalGetDirectionRequest request)
    {
        if(request == null)
        {
            return;
        }

        GetDirectionRequest directionRequest = new GetDirectionRequest(request);
        RequestWrapper wrapper = new RequestWrapper();

        RequestConverterHandler converterHandler = new RequestConverterHandler();
        converterHandler.setGetDirectionRequestConverterStrategy();

        wrapper.setRequestType(directionRequest.getRequestType());
        wrapper.setPayload(converterHandler.fromObjectToBytes(directionRequest));

        requestWrapperKafkaTemplate.send(KafkaTopics.DATA_ROOM, wrapper);
        System.out.println("after sending get direction request...");
    }

    public void sendToDataRoom(ExternalAcceptPickupRequest request)
    {
        if(request == null)
        {
            return;
        }

        AcceptPickupRequest acceptPickupRequest = new AcceptPickupRequest(request);
        RequestConverterHandler converterHandler = new RequestConverterHandler();
        RequestWrapper wrapper = new RequestWrapper();

        converterHandler.setAcceptPickupRequestConverterStrategy();

        wrapper.setRequestType(acceptPickupRequest.getRequestType());
        wrapper.setPayload(converterHandler.fromObjectToBytes(acceptPickupRequest));

        requestWrapperKafkaTemplate.send(KafkaTopics.DATA_ROOM, wrapper);
        System.out.println("after sending accept pickup request...");

    }

}
