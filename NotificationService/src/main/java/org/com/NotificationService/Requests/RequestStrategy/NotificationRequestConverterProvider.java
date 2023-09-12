package org.com.NotificationService.Requests.RequestStrategy;

import org.com.NotificationService.Requests.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.NotificationService.Support.Packages.AccessingAllClassesInPackage;

import java.util.HashMap;
import java.util.List;

public class NotificationRequestConverterProvider
{
    private final String packageOfRequestConverterStrategy = "org.com.NotificationService.Requests.RequestStrategy.RequestConverterStrategies";
    private HashMap<String, String> strategyProvider;

    public NotificationRequestConverterProvider()
    {
        strategyProvider = new HashMap<>();
        initialize();
    }

    private void initialize()
    {
        try
        {
//            //requestConverterStrategiesPath = "../classes/org/com/VerificationService/Request/RequestStrategy/RequestConverterStrategies/";
//            requestConverterStrategiesPath = getClass().getResource("RequestConverterStrategyProvider.class").toString();
//            int indexOfsubstring = requestConverterStrategiesPath.indexOf("RequestConverterStrategyProvider.class");
//            requestConverterStrategiesPath = requestConverterStrategiesPath.substring(0, indexOfsubstring) + "RequestConverterStrategies/";

            //get list of class'name in RequestConverterStrategies package
            AccessingAllClassesInPackage accessor = new AccessingAllClassesInPackage();
            List<String> nameOfStrategyClasses = accessor.findAllClassNameOfAPackage(packageOfRequestConverterStrategy);

            for(int i = 0; i < nameOfStrategyClasses.size(); i++)
            {
                String nameOfStrategyClass = nameOfStrategyClasses.get(i);
                RequestConverterStrategy strategy = (RequestConverterStrategy) Class.forName(nameOfStrategyClass).getDeclaredConstructor().newInstance();
                String requestTypeReference = strategy.getReferenceRequestType();
                strategyProvider.put(requestTypeReference, nameOfStrategyClass);
            }
        }
        catch(Exception ex)
        {
            throw new RuntimeException(ex);
        }
    }

    public RequestConverterStrategy getRequestConverterStrategy(String requestType)
    {
        if(requestType == null)
        {
            return null;
        }

        try
        {
            //using reflection
            String requestConverterStrategyName = strategyProvider.get(requestType);
            return (RequestConverterStrategy) Class.forName(requestConverterStrategyName)
                    .getDeclaredConstructor().newInstance();
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    public String getNameOfRequestStrategyClass(String requestType)
    {
        if(requestType == null)
        {
            return null;
        }
        return strategyProvider.get(requestType);
    }
}
