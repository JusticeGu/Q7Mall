package com.q7w.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xiaogu
 * @date 2021/4/4 22:36
 **/
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.q7w")
public class Q7mallAuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run( Q7mallAuthServiceApplication.class, args);
        System.out.println("认证核心服务已启动...");
    }
}
