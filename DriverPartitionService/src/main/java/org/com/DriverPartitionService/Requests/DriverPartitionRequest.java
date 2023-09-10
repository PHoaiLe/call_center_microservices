package org.com.DriverPartitionService.Requests;

import java.io.Serializable;

public class DriverPartitionRequest implements Serializable
{
    private String userId;
    private String requestType;
    private String startLongitude;
    private String startLatitude;
    private String endLongitude;
    private String endLatitude;
    private String startAddress;
    private String endAddress;
    private String customerName;
    private String phone;
    private String vehicle;
    private Double duration;
    private Double distance;
    private Double cost;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
        return "DriverPartitionRequest{" +
                "userId='" + userId + '\'' +
                ", requestType='" + requestType + '\'' +
                ", startLongitude='" + startLongitude + '\'' +
                ", startLatitude='" + startLatitude + '\'' +
                ", endLongitude='" + endLongitude + '\'' +
                ", endLatitude='" + endLatitude + '\'' +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                ", customerName='" + customerName + '\'' +
                ", phone='" + phone + '\'' +
                ", vehicle='" + vehicle + '\'' +
                ", duration='" + duration + '\'' +
                ", distance='" + distance + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }

    public DriverPartitionRequest()
    {
        this.userId = "";
        this.requestType = "";
        this.startLongitude = "";
        this.startLatitude = "";
        this.startAddress = "";
        this.endLongitude = "";
        this.endLatitude = "";
        this.endAddress = "";
        this.customerName = "";
        this.phone = "";
        this.vehicle = "";
        this.duration = 0.00;

    }

}
