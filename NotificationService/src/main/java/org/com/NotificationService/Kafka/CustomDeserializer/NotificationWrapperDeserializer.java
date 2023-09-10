package org.com.NotificationService.Kafka.CustomDeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.com.NotificationService.Requests.NotificationWrapper;

import java.util.Map;

public class NotificationWrapperDeserializer implements Deserializer
{
    @Override
    public void configure(Map configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        NotificationWrapper wrapper = null;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            wrapper = mapper.readValue(bytes, NotificationWrapper.class);
        }
        catch (Exception ex)
        {
            return null;
        }

        return wrapper;
    }
}
