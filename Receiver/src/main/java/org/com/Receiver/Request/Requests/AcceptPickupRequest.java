package org.com.Receiver.Request.Requests;


import org.com.Receiver.Request.Constants.RequestTypes;
import org.com.Receiver.Request.ExternalRequests.ExternalAcceptPickupRequest;

import java.io.Serializable;

public class AcceptPickupRequest implements Serializable
{
    private String idToken;
    private String systemKey;
    private String requestType;
    private String partnerId;
    private String userId;
    private String customerName;

    private String startLongitude;
    private String startLatitude;
    private String endLongitude;
    private String endLatitude;


    private String startAddress;
    private String endAddress;

    private String phone;
    private String vehicle;
    private Double duration;
    private Double distance;
    private Double cost;


    public AcceptPickupRequest()
    {
        idToken = "";
        systemKey = "";
        requestType = RequestTypes.DRIVER_ACCEPT_PICKUP_REQUEST;
        partnerId = "";
        userId = "";
        customerName = "";

        startLongitude = "";
        startLatitude = "";
        endLongitude = "";
        endLatitude = "";


        startAddress = "";
        endAddress = "";

        phone = "";
        vehicle = "";
        duration = 0.00;
        distance = 0.00;
        cost = 0.00;
    }

    public AcceptPickupRequest(ExternalAcceptPickupRequest request)
    {
        idToken = request.getIdToken();
        systemKey = request.getSystemKey();
        requestType = RequestTypes.DRIVER_ACCEPT_PICKUP_REQUEST;
        partnerId = request.getPartnerId();
        userId = request.getUserId();
        customerName = request.getCustomerName();

        startLongitude = request.getStartLongitude();
        startLatitude = request.getStartLatitude();
        endLongitude = request.getEndLongitude();
        endLatitude = request.getEndLatitude();


        startAddress = request.getStartAddress();
        endAddress = request.getEndAddress();

        phone = request.getPhone();
        vehicle = request.getVehicle();
        duration = request.getDuration();
        distance = request.getDistance();
        cost = request.getCost();
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

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    @Override
    public String toString() {
        return "AcceptPickupRequest{" +
                "idToken='" + idToken + '\'' +
                ", systemKey='" + systemKey + '\'' +
                ", requestType='" + requestType + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", userId='" + userId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", startLongitude='" + startLongitude + '\'' +
                ", startLatitude='" + startLatitude + '\'' +
                ", endLongitude='" + endLongitude + '\'' +
                ", endLatitude='" + endLatitude + '\'' +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                ", phone='" + phone + '\'' +
                ", vehicle='" + vehicle + '\'' +
                ", duration=" + duration +
                ", distance=" + distance +
                ", cost=" + cost +
                '}';
    }
}
