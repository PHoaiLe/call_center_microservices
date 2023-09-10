package org.com.NotificationService.Kafka.ConsumerConfig;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.com.NotificationService.Kafka.CustomDeserializer.NotificationWrapperDeserializer;
import org.com.NotificationService.Requests.NotificationWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;


@EnableKafka
@Configuration
public class NotificationWrapperRequestConsumerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_servers;

    @Bean
    public Map<String, Object> newNotificationWrapperRequestProperty()
    {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, NotificationWrapperDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, NotificationWrapper> newNotificationWrapperRequestConsumerFactory()
    {
        return new DefaultKafkaConsumerFactory<>(newNotificationWrapperRequestProperty());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NotificationWrapper> notificationWrapperConcurrentKafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String, NotificationWrapper> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(newNotificationWrapperRequestConsumerFactory());
        return factory;
    }
}
