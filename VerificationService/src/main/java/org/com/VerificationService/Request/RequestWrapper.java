package org.com.VerificationService.Request;

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


}
