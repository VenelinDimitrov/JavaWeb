package com.example.beardculture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BeardCultureApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeardCultureApplication.class, args);
    }

}
