package org.com.VerificationService.Request;

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

    public void setPayload(byte[] bytes) {
        this.payload = bytes;
    }

    @Override
    public String toString() {
        return "BookingRequestWrapper{" +
                "requestType='" + requestType + '\'' +
                ", payload=" + Arrays.toString(payload) +
                '}';
    }
}
