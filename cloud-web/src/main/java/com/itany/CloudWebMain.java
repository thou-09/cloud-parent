package com.itany;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class})
@EnableFeignClients(basePackages = {"com.itany.client"})
@MapperScan("com.itany.dao")
public class CloudWebMain {

    public static void main(String[] args) {
        SpringApplication.run(CloudWebMain.class, args);
    }
}
