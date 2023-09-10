package org.com.LocatingService.Kafka.CustomSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;
import org.com.LocatingService.Request.GetCostNotification;

import java.io.Serializable;
import java.util.Map;

public class GetCostNotificationSerializer implements Serializer
{

    @Override
    public void configure(Map configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, Object o) {
        byte[] bytes = null;
        GetCostNotification costNotification = (GetCostNotification) o;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            bytes = mapper.writeValueAsBytes(o);
        }
        catch (Exception ex)
        {
            return null;
        }
        return bytes;
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Object data) {
        return Serializer.super.serialize(topic, headers, data);
    }
}
