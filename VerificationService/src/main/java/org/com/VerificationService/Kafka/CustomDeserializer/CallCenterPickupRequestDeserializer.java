package org.com.VerificationService.Kafka.CustomDeserializer;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.com.VerificationService.Request.CallCenterPickupRequest;

import java.io.IOException;
import java.util.Map;

public class CallCenterPickupRequestDeserializer implements Deserializer
{
    @Override
    public void configure(Map configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        CallCenterPickupRequest callCenterPickupRequest = null;
        try
        {
            callCenterPickupRequest = objectMapper.readValue(bytes, CallCenterPickupRequest.class);
        }
        catch (Exception ex)
        {
            return callCenterPickupRequest;
        }
        return callCenterPickupRequest;
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
