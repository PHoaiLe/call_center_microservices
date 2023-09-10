package org.com.DriverPartitionService.Firebase.DriverPartition;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import org.com.DriverPartitionService.Firebase.DriverPartition.DriverPartitionConstants.DriverPartitionConstants;
import org.com.DriverPartitionService.Firebase.Objects.DriverPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DriverPartitionFirebaseService
{
    private DriverPartitionFirestoreRepository repository;

    public DriverPartitionFirebaseService()
    {
        repository = new DriverPartitionFirestoreRepository();
    }

    public List<DriverPartition> getDriverPartitionBy(String startAddress)
    {
        List<DriverPartition> result = new ArrayList<>();
        if(startAddress == null)
        {
            return result;
        }
        if(startAddress.isEmpty())
        {
            return result;
        }

        String partitionKey = "";

        //example: 227 Duong Nguyen Van Cu, phuong 4, Quan 5, Thanh pho Ho Chi Minh
        //--> Loai bo dia cho so --> Duong Nguyen Van Cu, phuong 4, Quan 5, Thanh pho Ho Chi Minh
        //--> Su dung result = startAddress.split(" ") va loai bo result[0]
        String[] splitResult = startAddress.split(" ");
        for(int i=1; i< splitResult.length; i++)
        {
            partitionKey = partitionKey.concat(splitResult[i]);
        }
        System.out.println("partitionKey: " + partitionKey);
        //this function will try to get information at least MAX_QUERY_TIME
        List<QueryDocumentSnapshot> data = repository.getDriverPartitionByPartitionKey(partitionKey);
        for(int i=0; i<data.size(); i++)
        {
            QueryDocumentSnapshot documentSnapshot = data.get(i);
            String driver_id = documentSnapshot.getId();
            Map<String, Object> mappingObject = documentSnapshot.getData();
            //from Map<String, Object> to DriverPartitionResponse

            DriverPartition response = new DriverPartition();

            response.setDriver_id(driver_id);
            response.setArea_status(String.valueOf(mappingObject.get(DriverPartitionConstants.DRIVER_PARTITION_KEY_AREA_STATUS)));
            response.setDriver_status(String.valueOf(mappingObject.get(DriverPartitionConstants.DRIVER_PARTITION_KEY_DRIVER_STATUS)));
            response.setPartition_key(String.valueOf(mappingObject.get(DriverPartitionConstants.DRIVER_PARTITION_KEY_PARTITION_KEY)));
            response.setFcm_token(String.valueOf(mappingObject.get(DriverPartitionConstants.DRIVER_PARTITION_KEY_FCM_TOKEN)));


            result.add(response);
        }

        System.out.println("closest driver partition: " + result);
        return result;
    }

    public List<DriverPartition> getRelativeDriverPartitionBy(String startAddress)
    {
        List<DriverPartition> result = new ArrayList<>();
        if(startAddress == null)
        {
            return result;
        }
        if(startAddress.isEmpty())
        {
            return result;
        }

        String partitionKey = "";

        //example: 227 Duong Nguyen Van Cu, phuong 4, Quan 5, Thanh pho Ho Chi Minh
        //--> Loai bo dia cho so --> Duong Nguyen Van Cu, phuong 4, Quan 5, Thanh pho Ho Chi Minh
        //--> Su dung result = startAddress.split(" ") va loai bo result[0]
        String[] splitResult1 = startAddress.split(" ");
        for(int i=1; i< splitResult1.length; i++)
        {
            partitionKey = partitionKey.concat(splitResult1[i]);
        }

        String[] splitResult2 = partitionKey.split(",");
        partitionKey = "";
        for(int i=1; i< splitResult2.length; i++)
        {
            partitionKey = partitionKey.concat(splitResult2[i]).concat(",");
        }
        partitionKey = partitionKey.substring(0, partitionKey.length() - 1);
        System.out.println("relative partition_key: " + partitionKey);


        List<QueryDocumentSnapshot> data = repository.getRelativeDriverPartitionByPartitionKey(partitionKey);
        for(int i=0; i<data.size(); i++)
        {
            QueryDocumentSnapshot documentSnapshot = data.get(i);
            String driver_id = documentSnapshot.getId();
            Map<String, Object> mappingObject = documentSnapshot.getData();
            //from Map<String, Object> to DriverPartitionResponse

            DriverPartition response = new DriverPartition();

            response.setDriver_id(driver_id);
            response.setArea_status(String.valueOf(mappingObject.get(DriverPartitionConstants.DRIVER_PARTITION_KEY_AREA_STATUS)));
            response.setDriver_status(String.valueOf(mappingObject.get(DriverPartitionConstants.DRIVER_PARTITION_KEY_DRIVER_STATUS)));
            response.setPartition_key(String.valueOf(mappingObject.get(DriverPartitionConstants.DRIVER_PARTITION_KEY_PARTITION_KEY)));
            response.setFcm_token(String.valueOf(mappingObject.get(DriverPartitionConstants.DRIVER_PARTITION_KEY_FCM_TOKEN)));


            result.add(response);
        }

        System.out.println("Relative driver partition: " + result);
        return result;
    }
}
