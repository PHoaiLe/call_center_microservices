package org.com.LocatingService.Kafka.CustomSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.com.LocatingService.Request.DriverPartitionRequest;
import java.util.Map;

public class DriverPartitionRequestSerializer implements Serializer
{

    @Override
    public void configure(Map configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, Object o) {
        byte[] bytes = null;
        ObjectMapper objectMapper = new ObjectMapper();
        DriverPartitionRequest driverPartitionRequest = (DriverPartitionRequest) o;
        try
        {
            bytes = objectMapper.writeValueAsBytes(driverPartitionRequest);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return bytes;
        }
        return bytes;
    }


    @Override
    public void close() {
        Serializer.super.close();
    }
}
