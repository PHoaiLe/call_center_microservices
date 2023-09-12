package org.com.Receiver.Request.Requests;

import org.com.Receiver.Request.Constants.RequestTypes;
import org.com.Receiver.Request.ExternalRequests.ExternalGetDirectionRequest;

import java.io.Serializable;

public class GetDirectionRequest implements Serializable
{
    private String idToken;
    private String systemKey;
    private String requestType;
    private String userId;
    private String startAddress;
    private String endAddress;

    public GetDirectionRequest()
    {
        idToken = "";
        systemKey = "";
        requestType = RequestTypes.GET_DIRECTION_REQUEST;
        userId = "";
        startAddress = "";
        endAddress = "";
    }

    public GetDirectionRequest(ExternalGetDirectionRequest request)
    {
        idToken = request.getIdToken();
        systemKey = request.getSystemKey();
        requestType = RequestTypes.GET_DIRECTION_REQUEST;
        userId = request.getUserId();
        startAddress = request.getStartAddress();
        endAddress = request.getEndAddress();
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        return "GetDirectionRequest{" +
                "idToken='" + idToken + '\'' +
                ", systemKey='" + systemKey + '\'' +
                ", requestType='" + requestType + '\'' +
                ", userId='" + userId + '\'' +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                '}';
    }

}
