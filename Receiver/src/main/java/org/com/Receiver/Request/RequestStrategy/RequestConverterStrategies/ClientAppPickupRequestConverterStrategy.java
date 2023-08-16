package org.com.Receiver.Request.RequestStrategy.RequestConverterStrategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.Receiver.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.Receiver.Request.ClientAppPickupRequest;

public class ClientAppPickupRequestConverterStrategy implements RequestConverterStrategy
{

    @Override
    public byte[] fromObjectToBytes(Object object) {
        if(object == null)
        {
            return null;
        }
        byte[] bytes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        ClientAppPickupRequest clientAppPickupRequest = (ClientAppPickupRequest) object;
        try
        {
            bytes = objectMapper.writeValueAsBytes(clientAppPickupRequest);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
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
        ClientAppPickupRequest clientAppPickupRequest = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            clientAppPickupRequest = objectMapper.readValue(bytes, ClientAppPickupRequest.class);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return null;
        }
        return clientAppPickupRequest;
    }
}
