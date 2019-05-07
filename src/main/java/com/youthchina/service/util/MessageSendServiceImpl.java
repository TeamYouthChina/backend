package com.youthchina.service.util;

import com.youthchina.dto.security.VerifyEmailDTO;
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
public class MessageSendServiceImpl implements MessageSendService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String message) {
        this.amqpTemplate.convertAndSend("email", message);
    }

    @Override
    public void sendMessage(VerifyEmailDTO verifyEmailDTO) {
        this.amqpTemplate.convertAndSend("email", verifyEmailDTO);
    }
}
