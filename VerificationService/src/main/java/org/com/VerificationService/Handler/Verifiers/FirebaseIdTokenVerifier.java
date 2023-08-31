package org.com.VerificationService.Handler.Verifiers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.com.VerificationService.Handler.Interfaces.Verifier;
import org.com.VerificationService.Request.Requests.ClientAppPickupRequest;
//
//Firebase Authentication sessions are long lived.
//Every time a user signs in, the user credentials are sent to the Firebase Authentication backend and exchanged for a Firebase ID token (a JWT) and refresh token.
// Firebase ID tokens are short lived and last for an hour; the refresh token can be used to retrieve new ID tokens.
// Refresh tokens expire only when one of the following occurs:
//        The user is deleted
//        The user is disabled
//        A major account change is detected for the user. This includes events like password or email address updates.
//The Firebase Admin SDK provides the ability to revoke refresh tokens for a specified user.
//In addition, an API to check for ID token revocation is also made available.
//With these capabilities, you have more control over user sessions.
//The SDK provides the ability to add restrictions to prevent sessions from being used in suspicious circumstances,
// as well as a mechanism for recovery from potential token theft.

public class FirebaseIdTokenVerifier implements Verifier
{
    private FirebaseAuth firebaseAuth;

    public FirebaseIdTokenVerifier()
    {
        firebaseAuth = FirebaseAuth.getInstance();
    }
    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public boolean execute(String token) {
        return false;
    }

    @Override
    public boolean execute(ClientAppPickupRequest request) {
        String idToken = request.getIdToken();
        try
        {
            if(firebaseAuth.verifyIdToken(idToken) != null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (FirebaseAuthException ex)
        {
            return false;
        }
    }
}
