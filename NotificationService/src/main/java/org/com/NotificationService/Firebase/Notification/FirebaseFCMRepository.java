package org.com.NotificationService.Firebase.Notification;

import com.google.firebase.messaging.*;
import org.springframework.stereotype.Repository;

@Repository
public class FirebaseFCMRepository {
    //according to Firebase Cloud Messaging Document
    private Integer MAX_MESSAGE_PER_INVOCATION = 500;

    public FirebaseFCMRepository() {
    }

    public String sendMessageToDevice(Message message) {
        try {
            String response = FirebaseMessaging.getInstance().send(message);
            return response;
        } catch (FirebaseMessagingException ex) {
            System.out.println(ex);
            return null;
        }
    }

    public Integer getMAX_MESSAGE_PER_INVOCATION() {
        return MAX_MESSAGE_PER_INVOCATION;
    }

}