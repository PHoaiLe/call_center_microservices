package org.com.DriverPartitionService.Requests;

import java.io.Serializable;

public class DriverPartitionRequest implements Serializable
{
    private String userId;
    private String startAddress;
    private String endAddress;
    private String customerName;
    private String phone;
    private String vehicle;
    private Double duration;
    private Double distance;
    private Double cost;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        this.startAddress = "";
        this.endAddress = "";
        this.customerName = "";
        this.phone = "";
        this.vehicle = "";
        this.duration =0.00;
        this.distance = 0.00;
        this.cost = 0.00;
    }

}
