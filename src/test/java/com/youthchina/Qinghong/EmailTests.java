package com.youthchina.Qinghong;

import com.youthchina.service.Qinghong.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTests {

    @Autowired
    private MailService mailService;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("923793518@qq.com","test simple mail"," hello this is simple mail");
    }

    @Test
    public void testAttachmentMail() throws Exception{
        String filePath="/Users/wangqinghong/Desktop/YouthChina resume/夏锐思中文简历.pdf";
        mailService.sendAttachmentsMail("hmgswqh@gmail.com","test attachment mail","hello this is a attachment mail",filePath);
    }
    @Test
    public void testTemplateMail() throws Exception{
        Context context=new Context();
        context.setVariable("id","006");
        String emailContent=templateEngine.process("emailtemplates",context);
        mailService.sendSimpleMail("hmgswqh@gmail.com","test template mail",emailContent);
    }

    @Test
    public void testInlineMail() throws Exception{
        String recId="00011";
        String recPath="/Users/wangqinghong/Desktop/YouthChina resume/picture.jpeg";
        mailService.sendInlineResourceMail("hmgswqh@gmail.com","test inline mail","hello this is a inline mail",recPath,recId);
    }
    @Test
    public void testRegisterMail() throws Exception{
        Map<String, Object> valueMap = new HashMap<>();
        valueMap.put("to", "hmgswqh@gmail.com");
        valueMap.put("object", "申请账户邮件");
        valueMap.put("email","hmgswqh@gmail.com");
        mailService.sendUserRegisterEmail(valueMap);
    }


}

