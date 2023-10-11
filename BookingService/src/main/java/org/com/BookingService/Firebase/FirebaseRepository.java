package org.com.BookingService.Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.com.BookingService.Firebase.Constant.AdminConstants;
import org.com.BookingService.Firebase.Constant.BookingRecord;
import org.com.BookingService.Firebase.Constant.CustomerConstants;
import org.com.BookingService.Firebase.Constant.DriverPartitionConstants;
import org.com.BookingService.Firebase.Objects.BookingObject;
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

    public boolean updateFCMTokenForAdmin(String userId, String fcm_token)
    {
        Firestore firestore = FirestoreClient.getFirestore();
        try
        {
            firestore.collection(AdminConstants.COLLECTION).document(userId)
                    .update(AdminConstants.DOCUMENT_FCM_TOKEN, fcm_token);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public boolean addBooking(BookingObject object)
    {
        try
        {
            Firestore firestore = FirestoreClient.getFirestore();
            ApiFuture<WriteResult> result =  firestore.collection(BookingRecord.BOOKING)
                        .document().set(object);
            System.out.println("add successfully...");
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }
}
