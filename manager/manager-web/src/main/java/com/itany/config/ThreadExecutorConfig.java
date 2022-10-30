package com.itany.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ThreadExecutorConfig.
 * spring 线程池
 *
 * @author Thou
 * @date 2022/10/27
 */
@Configuration
public class ThreadExecutorConfig {

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        /*
            核心线程数
            1， 一个线程任务被提交到线程池中
            2. 先找空闲线程，如果有则空闲线程去执行任务
            3. 当线程执行完处于空闲状态后，线程池会回收线程，知道存活数量等于核心线程数
         */
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors() + 1);
        /*
            任务等待队列大小
            1. 当核心线程数到达最大时，且没有空闲
            2. 当有新的任务提交后会放入任务队列中
         */
        executor.setQueueCapacity(20);
        /*
            线程空闲时间
            1. 线程空闲时间到后，线程会退出，直到存活的线程数等于核心线程数
         */
        executor.setKeepAliveSeconds(60);
        /*
            最大线程数
            1. 当任务队列已满，且核心线程数满没有空闲
            2. 则新提交的任务，会创建新的线程去执行，直到总的线程数量等于最大线程数量
         */
        executor.setMaxPoolSize(20);
        /*
           线程名称前缀
         */
        executor.setThreadNamePrefix("my-");
        /*
            拒绝策略
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
