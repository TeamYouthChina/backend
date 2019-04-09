package com.youthchina.service.Qinghong;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: youthchina
 * @description: 生产者实现
 * @author: Qinghong Wang
 * @create: 2019-03-05 11:32
 **/

@Service
public class MessageSendServiceImpt implements MessageSendService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String message) {
        this.amqpTemplate.convertAndSend("email", message);
    }
}
