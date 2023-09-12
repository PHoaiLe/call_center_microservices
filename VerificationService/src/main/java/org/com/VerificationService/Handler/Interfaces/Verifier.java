package org.com.VerificationService.Handler.Interfaces;

import com.google.firebase.auth.FirebaseAuthException;
import org.com.VerificationService.Request.Requests.Receive.CallCenterPickupRequest;
import org.com.VerificationService.Request.Requests.Receive.ClientAppPickupRequest;
import org.com.VerificationService.Request.Requests.Receive.GetCostRequest;
import org.com.VerificationService.Request.Requests.Receive.UpdateFCMToken;

public interface Verifier
{
    boolean execute();
    boolean execute(String str);
    boolean execute(ClientAppPickupRequest request) throws FirebaseAuthException;
    boolean execute(CallCenterPickupRequest request);
    boolean execute(GetCostRequest request);
    boolean execute(UpdateFCMToken request);
}
