package org.com.VerificationService.Kafka.ProducerConfig;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.com.VerificationService.Kafka.CustomSerializer.NotificationWrapperRequestSerializer;
import org.com.VerificationService.Kafka.CustomSerializer.RequestWrapperSerializer;
import org.com.VerificationService.Request.NotificationWrapper;
import org.com.VerificationService.Request.RequestWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class NotificationWrapperRequestKafkaProducerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_server;

    @Bean
    public Map<String, Object> newNotificationWrapperProducerConfig()
    {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_server);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, NotificationWrapperRequestSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, NotificationWrapper> newNotificationWrapperFactoryProducerFactory()
    {
        return new DefaultKafkaProducerFactory(newNotificationWrapperProducerConfig());
    }

    @Bean
    public KafkaTemplate<String, NotificationWrapper> kafkaTemplateNotificationWrapper()
    {
        return new KafkaTemplate<>(newNotificationWrapperFactoryProducerFactory());
    }
}
