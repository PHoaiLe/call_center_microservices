package org.com.VerificationService.Kafka.ProducerConfig;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.com.VerificationService.Kafka.CustomSerializer.RequestWrapperSerializer;
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
public class RequestWrapperKafkaProducerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_server;

    @Bean
    public Map<String, Object> newRequestWrapperProducerConfig()
    {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_server);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, RequestWrapperSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, RequestWrapper> newRequestFactoryProducerFactory()
    {
        return new DefaultKafkaProducerFactory(newRequestWrapperProducerConfig());
    }

    @Bean
    public KafkaTemplate<String, RequestWrapper> kafkaTemplateRequestWrapper()
    {
        return new KafkaTemplate<>(newRequestFactoryProducerFactory());
    }
}
