package com.q7w;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xiaogu
 * @date 2021/8/9 01:10
 **/
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class SchoolServiceApp {
    public static void main(String[] args) {
        SpringApplication.run( SchoolServiceApp.class, args);
        System.out.println("校务核心服务已启动...");
    }
}
