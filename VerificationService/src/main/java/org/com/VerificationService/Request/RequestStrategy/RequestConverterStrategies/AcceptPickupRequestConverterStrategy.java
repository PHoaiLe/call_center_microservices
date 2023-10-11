package org.com.VerificationService.Request.RequestStrategy.RequestConverterStrategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.VerificationService.Request.Constants.RequestTypes;
import org.com.VerificationService.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.VerificationService.Request.Requests.Receive.AcceptPickupRequest;

public class AcceptPickupRequestConverterStrategy implements RequestConverterStrategy
{
    private final String requestType = RequestTypes.DRIVER_ACCEPT_PICKUP_REQUEST;

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
        AcceptPickupRequest request = null;
        ObjectMapper mapper = new ObjectMapper();
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

    @Override
    public String getReferenceRequestType() {
        return requestType;
    }
}
