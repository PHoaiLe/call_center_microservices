package org.com.BookingService;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class BookingService
{
    public static void main(String[] args)
    {
        SpringApplication.run(BookingService.class, args);
    }
}
