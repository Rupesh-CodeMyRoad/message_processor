package com.xgileit.mp.messageprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MessageprocessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageprocessorApplication.class, args);
    }

}
