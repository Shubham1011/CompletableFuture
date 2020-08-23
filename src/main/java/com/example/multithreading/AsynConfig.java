package com.example.multithreading;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class AsynConfig {

    @Bean("shubhamthread")
    public Executor threadPoolExecutor(){
        ThreadPoolTaskExecutor threadPoolExecutor=new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(3);
        threadPoolExecutor.setMaxPoolSize(3);
        threadPoolExecutor.setQueueCapacity(100);
        threadPoolExecutor.setThreadNamePrefix("shubham");
        return threadPoolExecutor;
    }
}
