package org.com.VerificationService.Handler.Interfaces;

import com.google.firebase.auth.FirebaseAuthException;
import org.com.VerificationService.Request.Requests.CallCenterPickupRequest;
import org.com.VerificationService.Request.Requests.ClientAppPickupRequest;

public interface Verifier
{
    boolean execute();
    boolean execute(String str);
    boolean execute(ClientAppPickupRequest request) throws FirebaseAuthException;

    boolean execute(CallCenterPickupRequest request);

}
