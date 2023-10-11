package org.com.VerificationService.Request;

import java.io.Serializable;
import java.util.Arrays;

public class NotificationWrapper implements Serializable
{
    private String notificationType;
    private byte[] payload;

    public NotificationWrapper()
    {
        notificationType = "";
        payload = null;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "NotificationWrapper{" +
                "notificationType='" + notificationType + '\'' +
                ", payload=" + Arrays.toString(payload) +
                '}';
    }
}
