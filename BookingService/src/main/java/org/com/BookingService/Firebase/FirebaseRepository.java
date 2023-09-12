package org.com.BookingService.Firebase;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.com.BookingService.Firebase.Constant.CustomerConstants;
import org.com.BookingService.Firebase.Constant.DriverPartitionConstants;
import org.springframework.stereotype.Repository;

@Repository
public class FirebaseRepository
{

    public boolean updateFCMTokenForCustomer(String userId, String fcm_token)
    {
        Firestore firestore = FirestoreClient.getFirestore();
        try
        {
            firestore.collection(CustomerConstants.COLLECTION).document(userId)
                    .update(CustomerConstants.DOCUMENT_FCM_TOKEN, fcm_token);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public boolean updateFCMTokenForDriver(String userId, String fcm_token)
    {
        Firestore firestore = FirestoreClient.getFirestore();
        try
        {
            firestore.collection(DriverPartitionConstants.COLLECTION).document(userId)
                    .update(CustomerConstants.DOCUMENT_FCM_TOKEN, fcm_token);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }
}
