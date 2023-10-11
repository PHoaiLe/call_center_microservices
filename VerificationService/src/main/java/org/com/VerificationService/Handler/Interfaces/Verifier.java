package org.com.VerificationService.Handler.Interfaces;

import com.google.firebase.auth.FirebaseAuthException;
import org.com.VerificationService.Request.Requests.Receive.*;

public interface Verifier
{
    boolean execute();
    boolean execute(String str);
    boolean execute(ClientAppPickupRequest request) throws FirebaseAuthException;
    boolean execute(CallCenterPickupRequest request);
    boolean execute(GetCostRequest request);
    boolean execute(UpdateFCMToken request);
    boolean execute(GetDirectionRequest request);
    boolean execute(AcceptPickupRequest request);
}
