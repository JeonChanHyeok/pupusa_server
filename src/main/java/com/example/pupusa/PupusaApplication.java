package com.example.pupusa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PupusaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PupusaApplication.class, args);
    }

}
