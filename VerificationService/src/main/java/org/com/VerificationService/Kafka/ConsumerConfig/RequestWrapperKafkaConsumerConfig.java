package org.com.VerificationService.Kafka.ConsumerConfig;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.com.VerificationService.Kafka.CustomDeserializer.RequestWrapperDeserializer;
import org.com.VerificationService.Request.RequestWrapper;
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
public class RequestWrapperKafkaConsumerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_server;

    @Bean
    public Map<String, Object> newRequestWrapperConsumerConfig()
    {
        HashMap<String, Object> props = new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_server);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, RequestWrapperDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, RequestWrapper> newRequestWrapperConsumerFactory()
    {
        return new DefaultKafkaConsumerFactory<>(newRequestWrapperConsumerConfig());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RequestWrapper> requestWrapperConcurrentKafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String, RequestWrapper> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(newRequestWrapperConsumerFactory());
        return factory;
    }
}
