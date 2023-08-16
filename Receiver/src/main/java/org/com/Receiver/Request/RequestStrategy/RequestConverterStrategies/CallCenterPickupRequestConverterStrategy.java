package org.com.Receiver.Request.RequestStrategy.RequestConverterStrategies;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.Receiver.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.Receiver.Request.CallCenterPickupRequest;

public class CallCenterPickupRequestConverterStrategy implements RequestConverterStrategy
{

    @Override
    public byte[] fromObjectToBytes(Object object) {
        byte[] bytes = null;
        if(object == null)
        {
            return bytes;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        CallCenterPickupRequest callCenterPickupRequest = (CallCenterPickupRequest) object;
        try
        {
            bytes = objectMapper.writeValueAsBytes(callCenterPickupRequest);
        }
        catch (JsonProcessingException e) {
            System.out.println(e);
            return null;
        }

        return bytes;
    }

    @Override
    public Object fromByteToObject(byte[] bytes) {
        CallCenterPickupRequest callCenterPickupRequest = null;
        if(bytes == null)
        {
            return callCenterPickupRequest;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            callCenterPickupRequest = objectMapper.readValue(bytes, CallCenterPickupRequest.class);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return null;
        }
        return callCenterPickupRequest;
    }
}
