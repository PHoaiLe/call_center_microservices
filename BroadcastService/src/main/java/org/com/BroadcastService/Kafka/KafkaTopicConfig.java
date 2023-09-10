package org.com.BroadcastService.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.com.BroadcastService.Kafka.Constants.KafkaTopics;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig
{
    public NewTopic BroadcastTopic()
    {
        return TopicBuilder.name(KafkaTopics.BROADCAST).build();
    }
}
