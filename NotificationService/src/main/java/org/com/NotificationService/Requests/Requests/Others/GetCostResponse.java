package org.com.NotificationService.Requests.Requests.Others;

import java.io.Serializable;

public class GetCostResponse implements Serializable
{
    @Override
    public String toString() {
        return "GetCostResponse{" +
                "vehicle='" + vehicle + '\'' +
                ", duration='" + duration + '\'' +
                ", distance='" + distance + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }

    private String vehicle;
    private String duration;
    private String distance;
    private String cost;

    public GetCostResponse()
    {
        vehicle = "";
        duration = "";
        distance = "";
        cost = "";
    }


    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }


}
