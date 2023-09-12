package org.com.VerificationService.Kafka.CustomSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.com.VerificationService.Request.Requests.Send.LocatingRequest;

import java.util.Map;

public class LocatingRequestSerializer implements Serializer
{

    @Override
    public void configure(Map configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, Object o) {
        byte[] bytes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        LocatingRequest locatingRequest = (LocatingRequest) o;
        try
        {
            bytes = objectMapper.writeValueAsBytes(locatingRequest);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            bytes = null;
        }
        return bytes;
    }


    @Override
    public void close() {
        Serializer.super.close();
    }
}
