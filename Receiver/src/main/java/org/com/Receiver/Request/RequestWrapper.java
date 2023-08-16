package org.com.Receiver.Request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.Arrays;

public class RequestWrapper implements Serializable
{
    private String requestType;
    private byte[] payload;

    public String getRequestType()
    {
        return requestType;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public void setPayload(byte[] bytes)
    {
        this.payload = bytes;
    }

    @Override
    public String toString() {
        return "RequestWrapper{" +
                "requestType='" + requestType + '\'' +
                ", payload=" + Arrays.toString(payload) +
                '}';
    }

//    public byte[] fromObjectToBytes(RequestWrapper wrapper)
//    {
//        byte[] bytes = null;
//        ObjectMapper objectMapper = new ObjectMapper();
//        try
//        {
//            bytes = objectMapper.writeValueAsBytes(wrapper);
//            return bytes;
//        } catch (JsonProcessingException e) {
//            return bytes;
//        }
//    }


}
