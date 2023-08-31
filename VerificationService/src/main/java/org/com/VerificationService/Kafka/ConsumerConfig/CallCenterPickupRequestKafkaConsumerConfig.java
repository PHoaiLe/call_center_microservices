package org.com.VerificationService.Kafka.ConsumerConfig;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.com.VerificationService.Kafka.CustomDeserializer.CallCenterPickupRequestDeserializer;
import org.com.VerificationService.Request.Requests.CallCenterPickupRequest;
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
public class CallCenterPickupRequestKafkaConsumerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_server;

    @Bean
    public Map<String, Object> newCallCenterPickupRequestConsumerConfig()
    {
        try
        {
            HashMap<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_server);
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CallCenterPickupRequestDeserializer.class);
            return props;
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    @Bean
    public ConsumerFactory<String, CallCenterPickupRequest> newCallCenterPickupRequestConsumerFactory()
    {
        return new DefaultKafkaConsumerFactory<>(newCallCenterPickupRequestConsumerConfig());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CallCenterPickupRequest> callCenterPickupRequestConcurrentKafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String, CallCenterPickupRequest> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(newCallCenterPickupRequestConsumerFactory());
        return factory;
    }

}
