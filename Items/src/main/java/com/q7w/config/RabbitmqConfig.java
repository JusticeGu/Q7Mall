package com.q7w.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RabbitmqConfig {
    public static final String QUENE_NAME="Item_queue";//队列名称
    public static final String EXCHANGE="Item_exchange";//交换器名称
    public static final String ROUTEKEY="Item_routekey";

    @Autowired
    private Environment env;

    @Bean
    Queue queue() {//队列
        String name =QUENE_NAME;
        // 是否持久化
        boolean durable = true;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;

        return new Queue(name, durable, exclusive, autoDelete);
    }


    @Bean
    TopicExchange exchange() {//交换器
        String name = EXCHANGE;
        // 是否持久化
        boolean durable = true;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;

        return new TopicExchange(name,durable,autoDelete);
    }

    @Bean
    Binding binding() {//绑定
        return BindingBuilder.bind(queue()).to(exchange()).with("Item_queue");
    }
}
