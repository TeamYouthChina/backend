package com.youthchina.Qinghong;

import com.google.common.base.Verify;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.service.Qinghong.MailService;
import com.youthchina.service.Qinghong.MailServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTests {

    @Autowired
    private MailServiceImpl mailService;

    @InjectMocks
    private MailServiceImpl mailService1;


    @Autowired
    private SpringTemplateEngine templateEngine;
    @Mock
    private JavaMailSender javaMailSender;

    @Value("hmgswqh@gmail.com")
    private String from;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSimpleMail() throws Exception {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        mailService1.sendSimpleMail("923793518@qq.com", "test simple mail", "hello this is simple mail");
        //Mockito.when(javaMailSender.send(simpleMailMessage)).thenReturn(simpleMailMessage);
        verify(javaMailSender).send((SimpleMailMessage) argThat(new SimpleArgumentMatcher()));
    }

    @Test
    public void testAttachmentMail() throws Exception {
        String filePath = "/Users/wangqinghong/Desktop/YouthChina resume/resume.pdf";
        mailService.sendAttachmentsMail("hmgswqh@gmail.com", "test attachment mail", "hello this is a attachment mail", filePath);
//        verify(javaMailSender).send((MimeMessage) argThat(new AttachmentArgumentMatcher()));
    }

    @Test
    public void testTemplateMail() throws Exception {
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailtemplates", context);
        mailService.sendtemplateMail("hmgswqh@gmail.com", "test template mail", emailContent);
    }

    @Test
    public void testInlineMail() throws Exception {
        String recId = "00011";
        String recPath = "/Users/wangqinghong/Desktop/YouthChina resume/picture.jpeg";
        mailService.sendInlineResourceMail("hmgswqh@gmail.com", "test inline mail", "hello this is a inline mail", recPath, recId);
    }

    @Test
    public void testRegisterMail() throws Exception {
        Map<String, Object> valueMap = new HashMap<>();
        User user=new User();
        user.setUsername("wqh");
        user.setEmail("111");
        valueMap.put("to", "hmgswqh@gmail.com");
        valueMap.put("subject", "申请账户邮件");
        valueMap.put("email", "hmgswqh@gmail.com");
        valueMap.put("User",user);
        mailService.sendUserRegisterEmail(valueMap);
    }


     class SimpleArgumentMatcher implements org.mockito.ArgumentMatcher {
        @Override
        public boolean matches(Object o) {
            if (o instanceof SimpleMailMessage) {
                SimpleMailMessage simpleMailMessage = (SimpleMailMessage) o;
                if (simpleMailMessage.getSubject().equals("test simple mail")
                        &&simpleMailMessage.getTo()[0].equals("923793518@qq.com")
                        &&simpleMailMessage.getText().equals("hello this is simple mail")
                ) {
                    return true;
                }
            }
            return false;
        }
    }

//    class AttachmentArgumentMatcher implements org.mockito.ArgumentMatcher {
//        @Override
//        public boolean matches(Object o) {
//            if (o instanceof MimeMessage) {
//                MimeMessage mimeMessage = (MimeMessage) o;
//                MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage);
//                if (mimeMessageHelper.equals("test attachment mail")
//                        &&simpleMailMessage.get()[0].equals("hmgswqh@gmail.com")
//                        &&simpleMailMessage.getText().equals("hello this is a attachment mail")
//                        &&simpleMailMessage.get
//                        &&mimeMessageHelper.getMimeMessage().getSubject().equals()
//                ) {
//                    return true;
//                }
//            }
//            return false;
//        }
//    }
}

