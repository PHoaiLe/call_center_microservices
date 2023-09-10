package org.com.BroadcastService.Kafka.ConsumerConfig;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.com.BroadcastService.Kafka.CustomDeserializer.BroadcastRequestDeserializer;
import org.com.BroadcastService.Requests.Requests.BroadcastRequest;
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
public class BroadcastRequestConsumerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_servers;

    @Bean
    public Map<String, Object> newBroadcastRequestProperty()
    {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, BroadcastRequestDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, BroadcastRequest> newBroadcastRequestConsumerFactory()
    {
        return new DefaultKafkaConsumerFactory<>(newBroadcastRequestProperty());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BroadcastRequest> broadcastRequestConcurrentKafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String, BroadcastRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(newBroadcastRequestConsumerFactory());
        return factory;
    }

}