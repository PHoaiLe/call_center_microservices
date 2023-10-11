package org.com.NotificationService.Requests.Requests.Others;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Geometry implements Serializable
{
    private List<Coordinate> coordinates;
    private String types;

    public Geometry()
    {
        coordinates = new ArrayList<>();
        types = "";
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "coordinates=" + coordinates +
                ", types='" + types + '\'' +
                '}';
    }
}
