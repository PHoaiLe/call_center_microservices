package org.com.VerificationService.Kafka.CustomSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.com.VerificationService.Request.RequestWrapper;

import java.util.Map;

public class RequestWrapperSerializer implements Serializer
{

    @Override
    public void configure(Map configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        RequestWrapper requestWrapper = (RequestWrapper) o;
        byte[] bytes = null;
        try
        {
            bytes = objectMapper.writeValueAsBytes(requestWrapper);
            return bytes;
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Object data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
