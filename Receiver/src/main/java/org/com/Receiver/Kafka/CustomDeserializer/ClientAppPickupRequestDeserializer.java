package org.com.Receiver.Kafka.CustomDeserializer;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.com.Receiver.Request.ClientAppPickupRequest;

import java.io.IOException;
import java.util.Map;

public class ClientAppPickupRequestDeserializer implements Deserializer
{
    @Override
    public void configure(Map configs, boolean isKey)
    {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        ObjectMapper objectMapper = new ObjectMapper();
        ClientAppPickupRequest clientAppPickupRequest = null;
        try
        {
            clientAppPickupRequest = objectMapper.readValue(bytes, ClientAppPickupRequest.class);
        }
        catch (Exception ex)
        {
            return clientAppPickupRequest;
        }
        return clientAppPickupRequest;
    }

//    @Override
//    public Object deserialize(String topic, Headers headers, byte[] data) {
//        return Deserializer.super.deserialize(topic, headers, data);
//    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
