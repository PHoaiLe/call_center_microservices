package org.com.BroadcastService.Firebase.Broadcast;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.MulticastMessage;
import com.google.firebase.messaging.SendResponse;

import org.com.BroadcastService.Firebase.Objects.DriverPartition;
import org.com.BroadcastService.Requests.Constants.DriverPartitionRequestAttributes;
import org.com.BroadcastService.Requests.Requests.DriverPartitionRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FirebaseFCMService
{
    private FirebaseFCMRepository repository;

    public FirebaseFCMService()
    {
        repository = new FirebaseFCMRepository();
    }

    public List<DriverPartition> sendMessageToMultipleDevices(List<DriverPartition> listOfDriverPartition, DriverPartitionRequest info)
    {
        if(listOfDriverPartition == null)
        {
            return null;
        }
        if(listOfDriverPartition.size() == 0)
        {
            return null;
        }
        if(info == null)
        {
            return null;
        }

        //extract necessary information
        List<String> driverFCMToken = new ArrayList<>();
        for(int i = 0; i< listOfDriverPartition.size(); i++)
        {
            String fcm_token = listOfDriverPartition.get(i).getFcm_token();
            driverFCMToken.add(fcm_token);
        }

        Map<String, String> property = new HashMap<>();
        property.put(DriverPartitionRequestAttributes.USERID, info.getUserId());
        property.put(DriverPartitionRequestAttributes.REQUEST_TYPE, info.getRequestType());
        property.put(DriverPartitionRequestAttributes.START_LONGITUDE, info.getStartLongitude());
        property.put(DriverPartitionRequestAttributes.START_LATITUDE, info.getStartLatitude());
        property.put(DriverPartitionRequestAttributes.END_LONGITUDE, info.getEndLongitude());
        property.put(DriverPartitionRequestAttributes.END_LATITUDE, info.getEndLatitude());
        property.put(DriverPartitionRequestAttributes.START_ADDRESS, info.getStartAddress());
        property.put(DriverPartitionRequestAttributes.END_ADDRESS, info.getEndAddress());
        property.put(DriverPartitionRequestAttributes.CUSTOMER_NAME, info.getCustomerName());
        property.put(DriverPartitionRequestAttributes.PHONE, info.getPhone());
        property.put(DriverPartitionRequestAttributes.VEHICLE, info.getVehicle());
        property.put(DriverPartitionRequestAttributes.DURATION, String.valueOf(info.getDuration()));
        property.put(DriverPartitionRequestAttributes.DISTANCE, String.valueOf(info.getDistance()));
        property.put(DriverPartitionRequestAttributes.COST, String.valueOf(info.getCost()));


        int MAX_MESSAGES_PER_INVOCATION = repository.getMAX_MESSAGE_PER_INVOCATION();
        int timeToSend = driverFCMToken.size() / MAX_MESSAGES_PER_INVOCATION;
        int remain = driverFCMToken.size() % MAX_MESSAGES_PER_INVOCATION;


        //driverFCMToken.size() < 500 OR start of partition = 0
        if(timeToSend < 1)
        {
            MulticastMessage message = MulticastMessage.builder()
                    .putAllData(property)
                    .addAllTokens(driverFCMToken)
                    .build();
            BatchResponse response = repository.sendMessageToMutipleDevices(message);
            List<DriverPartition> failedMessages = getMappingListOfErrorTokenFrom(response, listOfDriverPartition, 0);

            return failedMessages;
        }


        List<DriverPartition> result = new ArrayList<>();

        for(int partition = 1; partition <= timeToSend; partition++)
        {
            //TODO: improve by async task multiple threads
            int startIndex = (partition - 1)*MAX_MESSAGES_PER_INVOCATION;
            int endIndex = partition*MAX_MESSAGES_PER_INVOCATION;
            List<String> subDriverFCMToken = driverFCMToken.subList(startIndex, endIndex);

            MulticastMessage message = MulticastMessage.builder()
                    .putAllData(property)
                    .addAllTokens(subDriverFCMToken)
                    .build();
            BatchResponse batchResponses = repository.sendMessageToMutipleDevices(message);

            result.addAll(getMappingListOfErrorTokenFrom(batchResponses, listOfDriverPartition, startIndex));
        }

        if(remain == 0)
        {
            return result;
        }

        int startIndexFinal = timeToSend*MAX_MESSAGES_PER_INVOCATION;
        int endIndexFinal = startIndexFinal + remain;
        List<String> subDriverFCMToken = driverFCMToken.subList(startIndexFinal, endIndexFinal);

        MulticastMessage messageFinal = MulticastMessage.builder()
                .putAllData(property)
                .addAllTokens(subDriverFCMToken)
                .build();
        BatchResponse batchResponseFinal = repository.sendMessageToMutipleDevices(messageFinal);
        result.addAll(getMappingListOfErrorTokenFrom(batchResponseFinal, listOfDriverPartition, startIndexFinal));
        System.out.println("Error messages: " + result);
        return result;
    }

    //Local function
    private List<DriverPartition> getMappingListOfErrorTokenFrom(BatchResponse batchResponse, List<DriverPartition> driverPartitions, int startIndex)
    {
        if(batchResponse == null)
        {
            return null;
        }
        if(driverPartitions == null)
        {
            return null;
        }

        List<DriverPartition> result = new ArrayList<>();

        List<SendResponse> sendResponses = batchResponse.getResponses();
        for (int i =0; i < sendResponses.size(); i++)
        {
            if(!sendResponses.get(i).isSuccessful()) //failed message
            {
                result.add(driverPartitions.get(startIndex+i));
            }
        }
        return result;
    }
}
