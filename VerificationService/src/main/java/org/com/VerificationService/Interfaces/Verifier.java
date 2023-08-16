package org.com.VerificationService.Interfaces;

import com.google.firebase.auth.FirebaseAuthException;
import org.com.VerificationService.Request.ClientAppPickupRequest;

public interface Verifier
{
    boolean execute();
    boolean execute(String str);
    boolean execute(ClientAppPickupRequest request) throws FirebaseAuthException;

}
