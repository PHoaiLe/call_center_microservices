package org.com.BroadcastService.Kafka.CustomDeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.com.BroadcastService.Requests.Requests.Receive.BroadcastRequest;

import java.util.Map;

public class BroadcastRequestDeserializer implements Deserializer
{

    @Override
    public void configure(Map configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        BroadcastRequest broadcastRequest = null;
        try
        {
            broadcastRequest = objectMapper.readValue(bytes, BroadcastRequest.class);
        }
        catch(Exception ex)
        {
            return null;
        }

        return broadcastRequest;
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
