package org.com.Receiver.Kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.com.Receiver.Kafka.CustomSerializer.ClientAppPickupRequestSerializer;
import org.com.Receiver.Request.ClientAppPickupRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ClientAppPickupRequestKafkaProducerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;


    public Map<String, Object> newPickupRequestProducerConfig()
    {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ClientAppPickupRequestSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, ClientAppPickupRequest> newClientAppPickupRequestProducerFactory()
    {
        return new DefaultKafkaProducerFactory<>(newPickupRequestProducerConfig());
    }

    @Bean
    public KafkaTemplate<String, ClientAppPickupRequest> kafkaTemplateClientAppNewPickupRequest(ProducerFactory<String, ClientAppPickupRequest> producerFactory)
    {
        return new KafkaTemplate<>(producerFactory);
    }

}
