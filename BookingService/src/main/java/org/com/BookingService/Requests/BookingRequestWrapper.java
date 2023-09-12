package org.com.BookingService.Requests;

import java.io.Serializable;
import java.util.Arrays;

public class BookingRequestWrapper implements Serializable
{
    private String requestType;
    private byte[] payload;


    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "BookingWrapperRequest{" +
                "requestType='" + requestType + '\'' +
                ", payload=" + Arrays.toString(payload) +
                '}';
    }
}
