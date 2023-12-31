package org.com.VerificationService.Pickup;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.com.VerificationService.Kafka.Constants.KafkaTopics;
import org.com.VerificationService.Pickup.FunctionInterface.GetRunnable_BytesParam_Function;
import org.com.VerificationService.Request.BookingRequestWrapper;
import org.com.VerificationService.Request.Constants.BookingRequestTypes;
import org.com.VerificationService.Request.Constants.RequestTypes;
import org.com.VerificationService.Request.NotificationWrapper;
import org.com.VerificationService.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.VerificationService.Request.RequestStrategy.RequestConverterHandler;
import org.com.VerificationService.Request.RequestStrategy.RequestConverterStrategyProvider;
import org.com.VerificationService.Request.Requests.Receive.*;
import org.com.VerificationService.Request.Requests.Send.BookingAcceptPickupRequest;
import org.com.VerificationService.Request.Requests.Send.BookingUpdateFCMToken;
import org.com.VerificationService.Request.Requests.Send.DriverPartitionRequest;
import org.com.VerificationService.Request.Requests.Send.LocatingRequest;
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
    @Autowired
    private KafkaTemplate<String, BookingRequestWrapper> bookingRequestWrapperKafkaTemplate;
    @Autowired
    private KafkaTemplate<String, NotificationWrapper> notificationWrapperKafkaTemplate;

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
        serviceProvider.put(RequestTypes.UPDATE_FCM_TOKEN_REQUEST, GetUpdateFCMTokenRunnable);
        serviceProvider.put(RequestTypes.GET_DIRECTION_REQUEST, GetDirectionRequestRunnable);
        serviceProvider.put(RequestTypes.DRIVER_ACCEPT_PICKUP_REQUEST, GetAcceptPickupRequestRunnable);
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

    private GetRunnable_BytesParam_Function GetUpdateFCMTokenRunnable = (payload) ->
    {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                byte[] bytes = payload;
                String requestType = RequestTypes.UPDATE_FCM_TOKEN_REQUEST;

                VerificationServiceProvider verificationServiceProvider = new VerificationServiceProvider();
                RequestConverterStrategyProvider strategyProvider = new RequestConverterStrategyProvider();
                RequestConverterStrategy converterStrategy = strategyProvider.getRequestConverterStrategy(requestType);

                UpdateFCMToken request = (UpdateFCMToken) converterStrategy.fromByteToObject(bytes);

//                boolean check = verificationServiceProvider.getVerificationHandler().handle(request);

                boolean check = true;
                if(check == true)
                {
                    //TODO: send to BookingService to update FCM token in the database
                    BookingUpdateFCMToken sendingRequest = new BookingUpdateFCMToken();
                    sendingRequest.setUserId(request.getUserId());
                    sendingRequest.setRole(request.getRole());
                    sendingRequest.setRequestType(BookingRequestTypes.UPDATE_FCM_TOKEN);
                    sendingRequest.setFcm_token(request.getFcm_token());

                    BookingRequestWrapper wrapper = new BookingRequestWrapper();

                    ObjectMapper mapper = new ObjectMapper();
                    byte[] bookingWrapperPayload = null;
                    try
                    {
                        bookingWrapperPayload = mapper.writeValueAsBytes(sendingRequest);
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex);
                        return;
                    }

                    wrapper.setRequestType(sendingRequest.getRequestType());
                    wrapper.setPayload(bookingWrapperPayload);

                    bookingRequestWrapperKafkaTemplate.send(KafkaTopics.BOOKING, wrapper);
                    System.out.println("after sending update fcm_token");
                }
            }
        };

        return task;
    };

    GetRunnable_BytesParam_Function GetDirectionRequestRunnable = (payload) ->
    {
        Runnable task = new Runnable()
        {
            @Override
            public void run() {
                byte[] bytes = payload;

                String requestType = RequestTypes.GET_DIRECTION_REQUEST;
                VerificationServiceProvider verificationServiceProvider = new VerificationServiceProvider();

                RequestConverterStrategyProvider strategyProvider = new RequestConverterStrategyProvider();
                RequestConverterStrategy converterStrategy = strategyProvider.getRequestConverterStrategy(requestType);

                RequestConverterHandler handler = new RequestConverterHandler();
                handler.setStrategy(converterStrategy);

                GetDirectionRequest request = (GetDirectionRequest) handler.fromBytesToObject(bytes);

                //boolean check = verificationServiceProvider.getVerificationHandler().handle(request);
                boolean check = true;

                if(check)
                {
                    LocatingRequest locatingRequest = new LocatingRequest();
                    locatingRequest.setUserId(request.getUserId());
                    locatingRequest.setRequestType(request.getRequestType());
                    locatingRequest.setEndAddress(request.getEndAddress());
                    locatingRequest.setStartAddress(request.getStartAddress());

                    locatingRequestKafkaTemplate.send(KafkaTopics.LOCATING, locatingRequest);
                    System.out.println("after sending get-direction-request...");
                }

            }
        };

        return task;
    };

    private GetRunnable_BytesParam_Function GetAcceptPickupRequestRunnable = (payload) ->
    {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                byte[] bytes = payload;
                String requestType = RequestTypes.DRIVER_ACCEPT_PICKUP_REQUEST;

                VerificationServiceProvider verificationServiceProvider = new VerificationServiceProvider();

                RequestConverterStrategyProvider strategyProvider = new RequestConverterStrategyProvider();
                RequestConverterStrategy converterStrategy = strategyProvider.getRequestConverterStrategy(requestType);

                RequestConverterHandler handler = new RequestConverterHandler();
                handler.setStrategy(converterStrategy);

                AcceptPickupRequest request = (AcceptPickupRequest) handler.fromBytesToObject(bytes);

                //boolean check = verificationServiceProvider.getVerificationHandler().handle(request);
                boolean check = true;

                if(check)
                {

                    BookingAcceptPickupRequest bookingAcceptPickupRequest = new BookingAcceptPickupRequest();

                    bookingAcceptPickupRequest.setUserId(request.getUserId());
                    bookingAcceptPickupRequest.setPartnerId(request.getPartnerId());
                    bookingAcceptPickupRequest.setRequestType(request.getRequestType());
                    bookingAcceptPickupRequest.setCustomerName(request.getCustomerName());
                    bookingAcceptPickupRequest.setStartLongitude(request.getStartLongitude());
                    bookingAcceptPickupRequest.setStartLatitude(request.getStartLatitude());
                    bookingAcceptPickupRequest.setEndLongitude(request.getEndLongitude());
                    bookingAcceptPickupRequest.setEndLatitude(request.getEndLatitude());
                    bookingAcceptPickupRequest.setStartAddress(request.getStartAddress());
                    bookingAcceptPickupRequest.setEndAddress(request.getEndAddress());
                    bookingAcceptPickupRequest.setDuration(request.getDuration());
                    bookingAcceptPickupRequest.setDistance(request.getDistance());
                    bookingAcceptPickupRequest.setCost(request.getCost());
                    bookingAcceptPickupRequest.setVehicle(request.getVehicle());
                    bookingAcceptPickupRequest.setPhone(request.getPhone());


                    BookingRequestWrapper bookingRequestWrapper = new BookingRequestWrapper();

                    ObjectMapper mapper = new ObjectMapper();
                    byte[] bookingWrapperPayload = null;
                    try
                    {
                        bookingWrapperPayload = mapper.writeValueAsBytes(bookingAcceptPickupRequest);
                    }
                    catch (Exception ex)
                    {
                        System.out.println(ex);
                        return;
                    }

                    bookingRequestWrapper.setRequestType(BookingRequestTypes.ACCEPT_PICKUP_REQUEST);
                    bookingRequestWrapper.setPayload(bookingWrapperPayload);

                    bookingRequestWrapperKafkaTemplate.send(KafkaTopics.BOOKING, bookingRequestWrapper);
                    System.out.println("after sending addBooking...");


                }
            }
        };

        return task;
    };

}
