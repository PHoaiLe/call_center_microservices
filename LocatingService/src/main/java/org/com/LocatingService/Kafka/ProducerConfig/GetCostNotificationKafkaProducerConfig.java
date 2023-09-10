package org.com.LocatingService.Kafka.ProducerConfig;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.com.LocatingService.Request.GetCostNotification;
import org.com.LocatingService.Request.GetCostResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class GetCostNotificationKafkaProducerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_servers;

    @Bean
    public Map<String, Object> newGetCostNotificationProducer()
    {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GetCostNotification.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, GetCostNotification> newGetCostNotificationProducerFactory()
    {
        return new DefaultKafkaProducerFactory<>(newGetCostNotificationProducer());
    }

    @Bean
    public KafkaTemplate<String, GetCostNotification> getCostNotificationKafkaTemplate()
    {
        return new KafkaTemplate<>(newGetCostNotificationProducerFactory());
    }
}
