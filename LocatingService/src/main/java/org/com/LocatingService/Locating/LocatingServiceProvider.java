package org.com.LocatingService.Locating;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.LocatingService.Kafka.Constants.KafkaTopics;
import org.com.LocatingService.Kafka.Constants.VehicleTypes;
import org.com.LocatingService.Request.*;
import org.com.LocatingService.Request.Constants.NotificationTypes;
import org.com.LocatingService.Request.Constants.RequestTypes;
import org.com.LocatingService.Request.Receive.LocatingRequest;
import org.com.LocatingService.Request.Send.GetCostNotification;
import org.com.LocatingService.Request.Send.GetCostResponse;
import org.com.LocatingService.Request.Send.GetDirectionDetailNotification;
import org.com.LocatingService.Request.Send.InvalidLocationInfoNotification;
import org.com.LocatingService.Utils.Cost.CostHandler;
import org.example.locatingdependency.CallCenterLocatingService;
import org.example.locatingdependency.LocatingPluginService;
import org.example.locatingdependency.Objects.Coordinate.Coordinate;
import org.example.locatingdependency.Objects.Direction.InnerParts.Geometry.Geometry;
import org.example.locatingdependency.Objects.Direction.InnerParts.Properties.InnerProperties;
import org.example.locatingdependency.Services.DirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class LocatingServiceProvider
{
    private ThreadPoolExecutor executor;
    private LocatingPluginService pluginService;
    @Autowired
    private KafkaTemplate<String, NotificationWrapper> notificationWrapperKafkaTemplate;

    public LocatingServiceProvider() throws Exception
    {
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        pluginService = new CallCenterLocatingService();
    }

    public void execute(LocatingRequest locatingRequest)
    {
        System.out.println("inside execute function");
        Runnable task = null;
        System.out.println(locatingRequest);
        if(locatingRequest.getRequestType().equals(RequestTypes.GET_COST_REQUEST))
        {
            task = getCostRunnable(locatingRequest);
        }
        else if(locatingRequest.getRequestType().equals(RequestTypes.GET_DIRECTION_REQUEST))
        {
            task = getDirectionRunnable(locatingRequest);
        }

        if(task == null)
        {
            return;
        }
        try
        {
            executor.submit(task);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            //TODO: send to failed tasks in the future
        }
    }


//    private Runnable availableLocationInformation(LocatingRequest locatingRequest)
//    {
//        if(locatingRequest == null)
//        {
//            return null;
//        }
//        System.out.println("available infor");
//        Runnable task = new Runnable() {
//            @Override
//            public void run() {
//                LocatingRequest request = locatingRequest;
//                //call API
//                InnerProperties properties = null;
//                if(request.getVehicle().equals(VehicleTypes.MOTO))
//                {
//                    System.out.println("MOTO");
//                    properties = pluginService.getDistanceAndTimeBetween(DirectionService.BY_2_WHEELS, request.getStartLongitude(), request.getStartLatitude(), request.getEndLongitude(), request.getEndLatitude());
//                }
//                else if(request.getVehicle().equals(VehicleTypes.CAR))
//                {
//                    System.out.println("CAR");
//                    properties = pluginService.getDistanceAndTimeBetween(DirectionService.BY_4_WHEELS, request.getStartLongitude(), request.getStartLatitude(), request.getEndLongitude(), request.getEndLatitude());
//                }
//
//                //TODO: calculate cost and send request to driver-partition service
//                CostHandler costHandler = new CostHandler();
//                Double cost = costHandler.calculateCostFromDistanceAndDuration(properties.getSummary().getDistance(), properties.getSummary().getDuration());
//                DriverPartitionRequest driverPartitionRequest = new DriverPartitionRequest(request);
//                driverPartitionRequest.setDistance(properties.getSummary().getDistance());
//                driverPartitionRequest.setDuration(properties.getSummary().getDuration());
//                driverPartitionRequest.setCost(cost);
//
//                System.out.println("ready to send...");
//                driverPartitionRequestKafkaTemplate.send(KafkaTopics.DRIVER_PARTITION, driverPartitionRequest);
//                System.out.println("after sending driverPartitionRequest to DriverPartitionService...");
//            }
//        };
//
//        return task;
//    }

    private Runnable getCostRunnable(LocatingRequest locatingRequest)
    {
        if(locatingRequest == null)
        {
            return null;
        }

        //TODO:check if coordinate is empty

        System.out.println("not available info");
        Runnable task = new Runnable()
        {
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
                    InvalidLocationInfoNotification notification = new InvalidLocationInfoNotification();
                    notification.setUserId(request.getUserId());
                    notification.setStartAddress(request.getStartAddress());
                    notification.setEndAddress(request.getEndAddress());

                    ObjectMapper mapper = new ObjectMapper();
                    byte[] bytes = null;
                    try
                    {
                        bytes = mapper.writeValueAsBytes(notification);
                    }
                    catch (Exception ex)
                    {
                        return;
                    }

                    NotificationWrapper wrapper = new NotificationWrapper();
                    wrapper.setNotificationType(notification.getNotificationType());
                    wrapper.setPayload(bytes);
                    notificationWrapperKafkaTemplate.send(KafkaTopics.NOTIFICATION, wrapper);

                    System.out.println("Invalid coordinate....");
                    return;
                }

                //call API
                InnerProperties twoWheelProperty = pluginService.getDistanceAndTimeBetween(DirectionService.BY_2_WHEELS, startCoordinate, endCoordinate);
                InnerProperties fourWheelProperty = pluginService.getDistanceAndTimeBetween(DirectionService.BY_4_WHEELS, startCoordinate, endCoordinate);

                if(twoWheelProperty == null || fourWheelProperty == null)
                {
                    //TODO: send notification to producer
                    System.out.println("Invalid direction information...");
                    return;
                }

                //TODO: calculate cost and send request to driver-partition service
                CostHandler costHandler = new CostHandler();

                List<GetCostResponse> costResponses = new ArrayList<>();

                GetCostResponse twoWheelCostResponse = new GetCostResponse();
                twoWheelCostResponse.setCost(costHandler.calculateCostFromDistanceAndDuration(twoWheelProperty.getSummary().getDistance(), twoWheelProperty.getSummary().getDuration(), "MOTO"));
                twoWheelCostResponse.setDistance(twoWheelProperty.getSummary().getDistance());
                twoWheelCostResponse.setDuration(twoWheelProperty.getSummary().getDuration());
                twoWheelCostResponse.setVehicle(VehicleTypes.MOTO);

                GetCostResponse fourWheelResponse = new GetCostResponse();
                fourWheelResponse.setCost(costHandler.calculateCostFromDistanceAndDuration(fourWheelProperty.getSummary().getDistance(), fourWheelProperty.getSummary().getDuration(), "CAR"));
                fourWheelResponse.setVehicle(VehicleTypes.CAR);
                fourWheelResponse.setDuration(fourWheelProperty.getSummary().getDuration());
                fourWheelResponse.setDistance(fourWheelProperty.getSummary().getDistance());

                costResponses.add(twoWheelCostResponse);
                costResponses.add(fourWheelResponse);

                GetCostNotification costNotification = new GetCostNotification();
                costNotification.setListOfCostResponse(costResponses);
                costNotification.setIdUser(request.getUserId());

                NotificationWrapper wrapper = new NotificationWrapper();
                wrapper.setPayload(getBytesFrom(costNotification));
                wrapper.setNotificationType(costNotification.getNotificationType());

                //send to NotificationService
                notificationWrapperKafkaTemplate.send(KafkaTopics.NOTIFICATION, wrapper);
                System.out.println("after sending...");
            }
        };

        return task;
    }

    private byte[] getBytesFrom(GetCostNotification costNotification)
    {
        if(costNotification == null)
        {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = null;
        try
        {
            bytes = objectMapper.writeValueAsBytes(costNotification);
        }
        catch (Exception ex)
        {
            return null;
        }
        return bytes;
    }


    private Runnable getDirectionRunnable(LocatingRequest locatingRequest)
    {
        if(locatingRequest == null)
        {
            return null;
        }
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("inside get direction task...");

                Coordinate start = pluginService.getCoordinateFromAddress(locatingRequest.getStartAddress());
                Coordinate end = pluginService.getCoordinateFromAddress(locatingRequest.getEndAddress());

                Geometry geometry = pluginService.getDirectionDetail(DirectionService.BY_2_WHEELS, start, end);


                GetDirectionDetailNotification notification = new GetDirectionDetailNotification();
                notification.setUserId(locatingRequest.getUserId());
                notification.setGeometry(geometry);
                notification.setNotificationType(NotificationTypes.GET_DIRECTION_NOTIFICATION);

                ObjectMapper mapper = new ObjectMapper();
                byte[] payload = null;
                try
                {
                    payload = mapper.writeValueAsBytes(notification);
                }
                catch (Exception ex)
                {
                    payload = null;
                }

                NotificationWrapper wrapper = new NotificationWrapper();
                wrapper.setNotificationType(NotificationTypes.GET_DIRECTION_NOTIFICATION);
                wrapper.setPayload(payload);
                notificationWrapperKafkaTemplate.send(KafkaTopics.NOTIFICATION, wrapper);
                System.out.println("after sending direction request to notification service...");
            }
        };

        return task;
    }

}
