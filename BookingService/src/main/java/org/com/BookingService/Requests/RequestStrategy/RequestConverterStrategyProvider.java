package org.com.BookingService.Requests.RequestStrategy;



import org.com.BookingService.Requests.RequestStrategy.Interfaces.RequestConverterStrategy;
import org.com.BookingService.Support.Packages.AccessingAllClassesInPackage;

import java.util.HashMap;
import java.util.List;

public class RequestConverterStrategyProvider
{
    private final String packageOfRequestConverterStrategy = "org.com.BookingService.Requests.RequestStrategy.RequestConverterStrategies";
    private HashMap<String, String> strategyProvider;

    public RequestConverterStrategyProvider()
    {
        strategyProvider = new HashMap<>();
        initialize();
    }

    private void initialize()
    {
        try
        {
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
            System.out.println(ex);
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
