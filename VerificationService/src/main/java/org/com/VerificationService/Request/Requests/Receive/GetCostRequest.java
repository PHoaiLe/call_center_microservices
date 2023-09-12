package org.com.VerificationService.Request.Requests.Receive;

import java.io.Serializable;

public class GetCostRequest implements Serializable
{
    private String idToken;
    private String userId;
    private String systemKey;
    private String requestType;
    private String startAddress;
    private String endAddress;


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

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
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
        return "GetCostRequest{" +
                "idToken='" + idToken + '\'' +
                ", userId='" + userId + '\'' +
                ", systemKey='" + systemKey + '\'' +
                ", requestType='" + requestType + '\'' +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                '}';
    }

    public GetCostRequest()
    {
        this.userId = "";
        this.idToken = "";
        this.systemKey = "";
        this.requestType = "";
        this.startAddress = "";
        this.endAddress = "";
    }
}
