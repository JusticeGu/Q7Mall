package com.q7w;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xiaogu
 * @date 2021/3/31 16:11
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Q7mallOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run( Q7mallOrderApplication.class, args);
        System.out.println("订单核心服务已启动...");
    }
}
