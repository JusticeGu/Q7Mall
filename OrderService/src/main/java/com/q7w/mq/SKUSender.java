package com.q7w.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SKUSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Map map) {
        System.out.println("正在上传至队列");
        //使用AmqpTemplate将消息发送到消息队列QueueHello中去
        this.rabbitTemplate.convertAndSend("Item_exchange","Item_queue",map);
    }
    public boolean sendmsg(int sid, int op, int num){
        Map map = new HashMap();
        map.put("skuid",sid);
        map.put("op",op);
        map.put("num",num);
        send(map);
        return true;
    }
}