package org.com.VerificationService.Request;



import org.com.VerificationService.Constants.RequestTypes;

import java.io.Serializable;

public class ClientAppPickupRequest implements Serializable
{
    private String idToken;
    private String systemKey;
    private String requestType;
    private String name;
    private String address;
    private String longitude;
    private String latitude;
    private String time;

    public ClientAppPickupRequest()
    {
        requestType = RequestTypes.CLIENT_APP_NEW_PICK_UP_REQUEST;
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

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
    }

    @Override
    public String toString() {
        return "ClientAppPickupRequest{" +
                "idToken='" + idToken + '\'' +
                ", systemKey=" + systemKey + '\''+
                ", requestType='" + requestType + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
