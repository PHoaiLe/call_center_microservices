package org.com.LocatingService.Kafka.CustomDeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.com.LocatingService.Request.Receive.LocatingRequest;

import java.util.Map;

public class LocatingRequestDeserializer implements Deserializer
{

    @Override
    public void configure(Map configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        LocatingRequest locatingRequest = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            locatingRequest = objectMapper.readValue(bytes, LocatingRequest.class);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return null;
        }
        return locatingRequest;
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
