package org.com.VerificationService.Request.RequestStrategy;

import org.com.VerificationService.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.VerificationService.Request.RequestStrategy.RequestConverterStrategies.CallCenterPickupRequestConverterStrategy;
import org.com.VerificationService.Request.RequestStrategy.RequestConverterStrategies.ClientAppPickupRequestConverterStrategy;

public class RequestConverterHandler
{
    private RequestConverterStrategy strategy;

    public byte[] fromObjectToBytes(Object object)
    {
        return strategy.fromObjectToBytes(object);
    }

    public Object fromBytesToObject(byte[] bytes)
    {
        return strategy.fromByteToObject(bytes);
    }

    //setter of strategy

    public void setCallCenterPickupRequestConverterStrategy()
    {
        if(strategy == null)
        {
            throw new NullPointerException("strategy is null reference. Please set strategy first!");
        }
        strategy = new CallCenterPickupRequestConverterStrategy();
    }

    public void setClientAppPickupRequestConverterStrategy()
    {
        if(strategy == null)
        {
            throw new NullPointerException("strategy is null reference. Please set strategy first!");
        }
        strategy = new ClientAppPickupRequestConverterStrategy();
    }
}
