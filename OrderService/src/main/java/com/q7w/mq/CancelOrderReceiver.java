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
        try {
            LOGGER.info("正在审核订单：orderId:{}",orderId);
            int status = orderService.getorderstatus(orderId);
            if (status == 0){
                LOGGER.info("逾期订单，加入取消队列：orderId:{}",orderId);
                orderService.cancelorder(orderId);
            }else if(status == 1){
                LOGGER.info("已支付订单，放行：orderId:{}",orderId);
            }else {
                LOGGER.info("重复消费订单：orderId:{}",orderId);
            }
        }catch (Exception e){
            LOGGER.info("订单不存在：orderId:{}",orderId);
        }

    }
}

