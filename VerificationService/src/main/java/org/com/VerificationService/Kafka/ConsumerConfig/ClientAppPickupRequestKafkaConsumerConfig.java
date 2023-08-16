package org.com.VerificationService.Kafka.ConsumerConfig;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.com.VerificationService.Kafka.CustomDeserializer.ClientAppPickupRequestDeserializer;
import org.com.VerificationService.Request.ClientAppPickupRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;
//
//For consuming messages, we need to configure a ConsumerFactory and a KafkaListenerContainerFactory.
// Once these beans are available in the Spring bean factory, POJO-based consumers can be configured using @KafkaListener annotation.
//
//@EnableKafka annotation is required on the configuration class to enable the detection of @KafkaListener annotation on spring-managed beans:

@EnableKafka
@Configuration
public class ClientAppPickupRequestKafkaConsumerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> newClientAppPickUpRequestConsumerConfig()
    {
        try
        {
            HashMap<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ClientAppPickupRequestDeserializer.class);
            return props;
        }
        catch(Exception ex)
        {
            return null;
        }
    }
    @Bean
    public ConsumerFactory<String, ClientAppPickupRequest> newClientAppPickupRequestConsumerFactory()
    {
        return new DefaultKafkaConsumerFactory<>(newClientAppPickUpRequestConsumerConfig());
    }

    //su dung pickupRequestConcurrentKafkaListenerContainerFactory cho khai bao containerFactory = "clientAppPickupRequestConcurrentKafkaListenerContainerFactory"
    //de thuc hien convert du lieu trong Kafka sang PickupRequest
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ClientAppPickupRequest> clientAppPickupRequestConcurrentKafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String, ClientAppPickupRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(newClientAppPickupRequestConsumerFactory());
        return factory;
    }
}
