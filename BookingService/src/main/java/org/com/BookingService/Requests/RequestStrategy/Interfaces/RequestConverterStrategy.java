package org.com.BookingService.Requests.RequestStrategy.Interfaces;

public interface RequestConverterStrategy
{
    byte[] fromObjectToBytes(Object object);
    Object fromByteToObject(byte[] bytes);
    String getReferenceRequestType();

}
