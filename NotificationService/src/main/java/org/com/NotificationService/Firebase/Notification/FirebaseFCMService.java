package org.com.NotificationService.Firebase.Notification;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.com.NotificationService.Firebase.Cusomer.FirebaseCustomerRepository;
import org.com.NotificationService.Requests.Constants.NotificationAttributes;
import org.com.NotificationService.Requests.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.NotificationService.Requests.RequestStrategy.RequestConverterStrategies.GetCostNotificationConverterStrategy;
import org.com.NotificationService.Requests.RequestStrategy.RequestConverterStrategies.GetDirectionNotificationConverterStrategy;
import org.com.NotificationService.Requests.Requests.GetCostNotification;
import org.com.NotificationService.Requests.Requests.GetDirectionNotification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FirebaseFCMService
{
    private FirebaseFCMRepository fcmRepository;
    private FirebaseCustomerRepository customerRepository;

    public FirebaseFCMService()
    {
        fcmRepository = new FirebaseFCMRepository();
        customerRepository = new FirebaseCustomerRepository();
    }

    public String sendMessageToDevice(GetCostNotification notification)
    {
        if(notification == null)
        {
            return null;
        }

        String fcm_token = customerRepository.getCustomerFCMToken(notification.getIdUser());
        System.out.println(fcm_token);

        //convert GetCostNotification into String(bytes)

        RequestConverterStrategy converterStrategy = new GetCostNotificationConverterStrategy();

        String userId = notification.getIdUser();
        String notificationType = notification.getNotificationType();
        String stringOfBytes = new String(converterStrategy.fromObjectToBytes(notification));

        Map<String, String> props = new HashMap<>();
        props.put(NotificationAttributes.USER_ID, userId);
        props.put(NotificationAttributes.NOTIFICATION_TYPE, notificationType);
        props.put(NotificationAttributes.PAYLOAD, stringOfBytes);


        Message message = Message.builder()
                .putAllData(props)
                .setToken(fcm_token)
                .build();

        return fcmRepository.sendMessageToDevice(message);
    }

    public String sendMessageToDevice(GetDirectionNotification notification)
    {
        if(notification == null)
        {
            return null;
        }

        String fcm_token = customerRepository.getCustomerFCMToken(notification.getUserId());
        System.out.println(fcm_token);
        if(fcm_token == null)
        {
            return null;
        }

        RequestConverterStrategy converterStrategy = new GetDirectionNotificationConverterStrategy();
        String userId = notification.getUserId();
        String notificationType = notification.getNotificationType();
        String payload = new String(converterStrategy.fromObjectToBytes(notification));

        Map<String, String> prop = new HashMap<>();
        prop.put(NotificationAttributes.USER_ID, userId);
        prop.put(NotificationAttributes.NOTIFICATION_TYPE, notificationType);
        prop.put(NotificationAttributes.PAYLOAD, payload);

        Message message = Message.builder()
                .putAllData(prop)
                .setToken(fcm_token)
                .build();
        String response = fcmRepository.sendMessageToDevice(message);
        return response;
    }

}
