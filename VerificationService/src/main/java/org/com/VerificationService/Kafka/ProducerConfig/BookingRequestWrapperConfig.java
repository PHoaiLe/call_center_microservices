package org.com.VerificationService.Kafka.ProducerConfig;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.com.VerificationService.Kafka.CustomSerializer.BookingRequestWrapperSerializer;
import org.com.VerificationService.Kafka.CustomSerializer.BookingUpdateFCMTokenSerializer;
import org.com.VerificationService.Request.BookingRequestWrapper;
import org.com.VerificationService.Request.Requests.Send.BookingUpdateFCMToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BookingRequestWrapperConfig
{

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrap_servers;

    @Bean
    public Map<String, Object> newBookingWrapperRequestProperty()
    {
        HashMap<String, Object> props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, BookingRequestWrapperSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, BookingRequestWrapper> newBookingRequestWrapperProducerFactory()
    {
        return new DefaultKafkaProducerFactory<>(newBookingWrapperRequestProperty());
    }

    @Bean
    public KafkaTemplate<String, BookingRequestWrapper> bookingRequestWrapperKafkaTemplate()
    {
        return new KafkaTemplate<>(newBookingRequestWrapperProducerFactory());
    }
}
