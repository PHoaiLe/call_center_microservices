package org.com.LocatingService.Request.Send;

import org.com.LocatingService.Request.Constants.NotificationTypes;
import org.example.locatingdependency.Objects.Direction.InnerParts.Geometry.Geometry;

import java.io.Serializable;

public class GetDirectionDetailNotification implements Serializable
{
    private String userId;
    private String notificationType = NotificationTypes.GET_DIRECTION_NOTIFICATION;
    private Geometry geometry;

    public GetDirectionDetailNotification()
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
        return "GetDirectionDetailNotification{" +
                "userId='" + userId + '\'' +
                ", notificationType='" + notificationType + '\'' +
                ", geometry=" + geometry +
                '}';
    }
}
