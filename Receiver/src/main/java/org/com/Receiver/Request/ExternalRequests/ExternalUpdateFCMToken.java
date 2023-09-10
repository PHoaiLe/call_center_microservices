package org.com.Receiver.Request.ExternalRequests;

import java.io.Serializable;

public class ExternalUpdateFCMToken implements Serializable
{
    private String userId;
    private String fcm_token;

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
        return "UpdateFCMTokenRequest{" +
                "userId='" + userId + '\'' +
                ", fcm_token='" + fcm_token + '\'' +
                '}';
    }
}
