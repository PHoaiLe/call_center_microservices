package org.com.VerificationService.Kafka.CustomSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.com.VerificationService.Request.NotificationWrapper;

import java.util.Map;

public class NotificationWrapperRequestSerializer implements Serializer
{
    @Override
    public void configure(Map configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }

    @Override
    public byte[] serialize(String s, Object o) {
        ObjectMapper mapper = new ObjectMapper();
        NotificationWrapper wrapper = (NotificationWrapper) o;
        byte[] bytes = null;
        try
        {
            bytes = mapper.writeValueAsBytes(wrapper);
        }
        catch(Exception ex)
        {
            return null;
        }
        return bytes;
    }

}
