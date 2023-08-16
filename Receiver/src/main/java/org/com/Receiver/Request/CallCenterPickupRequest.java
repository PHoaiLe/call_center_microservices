package org.com.Receiver.Request;

import org.com.Receiver.Constants.RequestTypes;

import java.io.Serializable;

public class CallCenterPickupRequest implements Serializable
{

    private String systemKey;
    private String requestType;
    private String name;
    private String address;
    private String longitude;
    private String latitude;
    private String time;

    public CallCenterPickupRequest()
    {
        requestType = RequestTypes.CALL_CENTER_NEW_PICK_UP_REQUEST;
        name = "";
        address = "";
        longitude = "";
        latitude = "";
        time = "";
    }


    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSystemKey() {
        return systemKey;
    }

    private void setSystemKey(String idToken) {
        this.systemKey = systemKey;
    }

    @Override
    public String toString() {
        return "CallCenterPickupRequest{" +
                "systemKey='" + systemKey + '\'' +
                ", requestType='" + requestType + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

}
