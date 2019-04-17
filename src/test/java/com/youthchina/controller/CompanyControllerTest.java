package com.youthchina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.domain.Qinghong.Location;
import com.youthchina.dto.company.CompanyRequestDTO;
import com.youthchina.dto.util.LocationDTO;
import com.youthchina.service.util.StaticFileService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


/**
 * @author: Qingyang Zhao
 * @create: 2019-02-16
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class CompanyControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private StaticFileService staticFileService;

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
    public void testGetCompany() throws Exception {
        Integer id = 1;
        this.mvc.perform(
                get(this.urlPrefix + "/companies/" + id)//.param("id", "1").param("detailLevel", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":1,\"name\":\"中国石油化工股份有限公司\",\"location\":\"110000\",\"website\":\"http://www.sinopec.com\",\"note\":\"中国石油化工股份有限公司是一家上中下游一体化、石油石化主业突出、拥有比较完备销售网络、境内外上市的股份制企业。中国石化是由中国石油化工集团公司依据《中华人民共和国公司法... \",\"nation\":\"中国\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testGetCompany2() throws Exception {
        Integer id = 2;
        this.mvc.perform(
                get(this.urlPrefix + "/companies/" + id)//.param("id", "1").param("detailLevel", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                //.andExpect(content().json("{\"content\":{\"id\":1,\"name\":\"中国石油化工股份有限公司\",\"location\":\"北京市\",\"website\":\"http://www.sinopec.com\",\"note\":\"中国石油化工股份有限公司是一家上中下游一体化、石油石化主业突出、拥有比较完备销售网络、境内外上市的股份制企业。中国石化是由中国石油化工集团公司依据《中华人民共和国公司法... \",\"nation\":\"中国\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testGetCompany37() throws Exception {
        Integer id = 37;
        this.mvc.perform(
                get(this.urlPrefix + "/companies/" + id)//.param("id", "1").param("detailLevel", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
        //.andExpect(content().json("{\"content\":{\"id\":1,\"name\":\"中国石油化工股份有限公司\",\"location\":\"北京市\",\"website\":\"http://www.sinopec.com\",\"note\":\"中国石油化工股份有限公司是一家上中下游一体化、石油石化主业突出、拥有比较完备销售网络、境内外上市的股份制企业。中国石化是由中国石油化工集团公司依据《中华人民共和国公司法... \",\"nation\":\"中国\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }
    

//    @Test
//    public void testGetRecommendFiveCompany() throws Exception {
//        this.mvc.perform(
//                get(this.urlPrefix + "/discovery/companies/")//.param("id", "1").param("detailLevel", "1")
//                        .with(authGenerator.authentication())
//
//        )
//                .andDo(print())
////                .andExpect(content().json("{\"content\":{\"id\":" + id + ",\"name\":\"\",\"avatarUrl\":\"\",\"location\":\"\",\"website\":\"\",\"note\":\"\",\"nation\":\"\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
//                //.andExpect(content().json("{\"content\":{\"id\":1,\"name\":\"中国石油化工股份有限公司\",\"avatarUrl\":null,\"location\":\"110000\",\"website\":\"http://www.sinopec.com\",\"note\":\"中国石油化工股份有限公司是一家上中下游一体化、石油石化主业突出、拥有比较完备销售网络、境内外上市的股份制企业。中国石化是由中国石油化工集团公司依据《中华人民共和国公司法... \",\"nation\":null},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
//        ;
//    }

    @Test
    public void testDeleteCompany() throws Exception {
        Integer id = 1;
        this.mvc.perform(
                delete(this.urlPrefix + "/companies/" + id)
                        .with(authGenerator.authentication())

        )
                .andDo(print())
        ;
    }




    @Test
    public void testAddCompany() throws Exception {
//        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"));
//        MockMultipartFile file2 = new MockMultipartFile("file2", "test2.txt", "multipart/form-data", "hello upload2".getBytes("UTF-8"));

        CompanyRequestDTO companyRequestDTO = new CompanyRequestDTO();
        companyRequestDTO.setName("Vavle");
        Location location = new Location();
        location.setRegionId(994701);
        location.setCountry("USA");
        companyRequestDTO.setLocation(new LocationDTO(location));
        companyRequestDTO.setNation("USA");
        companyRequestDTO.setWebsite("vavle.com");
        companyRequestDTO.setAvatarUrl("2856306669745344512");
        companyRequestDTO.setNote("Steam");
        List<String> photoIdList = new ArrayList<>();
        photoIdList.add("2856306669745344512");
        photoIdList.add("2858461057087705088");
        companyRequestDTO.setPhotoIdList(photoIdList);


        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String insertJson = ow.writeValueAsString(companyRequestDTO);
        System.out.println(insertJson);

        this.mvc.perform(
                post(this.urlPrefix + "/companies")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(insertJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                //,"avatarUrl":""
                .andExpect(content().json("{\"content\":{\"name\":\"Vavle\",\"location\":\"994701\",\"website\":\"vavle.com\",\"note\":\"Steam\",\"nation\":\"美国\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))

        ;
    }
    @Test
    public void getAllJobsOfOneCompany() throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/companies/37/jobs")
                        .with(authGenerator.authentication())
        )
                .andDo(print())

        ;
    }


    @Test
    public void testUpdateCompany() throws Exception {
        int id = 1;
        CompanyRequestDTO companyRequestDTO = new CompanyRequestDTO();
        companyRequestDTO.setId(id);
        companyRequestDTO.setName("Vavle");
        Location location = new Location();
        location.setRegionId(994701);
        location.setCountry("USA");
        companyRequestDTO.setLocation(new LocationDTO(location));
        companyRequestDTO.setNation("USA");
        companyRequestDTO.setWebsite("vavle.com");
        companyRequestDTO.setAvatarUrl("2858461057087705088");
        companyRequestDTO.setNote("Steam");
        List<String> photoUrlList = new ArrayList<>();
        photoUrlList.add("2856306669745344512");
        photoUrlList.add("2858461057087705088");

        companyRequestDTO.setPhotoIdList(photoUrlList);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String insertJson = ow.writeValueAsString(companyRequestDTO);

        System.out.println(insertJson);
        this.mvc.perform(
                put(this.urlPrefix + "/companies/" + id)
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(insertJson)

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":" + id + ",\"name\":\"Vavle\",\"location\":\"994701\",\"website\":\"vavle.com\",\"note\":\"Steam\",\"nation\":\"美国\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;

    }

//    @Test
//    public void getFileUrl(){
//        String s = staticFileService.getFileUrl("2856306669745344512","China").toString();
//        System.out.println(s);
//         s = staticFileService.getFileUrl("2858461057087705088","China").toString();
//        System.out.println(s);
//
//
//    }


}
