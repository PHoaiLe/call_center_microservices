package org.com.NotificationService.Requests.Requests;

import org.com.NotificationService.Requests.Constants.NotificationTypes;
import org.com.NotificationService.Requests.Requests.Others.Geometry;

import java.io.Serializable;

public class GetDirectionNotification implements Serializable
{
    private String userId;
    private String notificationType = NotificationTypes.GET_DIRECTION_NOTIFICATION;
    private Geometry geometry;

    public GetDirectionNotification()
    {
        notificationType = NotificationTypes.GET_DIRECTION_NOTIFICATION;
        userId = "";
        geometry = null;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    @Override
    public String toString() {
        return "GetDirectionNotification{" +
                "userId='" + userId + '\'' +
                ", notificationType='" + notificationType + '\'' +
                ", geometry=" + geometry +
                '}';
    }
}
