package org.com.VerificationService.Pickup;

import com.google.firebase.auth.FirebaseAuth;
import org.com.VerificationService.Handler.VerificationHandler;
import org.com.VerificationService.Handler.Verifiers.FirebaseIdTokenVerifier;
import org.com.VerificationService.Handler.Interfaces.Verifier;
import org.springframework.stereotype.Service;

@Service
public class VerificationServiceProvider
{
    private FirebaseAuth firebaseAuth;
    private VerificationHandler handler;

    public VerificationServiceProvider()
    {
        firebaseAuth = FirebaseAuth.getInstance();
        initializeVertificationHandler();
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
