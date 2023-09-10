package org.com.DriverPartitionService.Services;


import org.com.DriverPartitionService.Firebase.DriverPartition.DriverPartitionFirebaseService;
import org.com.DriverPartitionService.Firebase.Objects.DriverPartition;
import org.com.DriverPartitionService.Kafka.Constants.KafkaTopics;
import org.com.DriverPartitionService.Requests.BroadcastRequest;
import org.com.DriverPartitionService.Requests.DriverPartitionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.List;

@Service
public class DriverPartitionServiceProvider
{
    private ThreadPoolExecutor executor;
    @Autowired
    private KafkaTemplate<String, BroadcastRequest> broadcastRequestKafkaTemplate;

    public DriverPartitionServiceProvider()
    {
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public void execute(DriverPartitionRequest driverPartitionRequest)
    {
        if(driverPartitionRequest == null)
        {
            System.out.println("DriverPartitionServiceProvider::execute - input driverPartitionRequest is null");
            return;
        }
        Runnable task = getBackgroundTask(driverPartitionRequest);
        if(task == null)
        {
            return;
        }

        try
        {
            executor.submit(task);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
            return;
        }
    }

    private Runnable getBackgroundTask(DriverPartitionRequest driverPartitionRequest)
    {
        if(driverPartitionRequest == null)
        {
            return null;
        }

        Runnable task = new Runnable() {
            @Override
            public void run() {
                DriverPartitionRequest request = driverPartitionRequest;
                DriverPartitionFirebaseService service = new DriverPartitionFirebaseService();
                List<DriverPartition> listOfDriverPartition = service.getDriverPartitionBy(request.getStartAddress());
                //no available drivers in the closest area, expend the searching area
                if(listOfDriverPartition.size() == 0)
                {
                    System.out.println("closest area is not available");
                    listOfDriverPartition = service.getRelativeDriverPartitionBy(request.getStartAddress());

                    if(listOfDriverPartition.size() == 0)
                    {
                        System.out.println("relative area is not available");
                        //TODO: send notification back to Producer (a specific client or the call center by using userId in DriverPartitionRequest
                        return;
                    }
                }

                BroadcastRequest broadcastRequest = new BroadcastRequest();
                broadcastRequest.setLocatingRequestInfo(request);
                broadcastRequest.setListOfDrivers(listOfDriverPartition);
                //send broadcastRequest to BroadcastService
                System.out.println("broadcastRequest: " + broadcastRequest);
                broadcastRequestKafkaTemplate.send(KafkaTopics.BROADCAST, broadcastRequest);
            }
        };

        return task;
    }
}
