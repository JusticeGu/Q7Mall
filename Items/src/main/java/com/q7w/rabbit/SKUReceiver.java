
package com.q7w.rabbit;

import com.q7w.Service.SkuService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


/**
 * @author longzhonghua
 * @data 2019/02/03 11:07
 */

@Component
@RabbitListener(queues ="Item_queue")//监听QueueHello的消息队列
public class SKUReceiver {
    @Autowired
    SkuService skuService;
    private static Logger LOGGER = LoggerFactory.getLogger(SKUReceiver.class);
    @RabbitHandler//@RabbitHandler来实现具体消费
    public void QueueReceiver(Map res) throws IOException {

        try {
            LOGGER.info("正在处理SKU库存"+ (Integer)res.get("skuid")+":"+(Integer)res.get("op"));
            skuService.opsql( (Integer)res.get("skuid"), (Integer)res.get("op"), (Integer)res.get("num"));

        }catch (Exception e){
            System.out.println("接收异常："+e);
        }
    }

}
