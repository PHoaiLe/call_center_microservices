package org.com.VerificationService.Kafka.CustomSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.com.VerificationService.Request.Requests.CallCenterPickupRequest;

import java.util.Map;

public class CallCenterPickupRequestSerializer implements Serializer
{

    @Override
    public void configure(Map configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, Object o) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        CallCenterPickupRequest callCenterPickupRequest = (CallCenterPickupRequest) o;
        try
        {
            retVal = objectMapper.writeValueAsBytes(callCenterPickupRequest);
        }
        catch(Exception ex)
        {
            return retVal;
        }
        return retVal;
    }


    @Override
    public void close() {
        Serializer.super.close();
    }
}
