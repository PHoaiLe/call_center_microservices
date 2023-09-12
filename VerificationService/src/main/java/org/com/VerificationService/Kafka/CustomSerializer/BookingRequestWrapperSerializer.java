package org.com.VerificationService.Kafka.CustomSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.com.VerificationService.Request.BookingRequestWrapper;

import java.util.Map;

public class BookingRequestWrapperSerializer implements Serializer
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
        byte[] bytes = null;
        BookingRequestWrapper request = (BookingRequestWrapper) o;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            bytes = mapper.writeValueAsBytes(request);
        }
        catch (Exception ex)
        {
            return null;
        }
        return bytes;
    }
}
