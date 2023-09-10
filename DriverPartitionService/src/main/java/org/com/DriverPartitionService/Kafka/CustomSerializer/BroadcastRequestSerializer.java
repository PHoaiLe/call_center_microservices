package org.com.DriverPartitionService.Kafka.CustomSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.com.DriverPartitionService.Requests.BroadcastRequest;

import java.util.Map;

public class BroadcastRequestSerializer implements Serializer
{

    @Override
    public void configure(Map configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, Object o) {
        byte[] bytes = null;
        BroadcastRequest broadcastRequest = (BroadcastRequest) o;
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            bytes = objectMapper.writeValueAsBytes(broadcastRequest);
        }
        catch(Exception ex)
        {
            return null;
        }
        return bytes;
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
