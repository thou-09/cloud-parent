package com.itany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TestConfigClientMain {
    public static void main(String[] args) {
        SpringApplication.run(TestConfigClientMain.class, args);
    }
}
