package org.com.Receiver.Request.RequestStrategy.Interfaces;

public interface RequestConverterStrategy
{
    byte[] fromObjectToBytes(Object object);
    Object fromByteToObject(byte[] bytes);
}
