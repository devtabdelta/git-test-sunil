package com.ttek.testtask.javaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ttek.testtask.javaspring"})

public class DevTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevTestApplication.class, args);
    }

}
