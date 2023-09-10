package org.com.DriverPartitionService.Firebase.DriverPartition;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import org.com.DriverPartitionService.Firebase.DriverPartition.DriverPartitionConstants.DriverPartitionConstants;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class DriverPartitionFirestoreRepository
{
    private final int MAX_QUERY_TIME = 3;

    /**
     *
     * @return List<QueryDocumentSnapshot> listOfDriverPartiton
     */
    public List<QueryDocumentSnapshot> getDriverPartitionByPartitionKey(String partitionKey)
    {
        System.out.println("inside getDriverPartitionByPartitionKey");
        Firestore firestore = FirestoreClient.getFirestore();
        Query query =  firestore.collection(DriverPartitionConstants.DRIVER_PARTITION_COLLECTION)
                .whereEqualTo(DriverPartitionConstants.DRIVER_PARTITION_KEY_PARTITION_KEY, partitionKey)
                .whereEqualTo(DriverPartitionConstants.DRIVER_PARTITION_KEY_DRIVER_STATUS, DriverPartitionConstants.DRIVER_PARTITION_VALUE_DRIVER_STATUS);
        ApiFuture<QuerySnapshot> apiFuture = query.get();
        System.out.println("query.get()");
        int queryTime = 0;
        List<QueryDocumentSnapshot> listOfDriverPartition = new ArrayList<>();
        while(queryTime < MAX_QUERY_TIME)
        try
        {
            //async task
            listOfDriverPartition = apiFuture.get().getDocuments();
            break;
        }
        catch (Exception ex)
        {
            System.out.println("query exception");
            queryTime = queryTime + 1;
        }

        return listOfDriverPartition;
    }

    public List<QueryDocumentSnapshot> getRelativeDriverPartitionByPartitionKey(String partitionKey){
        //TODO: whereEqualToThanOrEqualTo doesnot give expected responses

        Firestore firestore = FirestoreClient.getFirestore();
        Query query =  firestore.collection(DriverPartitionConstants.DRIVER_PARTITION_COLLECTION)
                .whereGreaterThanOrEqualTo(DriverPartitionConstants.DRIVER_PARTITION_KEY_PARTITION_KEY, partitionKey)
                .whereEqualTo(DriverPartitionConstants.DRIVER_PARTITION_KEY_DRIVER_STATUS, DriverPartitionConstants.DRIVER_PARTITION_VALUE_DRIVER_STATUS);
        ApiFuture<QuerySnapshot> apiFuture = query.get();

        int queryTime = 0;
        List<QueryDocumentSnapshot> listOfDriverPartition = new ArrayList<>();
        while(queryTime < MAX_QUERY_TIME)
            try
            {
                //async task
                listOfDriverPartition = apiFuture.get().getDocuments();
                break;
            }
            catch (Exception ex)
            {
                queryTime = queryTime + 1;
            }

        return listOfDriverPartition;
    }
}
