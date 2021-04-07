package com.example.test1.service.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public FileWorkerImpl fileWorker() {
        return new FileWorkerImpl();
    }
    @Bean
    public ReverseServiceImpl reverseService() {
        return new ReverseServiceImpl();
    }
}
