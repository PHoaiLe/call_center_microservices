package org.com.BookingService.Service;

import org.com.BookingService.Kafka.Constants.KafkaListenerIds;
import org.com.BookingService.Kafka.Constants.KafkaTopics;
import org.com.BookingService.Requests.BookingRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class BookingRequestListener
{
    private BookingServiceProvider serviceProvider;

    @Autowired
    public BookingRequestListener(BookingServiceProvider service)
    {
        serviceProvider = service;
    }

    @KafkaListener(id = KafkaListenerIds.BOOKING_REQUEST_KAFKA_LISTENER_ID,
    topics = KafkaTopics.BOOKING,
    containerFactory = "bookingRequestWrapperConcurrentKafkaListenerContainerFactory")
    public void bookingRequestListen(BookingRequestWrapper request)
    {
        System.out.println(request);
        serviceProvider.execute(request);
    }
}
