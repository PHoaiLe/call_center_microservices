package org.com.VerificationService.Kafka.CustomSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.com.VerificationService.Request.Constants.BookingRequestTypes;
import org.com.VerificationService.Request.Requests.Send.BookingUpdateFCMToken;

import java.util.Map;

public class BookingUpdateFCMTokenSerializer implements Serializer
{

    @Override
    public void configure(Map configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, Object o) {
        byte[] bytes = null;
        BookingUpdateFCMToken updateFCMToken = (BookingUpdateFCMToken) o;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            bytes = mapper.writeValueAsBytes(updateFCMToken);
        }
        catch (Exception ex)
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
