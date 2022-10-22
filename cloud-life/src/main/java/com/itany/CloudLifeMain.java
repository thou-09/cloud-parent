package com.itany;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableCircuitBreaker//启动服务熔断
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
@EnableFeignClients(basePackages = {"com.itany.client"})
@MapperScan("com.itany.dao")
public class CloudLifeMain {
    public static void main(String[] args) {
        SpringApplication.run(CloudLifeMain.class, args);
    }



}
