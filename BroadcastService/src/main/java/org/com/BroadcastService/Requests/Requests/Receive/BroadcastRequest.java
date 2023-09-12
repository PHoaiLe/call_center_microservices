package org.com.BroadcastService.Requests.Requests.Receive;


import org.com.BroadcastService.Firebase.Objects.DriverPartition;

import java.io.Serializable;
import java.util.List;

public class BroadcastRequest implements Serializable
{
    DriverPartitionRequest locatingRequestInfo;
    List<DriverPartition> listOfDrivers;


    public DriverPartitionRequest getLocatingRequestInfo() {
        return locatingRequestInfo;
    }

    public void setLocatingRequestInfo(DriverPartitionRequest locatingRequestInfo) {
        this.locatingRequestInfo = locatingRequestInfo;
    }

    public List<DriverPartition> getListOfDrivers() {
        return listOfDrivers;
    }

    public void setListOfDrivers(List<DriverPartition> listOfDrivers) {
        this.listOfDrivers = listOfDrivers;
    }

    @Override
    public String toString() {
        return "BroadcastRequest{" +
                "locatingRequestInfo=" + locatingRequestInfo +
                ", listOfDrivers=" + listOfDrivers +
                '}';
    }
}
