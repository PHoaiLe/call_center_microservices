package org.com.Receiver.Kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.com.Receiver.Kafka.CustomSerializer.CallCenterPickupRequestSerializer;
import org.com.Receiver.Request.CallCenterPickupRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CallCenterPickupRequestKafkaProducerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_servers;

    @Bean
    public Map<String, Object> newCallCenterProducerConfig()
    {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CallCenterPickupRequestSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, CallCenterPickupRequest> newCallCenterPickupRequestProducerFactory()
    {
        return new DefaultKafkaProducerFactory<>(newCallCenterProducerConfig());
    }

    @Bean
    public KafkaTemplate<String, CallCenterPickupRequest> kafkaTemplateCallCenterNewPickupRequest()
    {
        return new KafkaTemplate<>(newCallCenterPickupRequestProducerFactory());
    }

}
