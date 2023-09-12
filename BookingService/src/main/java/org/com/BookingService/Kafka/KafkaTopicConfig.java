package org.com.BookingService.Kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig
{
    public NewTopic BookingTopic()
    {
        return TopicBuilder.name("booking").build();
    }
}
