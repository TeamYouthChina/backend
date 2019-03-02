package com.youthchina.Qingyang;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.Country;
import com.youthchina.dto.Applicant.ResumeRequestDTO;
import com.youthchina.dto.CompanyDTO;
import com.youthchina.dto.LocationDTO;
import com.youthchina.dto.NationDTO;
import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-16
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:company.xml"})
@WebAppConfiguration
public class ResumeJsonControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${web.url.prefix}")
    private String urlPrefix;

    private AuthGenerator authGenerator = new AuthGenerator();

    MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    public void insertTest() throws Exception{
        Integer id = 1;
        ResumeRequestDTO resumeRequestDTO = new ResumeRequestDTO();
        List<String> json = new ArrayList<>();

        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "1111");
        map.put(2,"2222");

        json.add("1111");
        json.add("2222");
        resumeRequestDTO.setJson(json);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String insertJson = ow.writeValueAsString(resumeRequestDTO);

        this.mvc.perform(
                post(this.urlPrefix + "/resumes")//.param("id", "1").param("detailLevel", "1")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(insertJson)
        )
                .andDo(print())
           //     .andExpect(content().json("{\"content\":{\"id\":" +id+ ",\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"status\":{\"code\":2000,\"reason\":\"\"}}",false))
        ;
    }
}