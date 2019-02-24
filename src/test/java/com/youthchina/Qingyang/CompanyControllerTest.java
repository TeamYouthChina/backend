package com.youthchina.Qingyang;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.Country;
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
public class CompanyControllerTest {

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
    public void testgetCompany() throws Exception {
        Integer id = 1;
        this.mvc.perform(
                get(this.urlPrefix + "/companies/" + id)//.param("id", "1").param("detailLevel", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":" +id+ ",\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\",\"status\":null},\"status\":{\"code\":2000,\"reason\":\"\"}}",false))
        ;
    }

    @Test
    public void testAddCompany() throws Exception {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName("Vavle");
        Location location = new Location();
        location.setRegion_num(1);
        companyDTO.setLocation(new LocationDTO(location));
        Country country = new Country();
        country.setCountryAbbre("USA");
        companyDTO.setNation(new NationDTO(country));
        companyDTO.setWebsite("vavle.com");
        companyDTO.setAvatarUrl("vavle.com/AvatarUrl");
        companyDTO.setNote("Steam");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String insertJson = ow.writeValueAsString(companyDTO);


        this.mvc.perform(
                post(this.urlPrefix + "/companies")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(insertJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"name\":\"Vavle\",\"location\":{\"nation_code\":\"CHN\",\"location_code\":\"1\"},\"website\":\"vavle.com\",\"nation\":{\"countryAbbre\":\"USA\"},\"avatarUrl\":\"vavle.com/AvatarUrl\",\"note\":\"Steam\",\"userId\":1},\"status\":{\"code\":2000,\"reason\":\"\"}}",false))
        ;
    }

    @Test
    public void testUpdateCompany() throws Exception {
        int id = 1;
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(id);
        companyDTO.setName("Vavle");
        Location location = new Location();
        location.setRegion_num(1);
        companyDTO.setLocation(new LocationDTO(location));
        Country country = new Country();
        country.setCountryAbbre("USA");
        companyDTO.setNation(new NationDTO(country));
        companyDTO.setWebsite("vavle.com");
        companyDTO.setAvatarUrl("vavle.com/AvatarUrl");
        companyDTO.setNote("Steam");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String insertJson = ow.writeValueAsString(companyDTO);

        System.out.println(insertJson);
        this.mvc.perform(
                put(this.urlPrefix + "/companies/" + id)
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(insertJson)

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":" + id + ",\"name\":\"Vavle\",\"location\":{\"nation_code\":\"CHN\",\"location_code\":\"1\"},\"website\":\"vavle.com\",\"nation\":{\"countryAbbre\":\"USA\"},\"avatarUrl\":\"vavle.com/AvatarUrl\",\"note\":\"Steam\",\"userId\":1},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;

    }


}
