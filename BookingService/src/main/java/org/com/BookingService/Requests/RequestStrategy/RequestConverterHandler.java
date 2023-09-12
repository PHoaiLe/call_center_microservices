package org.com.BookingService.Requests.RequestStrategy;

import org.com.BookingService.Requests.RequestStrategy.Interfaces.RequestConverterStrategy;

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

    public void setStrategy(RequestConverterStrategy requestConverterStrategy)
    {
        strategy = requestConverterStrategy;
    }
}
