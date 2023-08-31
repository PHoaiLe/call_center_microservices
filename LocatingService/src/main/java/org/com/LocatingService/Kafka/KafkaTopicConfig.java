package org.com.LocatingService.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.com.LocatingService.Kafka.Constants.KafkaTopics;
import org.springframework.kafka.config.TopicBuilder;

public class KafkaTopicConfig
{
    public NewTopic Locating()
    {
        return TopicBuilder.name(KafkaTopics.LOCATING).build();
    }
}
