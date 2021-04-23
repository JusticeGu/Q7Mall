package com.q7w.mq;

/**
 * 取消订单消息的处理者
 * @author xiaogu
 * @date 2021/4/19 17:48
 **/

import com.q7w.Service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {
    private static Logger LOGGER =LoggerFactory.getLogger(CancelOrderReceiver.class);
    @Autowired
    OrderService orderService;
    @RabbitHandler
    public void handle(Long orderId){
        LOGGER.info("正在审核订单：orderId:{}",orderId);
        orderService.cancelorder(orderId);
    }
}

