package com.elan.config;

import jdk.management.resource.internal.inst.SimpleAsynchronousFileChannelImplRMHooks;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: create by elan
 * @version: v1.0
 * @description: com.elan.config
 * @date:2019/10/19
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean("taskExecutor")
    public Executor asyncConfigExcutor(){
        ThreadPoolTaskExecutor excutor = new ThreadPoolTaskExecutor();
        excutor.setCorePoolSize(5);
        excutor.setMaxPoolSize(5);
        excutor.setQueueCapacity(500);
        excutor.setKeepAliveSeconds(60);
        excutor.setThreadNamePrefix("WeatherDataSync-");
        excutor.initialize();
        return excutor;
    }

    @Bean("simpleExecutor")
    public Executor asyncExcutor(){
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        return executor;
    }

}
