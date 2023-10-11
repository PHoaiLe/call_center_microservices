package org.com.NotificationService.Services;

import org.com.NotificationService.Firebase.Notification.FirebaseFCMService;
import org.com.NotificationService.Requests.Constants.NotificationAttributes;
import org.com.NotificationService.Requests.Constants.NotificationTypes;
import org.com.NotificationService.Requests.NotificationWrapper;
import org.com.NotificationService.Requests.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.NotificationService.Requests.RequestStrategy.NotificationRequestConverterHandler;
import org.com.NotificationService.Requests.RequestStrategy.NotificationRequestConverterProvider;
import org.com.NotificationService.Requests.Requests.GetCostNotification;
import org.com.NotificationService.Requests.Requests.GetDirectionNotification;
import org.com.NotificationService.Services.FunctionInterface.GetRunnable_BytesParam_Function;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class NotificationRequestServiceProvider
{
    private ThreadPoolExecutor executor;
    private HashMap<String, GetRunnable_BytesParam_Function> serviceProvider;

    public NotificationRequestServiceProvider()
    {
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        serviceProvider = new HashMap<>();

        manualInitialization();
    }

    private void manualInitialization()
    {
        serviceProvider.put(NotificationTypes.GET_COST_NOTIFICATION_REQUEST, getCostNotificationTask);
        serviceProvider.put(NotificationTypes.GET_DIRECTION_NOTIFICATION, GetDirectionNotificationRunnable);
    }

    public void execute(NotificationWrapper wrapper)
    {
        if(wrapper == null)
        {
            return;
        }

        GetRunnable_BytesParam_Function runnableProvider = serviceProvider.get(wrapper.getNotificationType());
        if(runnableProvider == null)
        {
            return;
        }

        Runnable task = runnableProvider.getRunnable(wrapper.getPayload());
        try
        {
            executor.submit(task);
        }
        catch(RejectedExecutionException ex)
        {
            System.out.println(ex);
            //TODO: send to a service to handle this
        }
    }

    private GetRunnable_BytesParam_Function getCostNotificationTask = (payload) ->
    {
          if(payload == null)
          {
              return null;
          }

          Runnable task = new Runnable() {
              @Override
              public void run() {
                  byte[] bytes = payload;

                  //get RequestConverterStrategy by requestType
                  String requestType = NotificationTypes.GET_COST_NOTIFICATION_REQUEST;

                  NotificationRequestConverterProvider converterProvider = new NotificationRequestConverterProvider();

                  RequestConverterStrategy converterStrategy = converterProvider.getRequestConverterStrategy(requestType);

                  //set converter strategy for converter handler
                  NotificationRequestConverterHandler handler = new NotificationRequestConverterHandler();

                  handler.setStrategy(converterStrategy);

                  GetCostNotification notification = (GetCostNotification) handler.fromBytesToObject(bytes);

                  System.out.println(notification);

                  FirebaseFCMService fcmService = new FirebaseFCMService();

                  String response = fcmService.sendMessageToDevice(notification);
                  System.out.println("response: " + response);
                  System.out.println("After sending notification to user " + notification.getIdUser());
              }
          };

          return task;
    };

    GetRunnable_BytesParam_Function GetDirectionNotificationRunnable = (payload) ->
    {
        if(payload == null)
        {
            return null;
        }

        Runnable task = new Runnable() {
            @Override
            public void run() {
                byte[] bytes = payload;
                String requestType = NotificationTypes.GET_DIRECTION_NOTIFICATION;
                NotificationRequestConverterProvider converterProvider = new NotificationRequestConverterProvider();
                RequestConverterStrategy strategy = converterProvider.getRequestConverterStrategy(requestType);

                NotificationRequestConverterHandler handler = new NotificationRequestConverterHandler();
                handler.setStrategy(strategy);

                GetDirectionNotification notification = (GetDirectionNotification) handler.fromBytesToObject(bytes);
                System.out.println(notification);

                FirebaseFCMService service = new FirebaseFCMService();
                String response = service.sendMessageToDevice(notification);
                System.out.println("after sending, response: " + response);
            }
        };

        return task;
    };
}
