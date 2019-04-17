package com.youthchina.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.dto.community.article.EssayRequestDTO;
import com.youthchina.dto.util.RichTextRequestDTO;
import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static com.youthchina.util.CustomMockMvcMatchers.partialContent;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by hongshengzhang on 2/17/19.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class EssayControllerTest extends BaseControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CommunityMapper communityMapper;

    @Value("${web.url.prefix}")
    private String urlPrefix;

    private AuthGenerator authGenerator = new AuthGenerator();

    MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    public void getEssayTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/articles/4")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":4,\"title\":\"腾讯咋样\",\"company\":{\"id\":37,\"name\":\"深圳市腾讯计算机系统有限公司\",\"avatarUrl\":null,\"location\":null,\"website\":\"https://www.tencent.com\",\"note\":\"腾讯科技股份有限公司（港交所：700）是中国规模最大的互联网公司，1998年11月由马化腾、张志东、陈一丹、许晨晔、曾李青5位创始人共同创立，总部位于深圳南山区腾讯大厦。腾讯业务拓展... \",\"nation\":\"中国\",\"photoUrlList\":null,\"jobCount\":18,\"collected\":false},\"create_at\":1546300800000,\"modified_at\":1546300800000,\"author\":{\"id\":4,\"username\":\"ABC\",\"email\":\"123456@123.com\",\"register_date\":1546300800000,\"first_name\":\"AAA\",\"last_name\":\"AAABBBCCC\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":null,\"age\":25,\"phone_number\":\"1234657890123\"},\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"未知\",\"compiletype\":1},\"is_anonymous\":false,\"upvoteCount\":2,\"downvoteCount\":0,\"attentionCount\":4,\"evaluateStatus\":3,\"attention\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }


    @Test
    public void addEssayTest() throws Exception {
        String json = "wqewqewqe";
        String pre = "pre";
        RichTextRequestDTO richTextDTO = new RichTextRequestDTO();
        richTextDTO.setBraftEditorRaw(json);
        richTextDTO.setPreviewText(pre);
        richTextDTO.setCompiletype(1);

        EssayRequestDTO requestEssayDTO = new EssayRequestDTO();
        requestEssayDTO.setTitle("This is a new article Title");
        //RichTextDTOResponse richTextDTO = new RichTextDTOResponse();
        //richTextDTO.setBraftEditorRaw("{\n" +
        //       "  \"this\": \"that\"\n" +
        //        "}");
        //richTextDTO.setPreviewText("This is a new article Abbre");
        requestEssayDTO.setBody(richTextDTO);

        //  requestEssayDTO.setCompany_id(1);
        requestEssayDTO.setIs_anonymous(false);
        requestEssayDTO.setRela_id(1);
        requestEssayDTO.setRela_type(1);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(requestEssayDTO);

        this.mvc.perform(
                post(this.urlPrefix + "/articles").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(partialContent("{\"content\":{\"id\":54,\"title\":\"This is a new article Title\",\"company\":{\"id\":1,\"name\":\"中国石油化工股份有限公司\",\"avatarUrl\":null,\"location\":\"110000\",\"website\":\"http://www.sinopec.com\",\"note\":\"中国石油化工股份有限公司是一家上中下游一体化、石油石化主业突出、拥有比较完备销售网络、境内外上市的股份制企业。中国石化是由中国石油化工集团公司依据《中华人民共和国公司法... \",\"nation\":\"中国\",\"photoUrlList\":[null,null,null,null],\"jobCount\":0,\"collected\":false},\"create_at\":\"2019-04-17T06:29:00.533+0000\",\"modified_at\":\"2019-04-17T06:29:00.533+0000\",\"author\":{\"id\":1,\"username\":\"YihaoGuo\",\"email\":\"test@test.com\",\"register_date\":null,\"first_name\":\"John\",\"last_name\":\"Doe\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":[\"APPLICANT\"],\"age\":0,\"phone_number\":\"2022922222\"},\"body\":{\"braftEditorRaw\":\"wqewqewqe\",\"previewText\":\"pre\",\"compiletype\":1},\"is_anonymous\":false,\"upvoteCount\":null,\"downvoteCount\":null,\"attentionCount\":null,\"evaluateStatus\":null,\"attention\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}","$.content.create_at","$.content.id","$.content.company.avatarUrl","$.content.company.photoUrlList","$.content.modified_at"));
    }

    @Test
    public void updateEssayTest() throws Exception {
        EssayRequestDTO requestEssayDTO = new EssayRequestDTO();
        requestEssayDTO.setTitle("This is a new Title 1");

        String json = "werr";
        String pre = "pre";
        RichTextRequestDTO richTextDTO = new RichTextRequestDTO();
        richTextDTO.setBraftEditorRaw(json);
        richTextDTO.setPreviewText(pre);
        richTextDTO.setCompiletype(1);

        requestEssayDTO.setBody(richTextDTO);
        requestEssayDTO.setRela_id(1);
        requestEssayDTO.setRela_type(1);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(requestEssayDTO);

        this.mvc.perform(

                put(this.urlPrefix + "/articles/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(partialContent("{\"content\":{\"id\":1,\"title\":\"This is a new Title 1\",\"company\":{\"id\":1,\"name\":\"中国石油化工股份有限公司\",\"avatarUrl\":null,\"location\":\"110000\",\"website\":\"http://www.sinopec.com\",\"note\":\"中国石油化工股份有限公司是一家上中下游一体化、石油石化主业突出、拥有比较完备销售网络、境内外上市的股份制企业。中国石化是由中国石油化工集团公司依据《中华人民共和国公司法... \",\"nation\":\"中国\",\"photoUrlList\":[null,null,null,null],\"jobCount\":0,\"collected\":false},\"create_at\":null,\"modified_at\":\"2019-04-17T06:29:01.107+0000\",\"author\":{\"id\":1,\"username\":\"YihaoGuo\",\"email\":\"test@test.com\",\"register_date\":null,\"first_name\":\"John\",\"last_name\":\"Doe\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":[\"APPLICANT\"],\"age\":0,\"phone_number\":\"2022922222\"},\"body\":{\"braftEditorRaw\":\"werr\",\"previewText\":\"pre\",\"compiletype\":1},\"is_anonymous\":false,\"upvoteCount\":null,\"downvoteCount\":null,\"attentionCount\":null,\"evaluateStatus\":null,\"attention\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}","$.content.id","$.content.company.avatarUrl","$.content.company.photoUrlList","$.content.modified_at"));

    }


    @Test
    public void deleteEssayTest() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/articles/1")
                        .with(authGenerator.authentication())

        )
                .andDo(print());

        this.mvc.perform(
                get(this.urlPrefix + "/articles/1")
                        .with(authGenerator.authentication())

        )
                .andDo(print());


    }

    @Test
    public void addCommentTest() throws Exception {
        this.mvc.perform(
                post(this.urlPrefix + "/articles/1/comments").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(readJson("requests/post-comment.json"))
                        .with(authGenerator.authentication())
        )
                .andDo(print())
              .andExpect(partialContent("{\"content\":{\"id\":31,\"creator\":{\"id\":1,\"username\":\"YihaoGuo\",\"email\":\"test@test.com\",\"register_date\":null,\"first_name\":\"John\",\"last_name\":\"Doe\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":[\"APPLICANT\"],\"age\":0,\"phone_number\":\"2022922222\"},\"body\":\"何老师你要是被绑架了就扎扎眼！\",\"create_at\":1555482540750,\"is_anonymous\":false,\"modified_at\":1555482540750,\"upvoteCount\":null,\"downvoteCount\":null,\"evaluateStatus\":null},\"status\":{\"code\":201,\"reason\":\"success\"}}","$.content.id","$.content.modified_at","$.content.create_at"));
   }

    @Test
    public void updateAttention() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/articles/3/attention").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void deleteAttention() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/articles/1/attention").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        );

        this.mvc.perform(
                delete(this.urlPrefix + "/articles/attentions/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void getEssayComments() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/articles/1/comments")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"offset\":0,\"limit\":2147483646,\"data\":[{\"id\":16,\"creator\":null,\"body\":\"好好好\",\"create_at\":1549929600000,\"is_anonymous\":true,\"modified_at\":1549929600000,\"upvoteCount\":1,\"downvoteCount\":1,\"evaluateStatus\":3}],\"page_count\":0,\"item_count\":1,\"page_index\":0,\"is_first\":true,\"is_last\":false},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void countEssay() {

    }
}
