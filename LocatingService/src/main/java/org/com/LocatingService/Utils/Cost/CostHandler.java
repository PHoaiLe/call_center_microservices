package org.com.LocatingService.Utils.Cost;

import org.com.LocatingService.Kafka.Constants.VehicleTypes;

public class CostHandler
{
    private Double fundamentalPrice = 5000.00;
    private Double fuelFee = 20000.00;
    private Double kmPer1Fuel = 5.2;
    public Double calculateCostFromDistanceAndDuration(Double distanceInMeters, Double durationInSeconds, String vehicle)
    {
        //1l of gasoline = 20,000.00 --> 5.2 km
        //cost = distanceInMeters/1000 * 20,000.00 / 5.2;
        Double cost = (distanceInMeters / (1000 * kmPer1Fuel)) * fuelFee;
        Double additionalPrice = fundamentalPrice;
        if(vehicle.equals(VehicleTypes.CAR))
        {
            additionalPrice += additionalPrice*20.00/100.00;
        }
        cost = cost + additionalPrice;

        return cost;
    }
}
