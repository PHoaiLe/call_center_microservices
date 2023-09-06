package org.com.LocatingService.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.internals.Topic;
import org.com.LocatingService.Kafka.Constants.KafkaTopics;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig
{
    public NewTopic Locating()
    {
        return TopicBuilder.name(KafkaTopics.LOCATING).build();
    }
    public NewTopic DriverPartition()
    {
        return TopicBuilder.name(KafkaTopics.DRIVER_PARTITION).build();
    }
    public NewTopic Notification()
    {
        return TopicBuilder.name(KafkaTopics.NOTIFICATION).build();
    }
}
