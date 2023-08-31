package org.com.VerificationService.Pickup;

import org.com.VerificationService.Pickup.FunctionInterface.GetRunnable_BytesParam_Function;
import org.com.VerificationService.Request.Constants.RequestTypes;
import org.com.VerificationService.Request.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.VerificationService.Request.RequestStrategy.RequestConverterStrategyProvider;
import org.com.VerificationService.Request.Requests.ClientAppPickupRequest;
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
    private RequestConverterStrategyProvider converterStrategyProvider;
    private ThreadPoolExecutor executor;

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

        converterStrategyProvider = new RequestConverterStrategyProvider();

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
                RequestConverterStrategy converterStrategy = converterStrategyProvider.getRequestConverterStrategy(requestType);
                ClientAppPickupRequest clientAppPickupRequest = (ClientAppPickupRequest) converterStrategy.fromByteToObject(payload);
                System.out.println("ClientApp Runnable: " + clientAppPickupRequest);
            }
        };
        return task;
    };

    private GetRunnable_BytesParam_Function CallCenterRequestRunnable = (payload) ->
    {
        Runnable task = new Runnable() {
            @Override
            public void run() {

            }
        };
        return task;
    };
}
