package org.com.Receiver.Request.Requests;


import java.io.Serializable;

public class AcceptPickupRequest implements Serializable
{
    private String userId;
    private String name;

    public String getName()
    {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    @Override
    public String toString() {
        return "AcceptPickupRequest{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
