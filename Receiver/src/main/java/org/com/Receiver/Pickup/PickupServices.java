package org.com.Receiver.Pickup;

import org.com.Receiver.Kafka.Constants.KafkaTopics;
import org.com.Receiver.Request.ExternalRequests.ExternalCallCenterPickupRequest;
import org.com.Receiver.Request.ExternalRequests.ExternalClientAppPickupRequest;
import org.com.Receiver.Request.ExternalRequests.ExternalGetCostRequest;
import org.com.Receiver.Request.Requests.CallCenterPickupRequest;
import org.com.Receiver.Request.Requests.ClientAppPickupRequest;
import org.com.Receiver.Request.RequestStrategy.RequestConverterHandler;
import org.com.Receiver.Request.RequestWrapper;
import org.com.Receiver.Request.Requests.GetCostRequest;
import org.com.Receiver.Request.Requests.UpdateFCMToken;
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
    }


}
