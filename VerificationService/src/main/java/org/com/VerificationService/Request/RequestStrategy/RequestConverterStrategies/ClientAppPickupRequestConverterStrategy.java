package org.com.VerificationService.Request.RequestStrategy.RequestConverterStrategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.VerificationService.Request.Constants.RequestTypes;
import org.com.VerificationService.Request.Requests.Receive.ClientAppPickupRequest;
import org.com.VerificationService.Request.RequestStrategy.Interfaces.RequestConverterStrategy;

public class ClientAppPickupRequestConverterStrategy implements RequestConverterStrategy
{
    private final String referenceRequestType = RequestTypes.CLIENT_APP_NEW_PICK_UP_REQUEST;

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

    @Override
    public String getReferenceRequestType()
    {
        return referenceRequestType;
    }
}
