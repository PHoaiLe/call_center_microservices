package org.com.Receiver.Request.ExternalRequests;

import org.com.Receiver.Request.Constants.RequestTypes;

import java.io.Serializable;

public class ExternalCallCenterPickupRequest implements Serializable
{
    private String systemKey;
    private String userId;
    private String name;
    private String startAddress;
    private String endAddress;
    private String phone;
    private String vehicle;
    private Double duration;
    private Double distance;
    private Double cost;
    private String time;


    public ExternalCallCenterPickupRequest()
    {
        systemKey = "";
        userId = "";
        name = "";
        startAddress = "";
        endAddress = "";
        phone = "";
        vehicle = "";
        duration = 0.00;
        distance = 0.00;
        cost = 0.00;
        time = "";
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
        return "ExternalCallCenterPickupRequest{" +
                "systemKey='" + systemKey + '\'' +
                ", userId='" + userId + '\'' +
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
}
