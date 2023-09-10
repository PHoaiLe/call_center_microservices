package org.com.BroadcastService.Firebase.Broadcast;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.messaging.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirebaseFCMRepository
{
    //according to Firebase Cloud Messaging Document
    private Integer MAX_MESSAGE_PER_INVOCATION = 500;

    public FirebaseFCMRepository()
    {}

    public BatchResponse sendMessageToMutipleDevices(MulticastMessage message)
    {
        try
        {
            BatchResponse response = FirebaseMessaging.getInstance().sendMulticast(message);
            return response;
        }
        catch (FirebaseMessagingException ex)
        {
            System.out.println(ex);
            return null;
        }
    }

    public Integer getMAX_MESSAGE_PER_INVOCATION()
    {
        return MAX_MESSAGE_PER_INVOCATION;
    }
}
