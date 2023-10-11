package org.com.VerificationService.Handler;

import org.com.VerificationService.Handler.Interfaces.Verifier;
import org.com.VerificationService.Request.Requests.Receive.*;

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
        catch (Exception ex)
        {
            System.out.println(ex);
            return false;
        }

    }

    public boolean handle(CallCenterPickupRequest callCenterPickupRequest)
    {
        boolean result = false;
        if(verifier == null)
        {
            throw new NullPointerException(this.nullVerifier);
        }
        try
        {
            result = verifier.execute(callCenterPickupRequest);
            if(result == true && nextHandler != null)
            {
                return nextHandler.handle(callCenterPickupRequest);
            }

            return result;
        }
        catch (Exception ex)
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

    public boolean handle(GetCostRequest request)
    {
        boolean result = false;
        if(verifier == null)
        {
            throw new NullPointerException(this.nullVerifier);
        }
        try
        {
            result = verifier.execute(request);
            if(result == true && nextHandler != null)
            {
                return nextHandler.handle(request);
            }
            return result;
        }
        catch (Exception ex)
        {
            return false;
        }
    }

    public boolean handle(UpdateFCMToken request)
    {
        boolean result = false;
        if(verifier == null)
        {
            throw new NullPointerException(this.nullVerifier);
        }
        try
        {
            result = verifier.execute(request);
            if(result == true && nextHandler != null)
            {
                return nextHandler.handle(request);
            }
        }
        catch (Exception ex)
        {
            return false;
        }
        return result;
    }

    public boolean handle(GetDirectionRequest request)
    {
        boolean result = false;
        if(verifier == null)
        {
            throw new NullPointerException(this.nullVerifier);
        }
        try
        {
            result = verifier.execute(request);
            if(result == true && nextHandler != null)
            {
                return nextHandler.handle(request);
            }
        }
        catch (Exception ex)
        {
            return false;
        }
        return result;
    }

    public boolean handle(AcceptPickupRequest request)
    {
        boolean result = false;
        if(verifier == null)
        {
            throw new NullPointerException(this.nullVerifier);
        }
        try
        {
            result = verifier.execute(request);
            if(result == true && nextHandler != null)
            {
                return nextHandler.handle(request);
            }
        }
        catch (Exception ex)
        {
            return false;
        }
        return result;
    }
}
