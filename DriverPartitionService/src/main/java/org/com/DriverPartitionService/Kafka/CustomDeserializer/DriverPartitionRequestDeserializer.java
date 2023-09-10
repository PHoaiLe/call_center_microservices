package org.com.DriverPartitionService.Kafka.CustomDeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.com.DriverPartitionService.Requests.DriverPartitionRequest;

import java.util.Map;

public class DriverPartitionRequestDeserializer implements Deserializer
{


    @Override
    public void configure(Map configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        DriverPartitionRequest driverPartitionRequest = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try
        {
            driverPartitionRequest = objectMapper.readValue(bytes, DriverPartitionRequest.class);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return null;
        }
        return driverPartitionRequest;
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
