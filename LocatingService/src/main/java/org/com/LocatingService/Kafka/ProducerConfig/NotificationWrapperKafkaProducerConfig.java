package org.com.LocatingService.Kafka.ProducerConfig;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.com.LocatingService.Kafka.CustomSerializer.NotificationWrapperSerializer;
import org.com.LocatingService.Request.GetCostNotification;
import org.com.LocatingService.Request.NotificationWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class NotificationWrapperKafkaProducerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_servers;

    @Bean
    public Map<String, Object> newNotificationWrapperProperty()
    {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, NotificationWrapperSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, NotificationWrapper> newNotificationWrapperProducerFactory()
    {
        return new DefaultKafkaProducerFactory<>(newNotificationWrapperProperty());
    }

    @Bean
    public KafkaTemplate<String, NotificationWrapper> notificationWrapperKafkaTemplate()
    {
        return new KafkaTemplate<>(newNotificationWrapperProducerFactory());
    }
}
