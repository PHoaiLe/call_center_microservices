package org.com.Receiver.Request.ExternalRequests;

import org.com.Receiver.Request.Constants.RequestTypes;

import java.io.Serializable;

public class ExternalCallCenterPickupRequest implements Serializable
{

    private String systemKey;
    private String callCenterId;
    private String requestType;
    private String name;
    private String startAddress;
    private String endAddress;
    private String phone;
    private String vehicle;
    private String time;


    public ExternalCallCenterPickupRequest()
    {
        systemKey = "";
        requestType = RequestTypes.CALL_CENTER_NEW_PICK_UP_REQUEST;
        name = "";
        callCenterId = "";
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

    public String getCallCenterId() {
        return callCenterId;
    }

    public void setCallCenterId(String callCenterId) {
        this.callCenterId = callCenterId;
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
        return "ExternalCallCenterPickupRequest{" +
                "systemKey='" + systemKey + '\'' +
                ", callCenterId='" + callCenterId + '\'' +
                ", requestType='" + requestType + '\'' +
                ", name='" + name + '\'' +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", vehicle='" + vehicle + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
