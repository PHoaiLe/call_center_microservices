package org.com.BookingService.Firebase.Objects;

import org.com.BookingService.Requests.Requests.Receive.AcceptPickupRequest;

public class BookingObject
{
    private String CustomerName;
    private String CustomerRole;
    private String DestinationLatitude;
    private String DestinationLongitude;
    private String DestinationName;
    private String PhoneNumber;
    private String PickupDate;
    private String PickupLocationLatitude;
    private String PickupLocationLongitude;
    private String PickupLocationName;
    private String PickupTime;
    private String Price;
    private String Status;
    private String Transport;

    public BookingObject()
    {
        CustomerName = "";
        CustomerRole = "";
        DestinationLatitude = "";
        DestinationLongitude = "";
        DestinationName = "";
        PhoneNumber = "";
        PickupDate = "";
        PickupLocationLatitude = "";
        PickupLocationLongitude = "";
        PickupLocationName = "";
        PickupTime = "";
        Price = "";
        Status = "";
        Transport = "";
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerRole() {
        return CustomerRole;
    }

    public void setCustomerRole(String customerRole) {
        CustomerRole = customerRole;
    }

    public String getDestinationLatitude() {
        return DestinationLatitude;
    }

    public void setDestinationLatitude(String destinationLatitude) {
        DestinationLatitude = destinationLatitude;
    }

    public String getDestinationLongitude() {
        return DestinationLongitude;
    }

    public void setDestinationLongitude(String destinationLongitude) {
        DestinationLongitude = destinationLongitude;
    }

    public String getDestinationName() {
        return DestinationName;
    }

    public void setDestinationName(String destinationName) {
        DestinationName = destinationName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getPickupDate() {
        return PickupDate;
    }

    public void setPickupDate(String pickupDate) {
        PickupDate = pickupDate;
    }

    public String getPickupLocationLatitude() {
        return PickupLocationLatitude;
    }

    public void setPickupLocationLatitude(String pickupLocationLatitude) {
        PickupLocationLatitude = pickupLocationLatitude;
    }

    public String getPickupLocationLongitude() {
        return PickupLocationLongitude;
    }

    public void setPickupLocationLongitude(String pickupLocationLongitude) {
        PickupLocationLongitude = pickupLocationLongitude;
    }

    public String getPickupLocationName() {
        return PickupLocationName;
    }

    public void setPickupLocationName(String pickupLocationName) {
        PickupLocationName = pickupLocationName;
    }

    public String getPickupTime() {
        return PickupTime;
    }

    public void setPickupTime(String pickupTime) {
        PickupTime = pickupTime;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getTransport() {
        return Transport;
    }

    public void setTransport(String transport) {
        Transport = transport;
    }

    @Override
    public String toString() {
        return "BookingObject{" +
                "CustomerName='" + CustomerName + '\'' +
                ", CustomerRole='" + CustomerRole + '\'' +
                ", DestinationLatitude='" + DestinationLatitude + '\'' +
                ", DestinationLongitude='" + DestinationLongitude + '\'' +
                ", DestinationName='" + DestinationName + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", PickupDate='" + PickupDate + '\'' +
                ", PickupLocationLatitude='" + PickupLocationLatitude + '\'' +
                ", PickupLocationLongitude='" + PickupLocationLongitude + '\'' +
                ", PickupLocationName='" + PickupLocationName + '\'' +
                ", PickupTime='" + PickupTime + '\'' +
                ", Price='" + Price + '\'' +
                ", Status='" + Status + '\'' +
                ", Transport='" + Transport + '\'' +
                '}';
    }

    public BookingObject(AcceptPickupRequest request)
    {
        CustomerName = request.getCustomerName();
        CustomerRole = "";
        DestinationLatitude = request.getEndLatitude();
        DestinationLongitude = request.getEndLongitude();
        DestinationName = request.getEndAddress();
        PhoneNumber = request.getPhone();
        PickupDate = "";
        PickupTime = "";
        PickupLocationLatitude = request.getStartLatitude();
        PickupLocationLongitude = request.getStartLongitude();
        PickupLocationName = request.getStartAddress();
        Price = String.valueOf(request.getCost());
        Status = "0";
        Transport = request.getVehicle();
    }
}
