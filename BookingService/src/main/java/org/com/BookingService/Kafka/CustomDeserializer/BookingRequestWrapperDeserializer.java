package org.com.BookingService.Kafka.CustomDeserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;
import org.com.BookingService.Requests.BookingRequestWrapper;
import org.com.BookingService.Requests.Constants.BookingRequestTypes;

import java.util.Map;

public class BookingRequestWrapperDeserializer implements Deserializer
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
        ObjectMapper mapper = new ObjectMapper();
        BookingRequestWrapper wrapper = null;
        try
        {
            wrapper = mapper.readValue(bytes, BookingRequestWrapper.class);
        }
        catch (Exception ex)
        {
            return null;
        }
        return wrapper;
    }
}
