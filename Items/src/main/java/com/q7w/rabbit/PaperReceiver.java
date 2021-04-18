
package com.q7w.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author longzhonghua
 * @data 2019/02/03 11:07
 */

@Component
@RabbitListener(queues ="Item_queue")//监听QueueHello的消息队列
public class PaperReceiver {

    @RabbitHandler//@RabbitHandler来实现具体消费
    public void QueueReceiver(Map res) {

        try {
            System.out.println("Mail：已接收到来自队列正在处理..."+res);
        }catch (Exception e){
            System.out.println("接收异常："+e);
        }


    }

}
