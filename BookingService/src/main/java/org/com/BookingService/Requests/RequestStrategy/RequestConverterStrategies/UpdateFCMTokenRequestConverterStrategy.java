package org.com.BookingService.Requests.RequestStrategy.RequestConverterStrategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.BookingService.Requests.Constants.BookingRequestTypes;
import org.com.BookingService.Requests.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.BookingService.Requests.Requests.Receive.UpdateFCMToken;

public class UpdateFCMTokenRequestConverterStrategy implements RequestConverterStrategy
{
    private final String referenceRequestType = BookingRequestTypes.UPDATE_FCM_TOKEN;

    @Override
    public byte[] fromObjectToBytes(Object object) {
        byte[] bytes = null;
        UpdateFCMToken request = (UpdateFCMToken) object;
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
        UpdateFCMToken request = null;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            request = mapper.readValue(bytes, UpdateFCMToken.class);
        }
        catch(Exception ex)
        {
            return null;
        }
        return request;
    }

    @Override
    public String getReferenceRequestType() {
        return this.referenceRequestType;
    }
}
