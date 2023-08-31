package org.com.VerificationService.Request.Requests;

import org.com.VerificationService.Request.Constants.RequestTypes;

import java.io.Serializable;

public class CallCenterPickupRequest implements Serializable
{
    private String systemKey;
    private String requestType;
    private String name;
    private String startLongitude;
    private String startLatitude;
    private String endLongitude;
    private String endLatitude;
    private String startAddress;
    private String endAddress;
    private String phone;
    private String vehicle;
    private String time;

    public CallCenterPickupRequest()
    {
        requestType = RequestTypes.CALL_CENTER_NEW_PICK_UP_REQUEST;
        name = "";
        startLongitude = "";
        startLatitude = "";
        endLongitude = "";
        endLatitude = "";
        startAddress = "";
        endAddress = "";
        phone = "";
        vehicle = "";
        time = "";
    }

    public String getSystemKey() {
        return systemKey;
    }

    public void setSystemKey(String systemKey) {
        this.systemKey = systemKey;
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

    public String getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(String startLongitude) {
        this.startLongitude = startLongitude;
    }

    public String getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(String startLatitude) {
        this.startLatitude = startLatitude;
    }

    public String getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(String endLongitude) {
        this.endLongitude = endLongitude;
    }

    public String getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(String endLatitude) {
        this.endLatitude = endLatitude;
    }

    public String getStartAddress() {
        return startAddress;
    }

    public void setStartAddress(String startAddress) {
        this.startAddress = startAddress;
    }

    public String getEndAddress() {
        return endAddress;
    }

    public void setEndAddress(String endAddress) {
        this.endAddress = endAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "CallCenterPickupRequest{" +
                "systemKey='" + systemKey + '\'' +
                ", requestType='" + requestType + '\'' +
                ", name='" + name + '\'' +
                ", startLongitude='" + startLongitude + '\'' +
                ", startLatitude='" + startLatitude + '\'' +
                ", endLongitude='" + endLongitude + '\'' +
                ", endLatitude='" + endLatitude + '\'' +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", vehicle='" + vehicle + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public void CopyFromObject(Object object)
    {
        CallCenterPickupRequest externalObject =  (CallCenterPickupRequest) object;
        this.setSystemKey(externalObject.systemKey);
        this.setRequestType(externalObject.requestType);
        this.setName(externalObject.name);
        this.setStartLongitude(externalObject.startLongitude);
        this.setStartLatitude(externalObject.startLatitude);
        this.setEndLongitude(externalObject.endLongitude);
        this.setEndLatitude(externalObject.endLatitude);
        this.setStartAddress(externalObject.startAddress);
        this.setEndAddress(externalObject.endAddress);
        this.setPhone(externalObject.phone);
        this.setVehicle(externalObject.vehicle);
        this.setTime(externalObject.time);
    }
}
