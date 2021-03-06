package com.q7w;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author xiaogu
 * @date 2020/8/5 21:04
 **/

@SpringBootApplication
@EnableDiscoveryClient
public class Q7mallItemApplication {
    public static void main(String[] args) {
        SpringApplication.run( Q7mallItemApplication.class, args);
        System.out.println("商品核心服务已启动...");
    }
}
