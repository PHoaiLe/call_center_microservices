package org.com.LocatingService.Kafka.ProducerConfig;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.com.LocatingService.Kafka.CustomSerializer.DriverPartitionRequestSerializer;
import org.com.LocatingService.Request.DriverPartitionRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DriverPartitionRequestKafkaProducerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_server;

    @Bean
    Map<String, Object> newDriverPartitionRequestProperty()
    {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_server);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, DriverPartitionRequestSerializer.class);
        return props;
    }

    @Bean
    ProducerFactory<String, DriverPartitionRequest> newDriverPartitionRequestProducerFactory()
    {
        return new DefaultKafkaProducerFactory<>(newDriverPartitionRequestProperty());
    }

    @Bean
    KafkaTemplate<String, DriverPartitionRequest> kafkaTemplateDriverPartitionRequest()
    {
        return new KafkaTemplate<>(newDriverPartitionRequestProducerFactory());
    }
}
