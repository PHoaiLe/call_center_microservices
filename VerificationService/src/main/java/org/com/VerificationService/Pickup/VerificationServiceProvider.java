package org.com.VerificationService.Pickup;

import org.com.VerificationService.Handler.VerificationHandler;
import org.com.VerificationService.Handler.Verifiers.FirebaseIdTokenVerifier;
import org.com.VerificationService.Handler.Interfaces.Verifier;
import org.springframework.stereotype.Service;

@Service
public class VerificationServiceProvider
{
    private VerificationHandler handler;

    public VerificationServiceProvider()
    {
        initializeVertificationHandler();
    }


    //using chain of responsibilities pattern
    private void initializeVertificationHandler()
    {
        //TODO: add VerificationHandler in here

        Verifier firebaseVertifier = new FirebaseIdTokenVerifier();
        VerificationHandler firebaseVertifierHandler = new VerificationHandler();
        firebaseVertifierHandler.setVerifier(firebaseVertifier);

        handler = firebaseVertifierHandler;
    }


    public VerificationHandler getVerificationHandler()
    {
        return handler;
    }

}
