package org.com.NotificationService.Requests.RequestStrategy.Interfaces;

public interface RequestConverterStrategy
{
    byte[] fromObjectToBytes(Object object);
    Object fromBytesToObject(byte[] bytes);
    String getReferenceRequestType();
}
