package org.com.VerificationService.Request.Requests.Send;

import org.com.VerificationService.Request.Constants.BookingRequestTypes;
import org.com.VerificationService.Request.Constants.RequestTypes;

import java.io.Serializable;

public class BookingUpdateFCMToken implements Serializable
{
    private String requestType;
    private String userId;
    private String role;
    private String fcm_token;


    public BookingUpdateFCMToken()
    {
        this.requestType = BookingRequestTypes.UPDATE_FCM_TOKEN;
        this.fcm_token = "";
        this.role = "";
        this.userId = "";
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }

    @Override
    public String toString() {
        return "BookingUpdateFCMToken{" +
                "requestType='" + requestType + '\'' +
                ", userId='" + userId + '\'' +
                ", role='" + role + '\'' +
                ", fcm_token='" + fcm_token + '\'' +
                '}';
    }
}
