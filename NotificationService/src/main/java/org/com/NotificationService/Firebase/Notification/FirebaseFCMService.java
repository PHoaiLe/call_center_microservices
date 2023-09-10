package org.com.NotificationService.Firebase.Notification;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.com.NotificationService.Firebase.Cusomer.FirebaseCustomerRepository;
import org.com.NotificationService.Requests.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.NotificationService.Requests.RequestStrategy.NotificationRequestConverterHandler;
import org.com.NotificationService.Requests.RequestStrategy.RequestConverterStrategy.GetCostNotificationConverterStrategy;
import org.com.NotificationService.Requests.Requests.GetCostNotification;
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
        props.put("userId", userId);
        props.put("notificationType", notificationType);
        props.put("payload", stringOfBytes);

        Message message = Message.builder()
                .putAllData(props)
                .setToken(fcm_token)
                .build();

        return fcmRepository.sendMessageToDevice(message);
    }


}
