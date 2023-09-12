package org.com.Receiver.Request.Requests;

import org.com.Receiver.Request.Constants.RequestTypes;
import org.com.Receiver.Request.ExternalRequests.ExternalUpdateFCMToken;

import java.io.Serializable;

public class UpdateFCMToken implements Serializable
{
    private String systemKey;
    private String userId;
    private String role;
    private String fcm_token;
    private String requestType;
    private String idToken;

    public UpdateFCMToken()
    {
        this.idToken = "";
        this.role = "";
        this.fcm_token = "";
        this.systemKey = "";
        this.userId = "";
        this.requestType = RequestTypes.UPDATE_FCM_TOKEN_REQUEST;
    }

    public UpdateFCMToken(ExternalUpdateFCMToken request)
    {
        this.requestType = RequestTypes.UPDATE_FCM_TOKEN_REQUEST;
        this.fcm_token = request.getFcm_token();
        this.userId = request.getUserId();
        this.role = request.getRole();
        this.systemKey = request.getSystemKey();
        this.idToken = request.getIdToken();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        return "UpdateFCMToken{" +
                "systemKey='" + systemKey + '\'' +
                ", userId='" + userId + '\'' +
                ", role='" + role + '\'' +
                ", fcm_token='" + fcm_token + '\'' +
                ", requestType='" + requestType + '\'' +
                ", idToken='" + idToken + '\'' +
                '}';
    }
}
