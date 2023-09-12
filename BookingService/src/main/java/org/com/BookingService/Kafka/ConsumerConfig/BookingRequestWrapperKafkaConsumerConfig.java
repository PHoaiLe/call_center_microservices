package org.com.BookingService.Kafka.ConsumerConfig;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.com.BookingService.Kafka.CustomDeserializer.BookingRequestWrapperDeserializer;
import org.com.BookingService.Requests.BookingRequestWrapper;
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
public class BookingRequestWrapperKafkaConsumerConfig
{
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_servers;

    @Bean
    public Map<String, Object> newBookingRequestWrapperProperty()
    {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, BookingRequestWrapperDeserializer.class);
        return props;
    }

    @Bean
    public ConsumerFactory<String, BookingRequestWrapper> newBookingRequestWrapperConsumerFactory()
    {
        return new DefaultKafkaConsumerFactory<>(newBookingRequestWrapperProperty());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, BookingRequestWrapper> bookingRequestWrapperConcurrentKafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String, BookingRequestWrapper> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(newBookingRequestWrapperConsumerFactory());
        return factory;
    }
}
