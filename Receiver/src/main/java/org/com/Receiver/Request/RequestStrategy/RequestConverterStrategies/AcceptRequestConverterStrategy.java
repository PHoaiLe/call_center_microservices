package org.com.Receiver.Request.RequestStrategy.RequestConverterStrategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.Receiver.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.Receiver.Request.Requests.AcceptPickupRequest;

public class AcceptRequestConverterStrategy implements RequestConverterStrategy
{
    @Override
    public byte[] fromObjectToBytes(Object object) {
        byte[] bytes = null;
        AcceptPickupRequest request = (AcceptPickupRequest) object;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            bytes = mapper.writeValueAsBytes(request);
        }
        catch (Exception ex)
        {
            return null;
        }
        return bytes;
    }

    @Override
    public Object fromByteToObject(byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        AcceptPickupRequest request = null;
        try
        {
            request = mapper.readValue(bytes, AcceptPickupRequest.class);
        }
        catch (Exception ex)
        {
            return null;
        }
        return request;
    }
}
