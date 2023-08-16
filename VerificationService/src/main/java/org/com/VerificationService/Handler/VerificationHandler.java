package org.com.VerificationService.Handler;

import com.google.firebase.auth.FirebaseAuthException;
import org.com.VerificationService.Interfaces.Verifier;
import org.com.VerificationService.Request.ClientAppPickupRequest;

public class VerificationHandler
{
    private final String nullVerifier = "This handler doesnot have a verifier, please setVerifier() for the handler";
    private VerificationHandler nextHandler;
    private Verifier verifier;

    public VerificationHandler()
    {
        nextHandler = null;
        verifier = null;
    }

    public VerificationHandler setNextHandler(VerificationHandler handler)
    {
        this.nextHandler = handler;
        return this;
    }

    public VerificationHandler setVerifier(Verifier verifier)
    {
        this.verifier = verifier;
        return this;
    }

    //Overloading for each data structure
    public boolean handle(ClientAppPickupRequest clientAppPickupRequest){
        boolean result = false;
        if(verifier == null)
        {
            throw new NullPointerException(this.nullVerifier);
        }
        try
        {
            result = verifier.execute(clientAppPickupRequest);
            if(result == true && nextHandler != null)
            {
                return nextHandler.handle(clientAppPickupRequest);
            }

            return result;
        }
        catch (FirebaseAuthException ex)
        {
            System.out.println(ex);
            return false;
        }

    }

    //Overloading for each data structure
    public boolean handle(String str)
    {
        boolean result = false;
        return result;
    }

}
