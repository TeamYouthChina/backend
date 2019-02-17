package com.youthchina.Qinghong;

import com.aliyun.oss.internal.OSSUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dto.UserDTO;
import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * @program: youthchina
 * @description: 对于controller的测试
 * @author: Qinghong Wang
 * @create: 2019-02-10 14:47
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:applicant.xml"})
@WebAppConfiguration
public class ControllerTest {

    @Autowired
    WebApplicationContext context;

    private AuthGenerator authGenerator = new AuthGenerator();

    MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test

    public void testRegisterEmail() throws Exception{
        UserDTO userDTO=new UserDTO();
        userDTO.setEmail("hmgswqh@gmail.com");
        userDTO.setUsername("hmgswqh");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(userDTO);
        this.mvc.perform(MockMvcRequestBuilders.post("/registerEmail").contentType(MediaType.APPLICATION_JSON).content(requestJson).with(authGenerator.authentication())
                ).andDo(print());
//        System.out.print(s);

    }

    @Test
    public void testResumeEmail() throws Exception{
        File file = new File("/Users/wangqinghong/Desktop/YouthChina resume/resume.pdf");
        //文件之外的参数
        MockMultipartFile firstFile = new MockMultipartFile("file", "resume.pdf",
                MediaType.TEXT_PLAIN_VALUE, new FileInputStream(file));


        this.mvc.perform(MockMvcRequestBuilders
                .multipart("/sendResume")
                .file(firstFile)
                .with(authGenerator.authentication())
        ).andDo(print());

    }


}
