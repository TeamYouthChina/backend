package com.youthchina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.dto.job.JobRequestDTO;
import com.youthchina.dto.util.LocationDTO;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static com.youthchina.util.CustomMockMvcMatchers.partialContent;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


/**
 * Created by zhongyangwu on 2/6/19.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class JobControllerTest extends BaseControllerTest {

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
    public void testGetJob() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/jobs/1").param("id", "1").param("detailLevel", "1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":1,\"name\":\"产品信息管理实习生\",\"organization\":{\"id\":37,\"name\":\"深圳市腾讯计算机系统有限公司\",\"avatarUrl\":null,\"location\":null,\"website\":\"https://www.tencent.com\",\"note\":\"腾讯科技股份有限公司（港交所：700）是中国规模最大的互联网公司，1998年11月由马化腾、张志东、陈一丹、许晨晔、曾李青5位创始人共同创立，总部位于深圳南山区腾讯大厦。腾讯业务拓展... \",\"nation\":\"中国\"},\"location\":[\"440100\"],\"type\":\"实习\",\"deadLine\":1577750400000,\"job_duty\":\"在读硕士研究生, 如果是2019年毕业生, 有转正机会；熟悉产品信息管理\",\"job_description\":\"产品信息管理实习生\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testDeleteJob() throws Exception {
        Integer id = 1;
        this.mvc.perform(
                delete(this.urlPrefix + "/jobs/" + id)//.param("id", "1").param("detailLevel", "1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;
    }

    @Test
    public void testAddJob() throws Exception {
        this.mvc.perform(
                post(this.urlPrefix + "/jobs")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(readJson("requests/post-job.json"))
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(partialContent(readJson("responses/post-job.json"),"$.content.id"))
        ;
    }

    @Test
    public void testUpdateJob() throws Exception {
        int id = 1;
        JobRequestDTO jobRequestDTO = new JobRequestDTO();
        jobRequestDTO.setId(id);
        jobRequestDTO.setName("front");
        jobRequestDTO.setOrganization_id(2);
        jobRequestDTO.setType("1");
        jobRequestDTO.setJob_description("996ICU");
        jobRequestDTO.setStartTime("4070908800");
        jobRequestDTO.setDeadLine("41070908800");
        jobRequestDTO.setJob_duty("FullStack");
//        List<Integer> locationIdList = new ArrayList<>();
//        locationIdList.add(994701);
        List<LocationDTO> locationDTOList = new ArrayList<>();
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocation_code("994701");
        locationDTOList.add(locationDTO);
        jobRequestDTO.setLocation(locationDTOList);


        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String insertJson = ow.writeValueAsString(jobRequestDTO);
        this.mvc.perform(
                put(this.urlPrefix + "/jobs/" + id)
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(insertJson)

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":1,\"name\":\"front\",\"organization\":{\"id\":2,\"name\":\"中国石油天然气股份有限公司\",\"avatarUrl\":null,\"location\":\"110000\",\"website\":\"http://www.petrochina.com.cn\",\"note\":\"中国石油天然气股份有限公司是中国油气行业占主导地位的最大的油气生产和销售商，是国有企业，是中国销售收入最大的公司之一，也是世界最大的石油公司之一。\",\"nation\":\"中国\"},\"location\":[\"994701\"],\"type\":\"实习\",\"startTime\":4060800000,\"deadLine\":41040000000,\"job_duty\":\"FullStack\",\"job_description\":\"996ICU\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }


    @Test
    public void testSearchJob() throws Exception {
        this.mvc.perform(
                post(this.urlPrefix + "/jobs/search?page=1&limit=10")
                        .content("{\n" +
                                "  \"industry\": [\n" +
                                "    \"test\",\n" +
                                "    \"test\"\n" +
                                "  ]\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())

        )
                .andDo(print())
        ;
    }

}
