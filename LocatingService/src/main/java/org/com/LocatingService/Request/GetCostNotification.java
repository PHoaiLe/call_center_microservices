package org.com.LocatingService.Request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GetCostNotification implements Serializable
{
    private String notificationType = "NT00000001";

    private List<GetCostResponse> listOfCostResponse;

    private String idUser;

    public GetCostNotification()
    {
        listOfCostResponse = new ArrayList<>();
        idUser = "";
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public List<GetCostResponse> getListOfCostResponse() {
        return listOfCostResponse;
    }

    public void setListOfCostResponse(List<GetCostResponse> listOfCostResponse) {
        this.listOfCostResponse = listOfCostResponse;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }


    @Override
    public String toString() {
        return "GetCostNotification{" +
                "notificationType='" + notificationType + '\'' +
                ", listOfCostResponse=" + listOfCostResponse +
                ", idUser='" + idUser + '\'' +
                '}';
    }
}
