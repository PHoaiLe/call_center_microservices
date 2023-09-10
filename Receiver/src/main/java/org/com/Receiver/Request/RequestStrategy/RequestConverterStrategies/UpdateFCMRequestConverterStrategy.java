package org.com.Receiver.Request.RequestStrategy.RequestConverterStrategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.Receiver.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.Receiver.Request.Requests.UpdateFCMToken;

public class UpdateFCMRequestConverterStrategy implements RequestConverterStrategy
{

    @Override
    public byte[] fromObjectToBytes(Object object) {
        if(object == null)
        {
            return null;
        }
        byte[] bytes = null;
        UpdateFCMToken request = (UpdateFCMToken) object;
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
        if(bytes == null)
        {
            return null;
        }
        if(bytes.length == 0)
        {
            return null;
        }

        UpdateFCMToken updateFCMToken = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try
        {
            updateFCMToken = objectMapper.readValue(bytes, UpdateFCMToken.class);
        }
        catch(Exception ex)
        {
            return null;
        }

        return updateFCMToken;
    }
}
