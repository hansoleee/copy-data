package com.example.copydata;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing
@SpringBootApplication
public class CopyDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(CopyDataApplication.class, args);
    }

}
