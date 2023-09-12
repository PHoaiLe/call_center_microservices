package org.com.Receiver.Request.ExternalRequests;

import java.io.Serializable;

public class ExternalUpdateFCMToken implements Serializable
{
    private String idToken;
    private String systemKey;
    private String userId;
    private String role;
    private String fcm_token;

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

    @Override
    public String toString() {
        return "ExternalUpdateFCMToken{" +
                "idToken='" + idToken + '\'' +
                ", systemKey='" + systemKey + '\'' +
                ", userId='" + userId + '\'' +
                ", role='" + role + '\'' +
                ", fcm_token='" + fcm_token + '\'' +
                '}';
    }
}
