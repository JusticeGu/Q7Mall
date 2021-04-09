package com.q7w;

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
@SpringBootApplication
public class Q7mallUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run( Q7mallUserServiceApplication.class, args);
        System.out.println("用户核心服务已启动...");
    }
}
