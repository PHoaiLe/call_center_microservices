package org.com.Receiver.Request.RequestStrategy.RequestConverterStrategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.Receiver.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.Receiver.Request.Requests.GetDirectionRequest;

public class GetDirectionRequestConverterStrategy implements RequestConverterStrategy
{
    @Override
    public byte[] fromObjectToBytes(Object object) {
        byte[] bytes = null;
        GetDirectionRequest request = (GetDirectionRequest) object;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            bytes = mapper.writeValueAsBytes(request);
        }
        catch(Exception ex)
        {
            return null;
        }
        return bytes;
    }

    @Override
    public Object fromByteToObject(byte[] bytes) {
        GetDirectionRequest request = null;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            request = mapper.readValue(bytes, GetDirectionRequest.class);
        }
        catch(Exception ex)
        {
            return null;
        }
        return request;
    }
}
