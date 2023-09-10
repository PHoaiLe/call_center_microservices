package org.com.Receiver.Request.RequestStrategy.RequestConverterStrategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.Receiver.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.Receiver.Request.Requests.GetCostRequest;

public class GetCostRequestConverterStrategy implements RequestConverterStrategy
{

    @Override
    public byte[] fromObjectToBytes(Object object) {
        byte[] bytes = null;
        GetCostRequest request = (GetCostRequest) object;
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            bytes = objectMapper.writeValueAsBytes(request);
        }
        catch(Exception ex)
        {
            return null;
        }
        return bytes;
    }

    @Override
    public Object fromByteToObject(byte[] bytes) {
        GetCostRequest request = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            request = objectMapper.readValue(bytes, GetCostRequest.class);
        }
        catch(Exception ex)
        {
            return null;
        }
        return request;
    }
}
