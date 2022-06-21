package com.triple.mytrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MytripApplication {

    public static void main(String[] args) {
        SpringApplication.run(MytripApplication.class, args);
    }

}
