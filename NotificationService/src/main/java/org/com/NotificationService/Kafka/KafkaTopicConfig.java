package org.com.NotificationService.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.com.NotificationService.Kafka.Constants.KafkaTopics;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaTopicConfig
{
    public NewTopic Notification()
    {
        return TopicBuilder.name(KafkaTopics.NOTIFICATION).build();
    }
}
