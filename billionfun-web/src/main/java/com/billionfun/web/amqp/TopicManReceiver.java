package com.billionfun.web.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhuyi Email:zhuyi@co-mall.com
 * @since 2021/3/18 3:55 下午
 */
@Component
@RabbitListener(queues = "topic.man")
public class TopicManReceiver {
    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicManReceiver消费者收到消息  : " + testMessage.toString());
    }
}
