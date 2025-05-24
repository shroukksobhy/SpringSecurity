package com.example.learningspringsecurity; // <-- This package should match your test's package

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // <-- This annotation is crucial
public class LearningSpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningSpringSecurityApplication.class, args);
    }

}