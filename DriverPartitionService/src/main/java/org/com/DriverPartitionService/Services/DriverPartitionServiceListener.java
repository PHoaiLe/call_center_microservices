package org.com.DriverPartitionService.Services;

import org.com.DriverPartitionService.Kafka.Constants.KafkaListenerIds;
import org.com.DriverPartitionService.Kafka.Constants.KafkaTopics;
import org.com.DriverPartitionService.Requests.DriverPartitionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DriverPartitionServiceListener
{
    @Autowired
    private DriverPartitionServiceProvider serviceProvider;
    @KafkaListener(id = KafkaListenerIds.DRIVER_PARTITION_REQUEST_KAFKA_LISTENER_ID,
    topics = KafkaTopics.DRIVER_PARTITION,
    containerFactory = "driverPartitionRequestConcurrentKafkaListenerContainerFactory")
    public void DriverPartitionServiceRequestListener(DriverPartitionRequest request)
    {
        System.out.println(request);
        serviceProvider.execute(request);
    }
}
