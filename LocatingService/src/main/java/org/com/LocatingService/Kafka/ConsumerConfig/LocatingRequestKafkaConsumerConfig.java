package org.com.LocatingService.Kafka.ConsumerConfig;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.com.LocatingService.Kafka.CustomDeserializer.LocatingRequestDeserializer;
import org.com.LocatingService.Request.Receive.LocatingRequest;
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
public class LocatingRequestKafkaConsumerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_server;

    @Bean
    public Map<String, Object> newLocatingRequestConsumerConfig()
    {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_server);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, LocatingRequestDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, LocatingRequest> newLocatingRequestConsumerFactory()
    {
        return new DefaultKafkaConsumerFactory<>(newLocatingRequestConsumerConfig());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, LocatingRequest> locatingRequestConcurrentKafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String, LocatingRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(newLocatingRequestConsumerFactory());
        return factory;
    }
}
