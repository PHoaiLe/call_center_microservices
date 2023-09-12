package org.com.BookingService.Service;

import org.com.BookingService.Firebase.FirebaseServices;
import org.com.BookingService.Requests.BookingRequestWrapper;
import org.com.BookingService.Requests.Constants.BookingRequestTypes;
import org.com.BookingService.Requests.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.BookingService.Requests.RequestStrategy.RequestConverterHandler;
import org.com.BookingService.Requests.RequestStrategy.RequestConverterStrategyProvider;
import org.com.BookingService.Requests.Requests.Receive.UpdateFCMToken;
import org.com.BookingService.Service.FuntionInterfaces.GetRunnable_BytesParam_Function;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class BookingServiceProvider
{
    private HashMap<String, GetRunnable_BytesParam_Function> serviceProvider;
    private ThreadPoolExecutor executor;

    public BookingServiceProvider()
    {
        this.serviceProvider = new HashMap<>();

        this. executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        manualInitialize();
    }


    private void manualInitialize()
    {
        serviceProvider.put(BookingRequestTypes.UPDATE_FCM_TOKEN, updateFCMTokenRunnable);
    }

    public void execute(BookingRequestWrapper wrapper)
    {
        if(wrapper == null)
        {
            return;
        }

        GetRunnable_BytesParam_Function function = serviceProvider.get(wrapper.getRequestType());
        if(function == null)
        {
            return;
        }
        Runnable runnable = function.getRunnable(wrapper.getPayload());
        try
        {
            System.out.println("do task");
            System.out.println(executor);
            executor.submit(runnable);
            System.out.println(executor);
        }
        catch (RejectedExecutionException ex)
        {
            System.out.println(ex);
            //TODO: send to RejectedExecution service
        }
        catch (NullPointerException nullPointerException)
        {
            System.out.println(nullPointerException);
        }
    }


    //tasks
    private GetRunnable_BytesParam_Function updateFCMTokenRunnable = (payload) ->
    {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("inside task");
                byte[] bytes = payload;
                String requestType = BookingRequestTypes.UPDATE_FCM_TOKEN;
                RequestConverterStrategyProvider strategyProvider = new RequestConverterStrategyProvider();
                RequestConverterStrategy requestConverterStrategy = strategyProvider.getRequestConverterStrategy(requestType);

                RequestConverterHandler converterHandler = new RequestConverterHandler();
                converterHandler.setStrategy(requestConverterStrategy);

                UpdateFCMToken request = (UpdateFCMToken) converterHandler.fromBytesToObject(bytes);
                System.out.println("after convert: "+ request);
                FirebaseServices firebaseServices = new FirebaseServices();
                boolean check = firebaseServices.updateFCMToken(request.getRole(), request.getUserId(), request.getFcm_token());
                System.out.println("update result: " + check);
                if(check == false)
                {
                    System.out.println("after updating fcm token, FALSE");
                }
            }
        };


        return task;
    };


}
