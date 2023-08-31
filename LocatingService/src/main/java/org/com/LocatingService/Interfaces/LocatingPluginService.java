package org.com.LocatingService.Interfaces;

import org.example.Objects.Coordinate;
import org.example.Objects.GeocodeResponse;

import java.util.List;

public interface LocatingPluginService
{
    Coordinate getCoordinateFromAddress(String address);
    List<Coordinate> getCoordinateListFromAddress(String address);
    GeocodeResponse getGeocodeResponseFromAddress(String address);
    List<GeocodeResponse> getGeocodeResponseListFromAddress(String address);
    void getDistanceAndTimeBetween(String option, String longitudeStart, String latitudeStart, String longitudeEnd, String latitudeEnd);
    void getDistanceAndTimeBetween(String option, Coordinate coordinateStart, Coordinate coordinateEnd);
}
