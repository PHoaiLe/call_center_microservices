package org.com.VerificationService.Pickup;

import org.com.VerificationService.Handler.VerificationHandler;
import org.com.VerificationService.Kafka.Constants.KafkaTopics;
import org.com.VerificationService.Kafka.ProducerConfig.LocatingRequestKafkaProducerConfig;
import org.com.VerificationService.Pickup.FunctionInterface.GetRunnable_BytesParam_Function;
import org.com.VerificationService.Request.Constants.RequestTypes;
import org.com.VerificationService.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.VerificationService.Request.RequestStrategy.RequestConverterStrategyProvider;
import org.com.VerificationService.Request.Requests.*;
import org.com.VerificationService.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class PickupServiceProvider
{
    //HashMap<RequestType, AsyncTask-Service-Provider> serviceProvider;
    private HashMap<String, GetRunnable_BytesParam_Function> serviceProvider;
    private ThreadPoolExecutor executor;

    @Autowired
    private KafkaTemplate<String, LocatingRequest> locatingRequestKafkaTemplate;
    @Autowired
    private KafkaTemplate<String, DriverPartitionRequest> driverPartitionRequestKafkaTemplate;

    public PickupServiceProvider()
    {
        serviceProvider = new HashMap<>();
        /*Creates a thread pool that creates new threads as needed,
        but will reuse previously constructed threads when they are available.
        These pools will typically improve the performance of programs that execute many short-lived asynchronous tasks.
        Calls to execute will reuse previously constructed threads if available.
        If no existing thread is available, a new thread will be created and added to the pool.
        Threads that have not been used for sixty seconds are terminated and removed from the cache.
        Thus, a pool that remains idle for long enough will not consume any resources.
        Note that pools with similar properties but different details
        (for example, timeout parameters) may be created using ThreadPoolExecutor constructors.*/
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();


        initialize();
    }


    private void initialize()
    {
        //TODO: Add service providers in here
        serviceProvider.put(RequestTypes.CLIENT_APP_NEW_PICK_UP_REQUEST, ClientAppNewPickupRequestTask);
        serviceProvider.put(RequestTypes.CALL_CENTER_NEW_PICK_UP_REQUEST, CallCenterRequestRunnable);
        serviceProvider.put(RequestTypes.GET_COST_REQUEST, GetCostRequestRunnable);
    }

    public void execute(String requestType, byte[] payload)
    {
        GetRunnable_BytesParam_Function function = serviceProvider.get(requestType);
        if(function == null)
        {
            //wrong requestType
            return;
        }
        Runnable runnable = function.getRunnable(payload);
        if(runnable == null)
        {
            return;
        }
        try
        {
            executor.submit(runnable);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return;
        }
    }


    //service provided by PickupServiceProvider, omit IF condition for checking requestType variable

    //service for handling ClientAppPickupRequest
    private GetRunnable_BytesParam_Function ClientAppNewPickupRequestTask = (payload) ->
    {
        Runnable task = new Runnable()
        {
            @Override
            public void run()
            {
                String requestType = RequestTypes.CLIENT_APP_NEW_PICK_UP_REQUEST;
                RequestConverterStrategyProvider converterStrategyProvider = new RequestConverterStrategyProvider();

                RequestConverterStrategy converterStrategy = converterStrategyProvider.getRequestConverterStrategy(requestType);
                ClientAppPickupRequest clientAppPickupRequest = (ClientAppPickupRequest) converterStrategy.fromByteToObject(payload);

                //for practice
                //boolean check = verificationServiceProvider.getVerificationHandler().handle(clientAppPickupRequest);

                //for testing
                boolean check = true;


                if(check == true)
                {
                    DriverPartitionRequest driverPartitionRequest = new DriverPartitionRequest();

                    //assign value to driverPartitionRequest
                    driverPartitionRequest.setUserId(clientAppPickupRequest.getUserId());
                    driverPartitionRequest.setStartAddress(clientAppPickupRequest.getStartAddress());
                    driverPartitionRequest.setEndAddress(clientAppPickupRequest.getEndAddress());
                    driverPartitionRequest.setCustomerName(clientAppPickupRequest.getName());
                    driverPartitionRequest.setPhone(clientAppPickupRequest.getPhone());
                    driverPartitionRequest.setVehicle(clientAppPickupRequest.getVehicle());
                    driverPartitionRequest.setDuration(clientAppPickupRequest.getDuration());
                    driverPartitionRequest.setDistance(clientAppPickupRequest.getDistance());
                    driverPartitionRequest.setCost(clientAppPickupRequest.getCost());

                    driverPartitionRequestKafkaTemplate.send(KafkaTopics.DRIVER_PARTITION, driverPartitionRequest);
                    System.out.println("After sending to driver partition");
                }
            }
        };
        return task;
    };

    private GetRunnable_BytesParam_Function CallCenterRequestRunnable = (payload) ->
    {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                String requestType = RequestTypes.CALL_CENTER_NEW_PICK_UP_REQUEST;
                VerificationServiceProvider verificationServiceProvider = new VerificationServiceProvider();
                RequestConverterStrategyProvider converterStrategyProvider = new RequestConverterStrategyProvider();

                RequestConverterStrategy converterStrategy = converterStrategyProvider.getRequestConverterStrategy(requestType);
                CallCenterPickupRequest callCenterPickupRequest = (CallCenterPickupRequest) converterStrategy.fromByteToObject(payload);
                System.out.println("Callcenter Runnable: " + callCenterPickupRequest);
                boolean check = verificationServiceProvider.getVerificationHandler().handle(callCenterPickupRequest);
                if(check == true)
                {
                    DriverPartitionRequest request = new DriverPartitionRequest();

                    request.setUserId(callCenterPickupRequest.getUserId());
                    request.setStartAddress(callCenterPickupRequest.getStartAddress());
                    request.setEndAddress(callCenterPickupRequest.getEndAddress());
                    request.setCustomerName(callCenterPickupRequest.getName());
                    request.setPhone(callCenterPickupRequest.getPhone());
                    request.setVehicle(callCenterPickupRequest.getVehicle());
                    request.setDuration(callCenterPickupRequest.getDuration());
                    request.setDistance(callCenterPickupRequest.getDistance());
                    request.setCost(callCenterPickupRequest.getCost());

                    driverPartitionRequestKafkaTemplate.send(KafkaTopics.DRIVER_PARTITION, request);
                }
            }
        };
        return task;
    };

    private GetRunnable_BytesParam_Function GetCostRequestRunnable = (payload) ->
    {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                String requestType = RequestTypes.GET_COST_REQUEST;
                VerificationServiceProvider verificationServiceProvider = new VerificationServiceProvider();
                RequestConverterStrategyProvider requestConverterStrategyProvider = new RequestConverterStrategyProvider();

                RequestConverterStrategy requestConverterStrategy = requestConverterStrategyProvider.getRequestConverterStrategy(requestType);
                GetCostRequest getCostRequest = (GetCostRequest) requestConverterStrategy.fromByteToObject(payload);
//                boolean check = verificationServiceProvider.getVerificationHandler().handle(getCostRequest);
                boolean check = true;
                if(check == true)
                {
                    LocatingRequest locatingRequest = new LocatingRequest();
                    locatingRequest.setUserId(getCostRequest.getUserId());
                    locatingRequest.setRequestType(getCostRequest.getRequestType());
                    locatingRequest.setStartAddress(getCostRequest.getStartAddress());
                    locatingRequest.setEndAddress(getCostRequest.getEndAddress());

                    locatingRequestKafkaTemplate.send(KafkaTopics.LOCATING, locatingRequest);
                    System.out.println("after sending GetCostRequest...");
                }
            }
        };

        return task;
    };
}
