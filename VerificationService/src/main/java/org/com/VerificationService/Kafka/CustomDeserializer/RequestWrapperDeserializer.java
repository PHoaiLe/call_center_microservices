package org.com.VerificationService.Kafka.CustomDeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.com.VerificationService.Request.RequestWrapper;

import java.util.Map;

public class RequestWrapperDeserializer implements Deserializer
{

    @Override
    public void configure(Map configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        RequestWrapper requestWrapper  = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            requestWrapper = objectMapper.readValue(bytes, RequestWrapper.class);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return null;
        }
        return requestWrapper;
    }

    @Override
    public Object deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
