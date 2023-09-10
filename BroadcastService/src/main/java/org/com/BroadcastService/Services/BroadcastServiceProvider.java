package org.com.BroadcastService.Services;

import org.com.BroadcastService.Firebase.Broadcast.FirebaseFCMService;
import org.com.BroadcastService.Firebase.Objects.DriverPartition;
import org.com.BroadcastService.Requests.Requests.BroadcastRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class BroadcastServiceProvider
{
    private ThreadPoolExecutor executor;

    public BroadcastServiceProvider()
    {
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }

    public void execute(BroadcastRequest request)
    {
        if(request == null)
        {
            return;
        }
        Runnable task = getBackGroundExector(request);
        if(task == null)
        {
            //error request
            return;
        }
        try
        {
            executor.submit(task);
        }
        catch (RejectedExecutionException ree)
        {
            System.out.println(ree);
            //TODO: send to Failure handler service
        }

    }

    private Runnable getBackGroundExector(BroadcastRequest broadcastRequest)
    {
        if(broadcastRequest.getLocatingRequestInfo() == null || broadcastRequest.getListOfDrivers() == null)
        {
            return null;
        }

        Runnable task = new Runnable() {
            @Override
            public void run() {
                BroadcastRequest request = broadcastRequest;
                FirebaseFCMService service = new FirebaseFCMService();
                List<DriverPartition> errorMessages = service.sendMessageToMultipleDevices(request.getListOfDrivers(),request.getLocatingRequestInfo());
                //TODO: handle these error token
            }
        };

        return task;
    }

}
