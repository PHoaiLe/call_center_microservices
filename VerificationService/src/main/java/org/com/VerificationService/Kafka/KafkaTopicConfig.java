package org.com.VerificationService.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.com.VerificationService.Kafka.Constants.KafkaTopics;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig
{
    public NewTopic DataRoom()
    {
        return TopicBuilder.name(KafkaTopics.DATA_ROOM).build();
    }

    public NewTopic Broadcast()
    {
        return TopicBuilder.name(KafkaTopics.DRIVER_PARTITION).build();
    }

    public NewTopic Locating()
    {
        return TopicBuilder.name(KafkaTopics.LOCATING).build();
    }

    public NewTopic Booking()
    {
        return TopicBuilder.name(KafkaTopics.BOOKING).build();
    }
    public NewTopic Notification()
    {
        return TopicBuilder.name(KafkaTopics.NOTIFICATION).build();
    }

}
