package org.com.DriverPartitionService.Kafka.ProducerConfig;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.com.DriverPartitionService.Kafka.CustomSerializer.BroadcastRequestSerializer;
import org.com.DriverPartitionService.Requests.BroadcastRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BroadcastRequestProducerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_servers;

    @Bean
    public Map<String, Object> newBroadcastRequestProperty()
    {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, BroadcastRequestSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, BroadcastRequest> newBroadcastRequestProducerFactory()
    {
        return new DefaultKafkaProducerFactory<>(newBroadcastRequestProperty());
    }

    @Bean
    public KafkaTemplate<String, BroadcastRequest> kafkaTemplateBroadcastRequest()
    {
        return new KafkaTemplate<>(newBroadcastRequestProducerFactory());
    }

}
