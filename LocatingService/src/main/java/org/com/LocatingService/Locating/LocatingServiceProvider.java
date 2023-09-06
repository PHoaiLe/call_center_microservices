package org.com.LocatingService.Locating;


import org.com.LocatingService.Request.LocatingRequest;
import org.example.CallCenterLocatingService;
import org.example.LocatingPluginService;
import org.example.Objects.Coordinate.Coordinate;
import org.example.Objects.Direction.InnerParts.Properties.InnerProperties;
import org.example.Services.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class LocatingServiceProvider
{
    private ThreadPoolExecutor executor;
    private LocatingPluginService pluginService;

    @Autowired
    public LocatingServiceProvider(CallCenterLocatingService callCenterLocatingService)
    {
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        pluginService = callCenterLocatingService;
    }


    private Runnable availableLocationInformation(LocatingRequest locatingRequest)
    {
        if(locatingRequest == null)
        {
            return null;
        }

        Runnable task = new Runnable() {
            @Override
            public void run() {

            }
        };

        return task;
    }

    private Runnable notAvailableLocationInformation(LocatingRequest locatingRequest)
    {
        if(locatingRequest == null)
        {
            return null;
        }

        if(locatingRequest.getStartLongitude() != null && locatingRequest.getStartLatitude() != null
        && locatingRequest.getEndLongitude() != null && locatingRequest.getEndLatitude() != null)
        {
            return availableLocationInformation(locatingRequest);
        }

        Runnable task = new Runnable() {
            @Override
            public void run() {
                LocatingRequest request = locatingRequest;
                //call API
                Coordinate startCoordinate = pluginService.getCoordinateFromAddress(request.getStartAddress());
                //call API
                Coordinate endCoordinate = pluginService.getCoordinateFromAddress(request.getEndAddress());

                if(startCoordinate == null || endCoordinate == null)
                {
                    //TODO: send notification to producer

                    System.out.println("Invalid coordinate....");
                    return;
                }

                //call API
                InnerProperties properties = pluginService.getDistanceAndTimeBetween(DirectionService.BY_2_WHEELS, startCoordinate, endCoordinate);
                if(properties == null)
                {
                    //TODO: send notification to producer
                    System.out.println("Invalid direction information...");
                    return;
                }

                //TODO: calculate fee and send request to broadcast service

            }
        };

        return task;
    }

}
