package org.com.Receiver.Kafka.CustomSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.com.Receiver.Request.Requests.GetCostRequest;

import java.util.Map;

public class GetCostRequestSerializer implements Serializer
{

    @Override
    public void configure(Map configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, Object o) {
        byte[] bytes = null;
        ObjectMapper mapper = new ObjectMapper();
        GetCostRequest request = (GetCostRequest) o;
        try
        {
            bytes = mapper.writeValueAsBytes(mapper);
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
