package org.com.DriverPartitionService.Kafka.ConsumerConfig;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.com.DriverPartitionService.DriverPartitionService;
import org.com.DriverPartitionService.Kafka.CustomDeserializer.DriverPartitionRequestDeserializer;
import org.com.DriverPartitionService.Requests.DriverPartitionRequest;
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
public class DriverPartitionRequestConsumerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_servers;

    @Bean
    public Map<String, Object> newDriverPartitionRequestProperty()
    {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, DriverPartitionRequestDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, DriverPartitionRequest> newDriverPartitionRequestConsumerFactory()
    {
        return new DefaultKafkaConsumerFactory<>(newDriverPartitionRequestProperty());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DriverPartitionRequest> driverPartitionRequestConcurrentKafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String, DriverPartitionRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(newDriverPartitionRequestConsumerFactory());
        return factory;
    }
}
