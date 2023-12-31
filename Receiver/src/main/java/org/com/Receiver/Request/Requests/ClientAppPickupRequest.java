package org.com.Receiver.Request.Requests;



import org.com.Receiver.Request.Constants.RequestTypes;
import org.com.Receiver.Request.ExternalRequests.ExternalClientAppPickupRequest;

import java.io.Serializable;

public class ClientAppPickupRequest implements Serializable
{
    private String idToken;
    private String systemKey;
    private String userId;
    private String requestType;
    private String name;

//    private String startLongitude;
//    private String startLatitude;
//    private String endLongitude;
//    private String endLatitude;
    private String startAddress;
    private String endAddress;
    private String phone;
    private String vehicle;
    private Double duration;
    private Double distance;
    private Double cost;
    private String time;

    public ClientAppPickupRequest()
    {
        requestType = RequestTypes.CLIENT_APP_NEW_PICK_UP_REQUEST;
        userId = "";
        name = "";
//        startLongitude = "";
//        startLatitude = "";
//        endLongitude = "";
//        endLatitude = "";
        startAddress = "";
        endAddress = "";
        phone = "";
        vehicle = "";
        duration = 0.00;
        distance = 0.00;
        cost = 0.00;
        time = "";
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ClientAppPickupRequest{" +
                "idToken='" + idToken + '\'' +
                ", systemKey='" + systemKey + '\'' +
                ", userId='" + userId + '\'' +
                ", requestType='" + requestType + '\'' +
                ", name='" + name + '\'' +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", vehicle='" + vehicle + '\'' +
                ", duration=" + duration +
                ", distance=" + distance +
                ", cost=" + cost +
                ", time='" + time + '\'' +
                '}';
    }

    public ClientAppPickupRequest(ExternalClientAppPickupRequest request)
    {
        this.idToken = request.getIdToken();
        this.systemKey = request.getSystemKey();
        this.userId = request.getUserId();
        this.requestType = RequestTypes.CLIENT_APP_NEW_PICK_UP_REQUEST;
        this.name = request.getName();
        this.startAddress = request.getStartAddress();
        this.endAddress = request.getEndAddress();
        this.phone = request.getPhone();
        this.vehicle = request.getVehicle();
        this.duration = request.getDuration();
        this.distance = request.getDistance();
        this.cost = request.getCost();
        this.time = request.getTime();
    }
}
