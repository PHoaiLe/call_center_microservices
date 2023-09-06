package org.com.Receiver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@SpringBootApplication
public class Receiver
{
    public static void main(String[] args) {
        SpringApplication.run(Receiver.class, args);
    }

    @GetMapping("/")
    public boolean none()
    {
        return false;
    }
}
