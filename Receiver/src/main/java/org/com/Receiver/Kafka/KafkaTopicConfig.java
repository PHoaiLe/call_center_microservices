package org.com.Receiver.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.com.Receiver.Kafka.Constants.KafkaTopics;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic DataRoom()
    {
        return TopicBuilder.name(KafkaTopics.DATA_ROOM).build();
    }
}
