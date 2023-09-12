package org.com.BookingService.Firebase;

import org.com.BookingService.Firebase.Constant.FirebaseUserRole;
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
        else
        {
            return false;
        }
    }

}
