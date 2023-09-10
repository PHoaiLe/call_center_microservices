package org.com.NotificationService.Firebase.Cusomer;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.com.NotificationService.Firebase.Constant.CustomerConstants;
import org.com.NotificationService.Firebase.Constant.DriverPartitionConstant;

public class FirebaseCustomerRepository
{
    public String getCustomerFCMToken(String customerId)
    {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentSnapshot> apiFuture =  firestore.collection(CustomerConstants.COLLECTION)
                .document(customerId)
                .get();

        String result = null;

        if(apiFuture == null)
        {
            return null;
        }

        try
        {
            result = String.valueOf(apiFuture.get().get(CustomerConstants.DOCUMENT_FCM_TOKEN));
        }
        catch (Exception ex)
        {
            return null;
        }

        return result;
    }
}
