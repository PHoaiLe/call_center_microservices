package org.com.NotificationService.Requests.RequestStrategy;

import org.com.NotificationService.Requests.RequestStrategy.Interfaces.RequestConverterStrategy;

public class NotificationRequestConverterHandler
{
    private RequestConverterStrategy requestConverterStrategy;

    public byte[] fromObjectToBytes(Object object)
    {
        if(requestConverterStrategy == null)
        {
            throw new NullPointerException("strategy is null reference. Please set strategy first!");
        }
        return requestConverterStrategy.fromObjectToBytes(object);
    }

    public Object fromBytesToObject(byte[] bytes)
    {
        if(requestConverterStrategy == null)
        {
            throw new NullPointerException("strategy is null reference. Please set strategy first!");
        }
        return requestConverterStrategy.fromBytesToObject(bytes);
    }

    //setter of strategy

    public void setStrategy(RequestConverterStrategy requestConverterStrategy)
    {
        this.requestConverterStrategy = requestConverterStrategy;
    }
}
