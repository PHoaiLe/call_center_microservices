package org.com.NotificationService.Requests.RequestStrategy.RequestConverterStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.NotificationService.Requests.Constants.NotificationTypes;
import org.com.NotificationService.Requests.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.NotificationService.Requests.Requests.GetCostNotification;

public class GetCostNotificationConverterStrategy implements RequestConverterStrategy
{
    private final String referenceNotificationType = NotificationTypes.GET_COST_NOTIFICATION_REQUEST;
    @Override
    public byte[] fromObjectToBytes(Object object) {
        GetCostNotification notification = (GetCostNotification) object;
        ObjectMapper mapper = new ObjectMapper();
        byte[] bytes = null;
        try
        {
            bytes = mapper.writeValueAsBytes(notification);
        }
        catch(Exception ex)
        {
            return null;
        }
        return bytes;
    }

    @Override
    public Object fromBytesToObject(byte[] bytes) {
        GetCostNotification notification = null;
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            notification = mapper.readValue(bytes, GetCostNotification.class);
        }
        catch (Exception ex)
        {
            return null;
        }
        return notification;
    }

    @Override
    public String getReferenceRequestType() {
        return referenceNotificationType;
    }
}
