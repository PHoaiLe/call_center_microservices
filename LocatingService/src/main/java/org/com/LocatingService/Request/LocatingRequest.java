package org.com.LocatingService.Request;

import java.io.Serializable;

public class LocatingRequest implements Serializable
{
    private String startLongitude;
    private String startLatitude;
    private String endLongitude;
    private String endLatitude;
    private String startAddress;
    private String endAddress;

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    private String vehicle;

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


    @Override
    public String toString() {
        return "LocatingRequest{" +
                "startLongitude='" + startLongitude + '\'' +
                ", startLatitude='" + startLatitude + '\'' +
                ", endLongitude='" + endLongitude + '\'' +
                ", endLatitude='" + endLatitude + '\'' +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                ", vehicle='" + vehicle + '\'' +
                '}';
    }
}
