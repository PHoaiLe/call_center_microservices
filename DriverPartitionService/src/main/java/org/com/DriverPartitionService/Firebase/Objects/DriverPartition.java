package org.com.DriverPartitionService.Firebase.Objects;

import java.io.Serializable;

public class DriverPartition implements Serializable
{
    private String driver_id;
    private String area_status;
    private String driver_status;
    private String fcm_token;
    private String partition_key;

    public DriverPartition()
    {
        driver_id = "";
        area_status = "";
        driver_status = "";
        fcm_token = "";
        partition_key = "";
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getArea_status() {
        return area_status;
    }

    public void setArea_status(String area_status) {
        this.area_status = area_status;
    }

    public String getDriver_status() {
        return driver_status;
    }

    public void setDriver_status(String driver_status) {
        this.driver_status = driver_status;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }

    public String getPartition_key() {
        return partition_key;
    }

    public void setPartition_key(String partition_key) {
        this.partition_key = partition_key;
    }

    @Override
    public String toString() {
        return "DriverPartitionFirestoreResponse{" +
                "driver_id='" + driver_id + '\'' +
                ", area_status='" + area_status + '\'' +
                ", driver_status='" + driver_status + '\'' +
                ", fcm_token='" + fcm_token + '\'' +
                ", partition_key='" + partition_key + '\'' +
                '}';
    }
}
