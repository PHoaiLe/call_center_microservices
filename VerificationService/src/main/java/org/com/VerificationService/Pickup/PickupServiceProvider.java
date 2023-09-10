package org.com.VerificationService.Pickup;

import org.com.VerificationService.Handler.VerificationHandler;
import org.com.VerificationService.Kafka.Constants.KafkaTopics;
import org.com.VerificationService.Kafka.ProducerConfig.LocatingRequestKafkaProducerConfig;
import org.com.VerificationService.Pickup.FunctionInterface.GetRunnable_BytesParam_Function;
import org.com.VerificationService.Request.Constants.RequestTypes;
import org.com.VerificationService.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.VerificationService.Request.RequestStrategy.RequestConverterStrategyProvider;
import org.com.VerificationService.Request.Requests.CallCenterPickupRequest;
import org.com.VerificationService.Request.Requests.ClientAppPickupRequest;
import org.com.VerificationService.Request.Requests.GetCostRequest;
import org.com.VerificationService.Request.Requests.LocatingRequest;
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
        serviceProvider.put(RequestTypes.CLIENT_APP_NEW_PICK_UP_REQUEST, ClientAppRequestRunnable);
        serviceProvider.put(RequestTypes.CALL_CENTER_NEW_PICK_UP_REQUEST, CallCenterRequestRunnable);
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
    private GetRunnable_BytesParam_Function ClientAppRequestRunnable = (payload) ->
    {
        Runnable task = new Runnable()
        {
            @Override
            public void run()
            {
                String requestType = RequestTypes.CLIENT_APP_NEW_PICK_UP_REQUEST;
                VerificationServiceProvider verificationServiceProvider = new VerificationServiceProvider();
                RequestConverterStrategyProvider converterStrategyProvider = new RequestConverterStrategyProvider();

                RequestConverterStrategy converterStrategy = converterStrategyProvider.getRequestConverterStrategy(requestType);
                ClientAppPickupRequest clientAppPickupRequest = (ClientAppPickupRequest) converterStrategy.fromByteToObject(payload);
                System.out.println("ClientApp Runnable: " + clientAppPickupRequest);
                //boolean check = verificationServiceProvider.getVerificationHandler().handle(clientAppPickupRequest);

                //for testing
                boolean check = true;
                if(check == true)
                {
                    LocatingRequestKafkaProducerConfig producerConfig = new LocatingRequestKafkaProducerConfig();
                    //TODO: send to locating_queue
                    LocatingRequest request = new LocatingRequest();
//                    request.setStartLongitude(clientAppPickupRequest.getStartLongitude());
//                    request.setStartLatitude(clientAppPickupRequest.getStartLatitude());
//                    request.setEndLongitude(clientAppPickupRequest.getEndLongitude());
//                    request.setEndLatitude(clientAppPickupRequest.getEndLatitude());
                    request.setStartAddress(clientAppPickupRequest.getStartAddress());
                    request.setEndAddress(clientAppPickupRequest.getEndAddress());
                    request.setVehicle(clientAppPickupRequest.getVehicle());
                    request.setCustomerName(clientAppPickupRequest.getName());
                    request.setPhone(clientAppPickupRequest.getPhone());
                    request.setUserId(clientAppPickupRequest.getUserId());
                    request.setRequestType(clientAppPickupRequest.getRequestType());

                    System.out.println("Check = true");
                    locatingRequestKafkaTemplate.send(KafkaTopics.LOCATING, request);
                    System.out.println("after send...");
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
                    //TODO: send to locating_queue
                    LocatingRequestKafkaProducerConfig producerConfig = new LocatingRequestKafkaProducerConfig();
                    //TODO: send to locating_queue
                    LocatingRequest request = new LocatingRequest();
//                    request.setStartLongitude(callCenterPickupRequest.getStartLongitude());
//                    request.setStartLatitude(callCenterPickupRequest.getStartLatitude());
//                    request.setEndLongitude(callCenterPickupRequest.getEndLongitude());
//                    request.setEndLatitude(callCenterPickupRequest.getEndLatitude());
                    request.setStartAddress(callCenterPickupRequest.getStartAddress());
                    request.setEndAddress(callCenterPickupRequest.getEndAddress());
                    request.setVehicle(callCenterPickupRequest.getVehicle());
//                    request.setCustomerName(callCenterPickupRequest.getName());
                    request.setPhone(callCenterPickupRequest.getPhone());
                    request.setUserId(callCenterPickupRequest.getCallCenterId());
                    request.setRequestType(callCenterPickupRequest.getRequestType());

                    locatingRequestKafkaTemplate.send(KafkaTopics.LOCATING, request);
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
                boolean check = verificationServiceProvider.getVerificationHandler().handle(getCostRequest);
                if(check == true)
                {
                    LocatingRequest locatingRequest = new LocatingRequest();
                    locatingRequest.setUserId(getCostRequest.getUserId());
                    locatingRequest.setRequestType(getCostRequest.getRequestType());
                    locatingRequest.setStartAddress(getCostRequest.getStartAddress());
                    locatingRequest.setEndAddress(getCostRequest.getEndAddress());

                    locatingRequestKafkaTemplate.send(KafkaTopics.LOCATING, locatingRequest);
                }
            }
        };

        return task;
    };
}
