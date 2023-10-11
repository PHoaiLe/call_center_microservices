package org.com.BookingService.Requests.RequestStrategy.RequestConverterStrategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.BookingService.Requests.Constants.BookingRequestTypes;
import org.com.BookingService.Requests.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.BookingService.Requests.Requests.Receive.AcceptPickupRequest;

public class AcceptPickupRequestConverterStrategy implements RequestConverterStrategy
{

    private final String requestType = BookingRequestTypes.ACCEPT_PICKUP_REQUEST;

    @Override
    public byte[] fromObjectToBytes(Object object) {
        byte[] bytes = null;
        AcceptPickupRequest request = (AcceptPickupRequest) object;
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
