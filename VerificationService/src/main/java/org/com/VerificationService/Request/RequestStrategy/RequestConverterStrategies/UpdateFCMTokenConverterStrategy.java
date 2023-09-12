package org.com.VerificationService.Request.RequestStrategy.RequestConverterStrategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.VerificationService.Request.Constants.RequestTypes;
import org.com.VerificationService.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.VerificationService.Request.Requests.Receive.UpdateFCMToken;

public class UpdateFCMTokenConverterStrategy implements RequestConverterStrategy
{
    private final String referenceRequestType = RequestTypes.UPDATE_FCM_TOKEN_REQUEST;

    @Override
    public byte[] fromObjectToBytes(Object object) {
        byte[] bytes = null;
        UpdateFCMToken request = (UpdateFCMToken) object;
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
        UpdateFCMToken request = null;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            request = mapper.readValue(bytes, UpdateFCMToken.class);
        }
        catch (Exception ex)
        {
            return null;
        }
        return  request;
    }

    @Override
    public String getReferenceRequestType() {
        return referenceRequestType;
    }
}
