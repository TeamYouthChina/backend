package com.youthchina.service;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.security.VerifyEmailDTO;
import com.youthchina.service.util.MessageSendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhongyangwu on 5/6/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageSendServiceTest {
    @Autowired
    MessageSendService messageSendService;

    @Test
    public void sendMessage() {
        User user = new User();
        user.setFirstName("test");
        user.setLastName("test");
        VerifyEmailDTO verifyEmailDTO = new VerifyEmailDTO(user, "2347928rfid");
//        for(int i = 0; i < 10; i++){
//            this.messageSendService.sendMessage(verifyEmailDTO);
//        }
    }
}