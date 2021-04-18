package com.q7w.rabbit;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SenderA {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Map map) {
        System.out.println("正在上传至队列");
        //使用AmqpTemplate将消息发送到消息队列QueueHello中去
        this.rabbitTemplate.convertAndSend("Item_exchange","Item_queue",map);
    }
    public boolean sendmsg(int kid, int pid, String uno,Map ansmap){
        Map map = new HashMap();
        map.put("kid",kid);
        map.put("pid",pid);
        map.put("uno",uno);
        map.put("ansmap",ansmap);
        send(map);
        return true;
    }
}