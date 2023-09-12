package org.com.LocatingService.Kafka.CustomSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.com.LocatingService.Request.Send.InvalidLocationInfoNotification;

import java.util.Map;

public class InvalidLocationInfoNotificationSerializer implements Serializer
{

    @Override
    public void configure(Map configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, Object o) {
        byte[] bytes = null;
        InvalidLocationInfoNotification notification = (InvalidLocationInfoNotification) o;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            bytes = mapper.writeValueAsBytes(notification);
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
