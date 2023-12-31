package org.com.Receiver.Request.RequestStrategy;

import org.com.Receiver.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.Receiver.Request.RequestStrategy.RequestConverterStrategies.*;

public class RequestConverterHandler
{
    private RequestConverterStrategy strategy;

    public byte[] fromObjectToBytes(Object object)
    {
        if(strategy == null)
        {
            throw new NullPointerException("strategy is null reference. Please set strategy first!");
        }
        return strategy.fromObjectToBytes(object);
    }

    public Object fromBytesToObject(byte[] bytes)
    {
        if(strategy == null)
        {
            throw new NullPointerException("strategy is null reference. Please set strategy first!");
        }
        return strategy.fromByteToObject(bytes);
    }

    //setter of strategy

    public void setCallCenterPickupRequestConverterStrategy()
    {
        strategy = new CallCenterPickupRequestConverterStrategy();
    }

    public void setAcceptPickupRequestConverterStrategy()
    {
        strategy = new AcceptRequestConverterStrategy();
    }

    public void setClientAppPickupRequestConverterStrategy()
    {
        strategy = new ClientAppPickupRequestConverterStrategy();
    }

    public void setUpdateFCMTokenRequestConverterStrategy()
    {
        strategy = new UpdateFCMRequestConverterStrategy();
    }

    public void setGetCostRequestConverterStrategy()
    {
        strategy = new GetCostRequestConverterStrategy();
    }

    public void setGetDirectionRequestConverterStrategy()
    {
        strategy = new GetDirectionRequestConverterStrategy();
    }
}
