package org.com.NotificationService.Requests.RequestStrategy.RequestConverterStrategies;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.NotificationService.Requests.Constants.NotificationTypes;
import org.com.NotificationService.Requests.NotificationWrapper;
import org.com.NotificationService.Requests.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.NotificationService.Requests.Requests.GetDirectionNotification;

public class GetDirectionNotificationConverterStrategy implements RequestConverterStrategy
{
    private String notificationType = NotificationTypes.GET_DIRECTION_NOTIFICATION;
    @Override
    public byte[] fromObjectToBytes(Object object) {
        byte[]  bytes = null;
        GetDirectionNotification notification = (GetDirectionNotification) object;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            bytes = mapper.writeValueAsBytes(notification);
        }
        catch (Exception ex)
        {
            return null;
        }
        return bytes;
    }

    @Override
    public Object fromBytesToObject(byte[] bytes) {
        GetDirectionNotification notification = null;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            notification = mapper.readValue(bytes, GetDirectionNotification.class);
        }
        catch (Exception ex)
        {
            return null;
        }
        return notification;
    }

    @Override
    public String getReferenceRequestType() {
        return notificationType;
    }
}
