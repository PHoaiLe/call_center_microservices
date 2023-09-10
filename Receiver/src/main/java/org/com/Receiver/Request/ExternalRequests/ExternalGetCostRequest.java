package org.com.Receiver.Request.ExternalRequests;

import java.io.Serializable;

public class ExternalGetCostRequest implements Serializable
{
    private String idToken;
    private String userId;
    private String systemKey;
    private String startAddress;
    private String endAddress;

    public ExternalGetCostRequest()
    {
        this.idToken = "";
        this.userId = "";
        this.systemKey = "";
        this.startAddress = "";
        this.endAddress = "";
    }


    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }


    @Override
    public String toString() {
        return "ExternalGetCostRequest{" +
                "idToken='" + idToken + '\'' +
                ", userId='" + userId + '\'' +
                ", systemKey='" + systemKey + '\'' +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                '}';
    }
}
