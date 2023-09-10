package org.com.Receiver.Kafka.ProducerConfig;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.spi.ObjectThreadContextMap;
import org.com.Receiver.Kafka.CustomSerializer.GetCostRequestSerializer;
import org.com.Receiver.Request.Requests.GetCostRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class GetCostRequestKafkaProducerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_servers;

    @Bean
    public Map<String, Object> newGetCostRequestProperty()
    {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GetCostRequestSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, GetCostRequest> newGetCostRequestProducerFactory()
    {
        return new DefaultKafkaProducerFactory<>(newGetCostRequestProperty());
    }

    @Bean
    public KafkaTemplate<String, GetCostRequest> getCostRequestKafkaTemplate()
    {
        return new KafkaTemplate<>(newGetCostRequestProducerFactory());
    }
}