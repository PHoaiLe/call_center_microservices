package org.com.LocatingService.Request.Send;

import java.io.Serializable;
import java.util.List;

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
    private Double duration;
    private Double distance;
    private Double cost;

    public GetCostResponse()
    {
        vehicle = "";
        duration = 0.00;
        distance = 0.00;
        cost = 0.00;
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


}
