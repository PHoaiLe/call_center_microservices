package org.com.DriverPartitionService.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.com.DriverPartitionService.Kafka.Constants.KafkaTopics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig
{
    @Bean
    public NewTopic DriverPartition()
    {
        return TopicBuilder.name(KafkaTopics.DRIVER_PARTITION).build();
    }

    @Bean
    public NewTopic Broadcast()
    {
        return TopicBuilder.name(KafkaTopics.BROADCAST).build();
    }
}
