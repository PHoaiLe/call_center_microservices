package org.com.LocatingService.Request.Receive;

import java.io.Serializable;

public class LocatingRequest implements Serializable
{
    private String userId;
    private String requestType;
    //    private String startLongitude;
//    private String startLatitude;
//    private String endLongitude;
//    private String endLatitude;
    private String startAddress;
    private String endAddress;
//    private String customerName;
//    private String phone;
//    private String vehicle;

    public LocatingRequest()
    {
        userId = "";
        requestType = "";
//        startLongitude = "";
//        startLatitude = "";
//        endLongitude = "";
//        endLatitude = "";
        startAddress = "";
        endAddress = "";
//        vehicle = "";
//        customerName = "";
//        phone = "";
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestType() {
        return requestType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

//    public String getVehicle() {
//        return vehicle;
//    }
//
//    public void setVehicle(String vehicle) {
//        this.vehicle = vehicle;
//    }

//    public String getStartLongitude() {
//        return startLongitude;
//    }
//
//    public void setStartLongitude(String startLongitude) {
//        this.startLongitude = startLongitude;
//    }
//
//    public String getStartLatitude() {
//        return startLatitude;
//    }
//
//    public void setStartLatitude(String startLatitude) {
//        this.startLatitude = startLatitude;
//    }
//
//    public String getEndLongitude() {
//        return endLongitude;
//    }
//
//    public void setEndLongitude(String endLongitude) {
//        this.endLongitude = endLongitude;
//    }
//
//    public String getEndLatitude() {
//        return endLatitude;
//    }
//
//    public void setEndLatitude(String endLatitude) {
//        this.endLatitude = endLatitude;
//    }

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

//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public void setCustomerName(String customerName)
//    {
//        this.customerName = customerName;
//    }

//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }


    @Override
    public String toString() {
        return "LocatingRequest{" +
                "userId='" + userId + '\'' +
                ", requestType='" + requestType + '\'' +
//                ", startLongitude='" + startLongitude + '\'' +
//                ", startLatitude='" + startLatitude + '\'' +
//                ", endLongitude='" + endLongitude + '\'' +
//                ", endLatitude='" + endLatitude + '\'' +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
//                ", customerName='" + customerName + '\'' +
//                ", phone='" + phone + '\'' +
//                ", vehicle='" + vehicle + '\'' +
                '}';
    }
}
