package org.com.VerificationService.Pickup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.NotNull;
import org.com.VerificationService.Handler.VerificationHandler;
import org.com.VerificationService.Handler.Verifiers.FirebaseIdTokenVerifier;
import org.com.VerificationService.Interfaces.Verifier;
import org.com.VerificationService.Request.ClientAppPickupRequest;
import org.com.VerificationService.Request.RequestWrapper;
import org.springframework.stereotype.Service;

@Service
public class PickupService
{
    private FirebaseAuth firebaseAuth;
    private VerificationHandler handler;

    public PickupService()
    {
        firebaseAuth = FirebaseAuth.getInstance();
        initializeVertificationHandler();
    }
    public void sendToLocationDefineService(@NotNull ClientAppPickupRequest clientAppPickupRequest)
    {
        //verify message is sent by the system and from legal user
        //using user.idToken
        boolean result = handler.handle(clientAppPickupRequest);
        System.out.println("Vertification of request's idtoken " + clientAppPickupRequest.getIdToken() + " is " + result);
    }

    //using chain of responsibilities pattern
    private void initializeVertificationHandler()
    {
        Verifier firebaseVertifier = new FirebaseIdTokenVerifier();
        VerificationHandler firebaseVertifierHandler = new VerificationHandler();
        firebaseVertifierHandler.setVerifier(firebaseVertifier);

        handler = firebaseVertifierHandler;
    }
}
