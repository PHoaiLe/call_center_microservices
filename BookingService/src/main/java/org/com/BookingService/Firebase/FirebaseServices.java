package org.com.BookingService.Firebase;

import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.internal.FirebaseTokenFactory;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import org.com.BookingService.Firebase.Constant.FirebaseUserRole;
import org.com.BookingService.Firebase.Objects.BookingObject;
import org.com.BookingService.Requests.Requests.Receive.AcceptPickupRequest;
import org.springframework.stereotype.Service;

@Service
public class FirebaseServices
{
    private FirebaseRepository repository;

    public FirebaseServices()
    {
        repository = new FirebaseRepository();
    }

    public boolean updateFCMToken(String role, String userId, String fcm_token)
    {
        if(role == null || userId == null || fcm_token == null)
        {
            return false;
        }

        if(role.equals(FirebaseUserRole.CUSTOMER))
        {
            return repository.updateFCMTokenForCustomer(userId, fcm_token);
        }
        else if(role.equals(FirebaseUserRole.DRIVER))
        {
            return repository.updateFCMTokenForDriver(userId, fcm_token);
        }
        else if(role.equals(FirebaseUserRole.CENTER))
        {
            return repository.updateFCMTokenForAdmin(userId, fcm_token);
        }
        else
        {
            return false;
        }
    }

    public boolean addPickUpInfor(AcceptPickupRequest request)
    {
        if(request == null)
        {
            return false;
        }

        BookingObject object = new BookingObject(request);
        return repository.addBooking(object);
    }

}
