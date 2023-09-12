package org.com.LocatingService.Request.Send;

import org.com.LocatingService.Request.Constants.NotificationTypes;

import java.io.Serializable;

public class InvalidLocationInfoNotification implements Serializable
{
    private final String notificationType = NotificationTypes.INVALID_LOCATION_INFO;
    private String userId;
    private String startAddress;
    private String endAddress;

    public InvalidLocationInfoNotification()
    {
        this.userId = "";
        this.startAddress = "";
        this.endAddress = "";
    }

    public String getNotificationType() {
        return notificationType;
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
        return "InvalidLocationInfoNotification{" +
                "notificationType='" + notificationType + '\'' +
                ", userId='" + userId + '\'' +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                '}';
    }
}
