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
                .andExpect(content().json("{\"content\":{\"id\":4,\"title\":\"腾讯咋样\",\"company\":{\"id\":37,\"name\":\"深圳市腾讯计算机系统有限公司\",\"avatarUrl\":null,\"location\":\"440300\",\"website\":\"https://www.tencent.com/\",\"note\":\"腾讯科技股份有限公司（港交所：700）是中国规模最大的互联网公司，1998年11月由马化腾、张志东、陈一丹、许晨晔、曾李青5位创始人共同创立，总部位于深圳南山区腾讯大厦。腾讯业务拓展至社交、娱乐、金融、资讯、工具和平台等不同领域。目前，腾讯拥有中国国内使用人数最多的社交软件腾讯QQ和微信，以及中国国内最大的网络游戏社区腾讯游戏。在电子书领域，旗下有阅文集团，运营有QQ读书和微信读书。腾讯于2004年6月16日在香港交易所挂牌上市，于2016年9月5日首次成为亚洲市值最高的上市公司，并于2017年11月21日成为亚洲首家市值突破5000亿美元的公司。2017年，腾讯首次跻身《财富》杂志世界500强排行榜，以228.7亿美元的营收位居478位。\",\"nation\":\"中国\",\"photoUrlList\":null,\"jobCount\":18,\"collected\":false},\"create_at\":1546300800000,\"modified_at\":1546300800000,\"author\":{\"id\":4,\"email\":\"123456@123.com\",\"register_date\":1546300800000,\"date_of_birth\":0,\"first_name\":\"AAA\",\"last_name\":\"AAABBBCCC\",\"gender\":\"MALE\",\"avatar_url\":\"---\",\"role\":null,\"phone_number\":\"1234657890123\"},\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"未知\",\"compiletype\":1},\"is_anonymous\":false,\"upvoteCount\":2,\"downvoteCount\":0,\"attentionCount\":4,\"evaluateStatus\":3,\"attention\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
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
                .andExpect(partialContent(" {\"content\":{\"id\":54,\"title\":\"This is a new article Title\",\"company\":{\"id\":1,\"name\":\"中国石油化工股份有限公司\",\"avatarUrl\":null,\"location\":\"110000\",\"website\":\"http://www.sinopec.com/\",\"note\":\"中国石油化工股份有限公司是一家上中下游一体化、石油石化主业突出、拥有比较完备销售网络、境内外上市的股份制企业。中国石化是由中国石油化工集团公司依据《中华人民共和国公司法》，以独家发起方式于2000年2月25日设立的股份制企业。\",\"nation\":\"中国\",\"photoUrlList\":null,\"jobCount\":0,\"collected\":false},\"create_at\":1558014108987,\"modified_at\":1558014108987,\"author\":{\"id\":1,\"email\":\"test@test.com\",\"register_date\":1546304461000,\"date_of_birth\":0,\"first_name\":\"Yihao\",\"last_name\":\"Guo\",\"gender\":\"MALE\",\"avatar_url\":null,\"role\":[\"APPLICANT\"],\"phone_number\":\"2022922222\"},\"body\":{\"braftEditorRaw\":\"wqewqewqe\",\"previewText\":\"pre\",\"compiletype\":1},\"is_anonymous\":false,\"upvoteCount\":null,\"downvoteCount\":null,\"attentionCount\":null,\"evaluateStatus\":null,\"attention\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}", "$.content.create_at", "$.content.id", "$.content.company.avatarUrl", "$.content.company.photoUrlList", "$.content.modified_at"));
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
                .andExpect(partialContent("{\"content\":{\"id\":1,\"title\":\"This is a new Title 1\",\"company\":{\"id\":1,\"name\":\"中国石油化工股份有限公司\",\"avatarUrl\":null,\"location\":\"110000\",\"website\":\"http://www.sinopec.com/\",\"note\":\"中国石油化工股份有限公司是一家上中下游一体化、石油石化主业突出、拥有比较完备销售网络、境内外上市的股份制企业。中国石化是由中国石油化工集团公司依据《中华人民共和国公司法》，以独家发起方式于2000年2月25日设立的股份制企业。\",\"nation\":\"中国\",\"photoUrlList\":null,\"jobCount\":0,\"collected\":false},\"create_at\":null,\"modified_at\":1558014109940,\"author\":{\"id\":1,\"email\":\"test@test.com\",\"register_date\":1546304461000,\"date_of_birth\":0,\"first_name\":\"Yihao\",\"last_name\":\"Guo\",\"gender\":\"MALE\",\"avatar_url\":null,\"role\":[\"APPLICANT\"],\"phone_number\":\"2022922222\"},\"body\":{\"braftEditorRaw\":\"werr\",\"previewText\":\"pre\",\"compiletype\":1},\"is_anonymous\":false,\"upvoteCount\":null,\"downvoteCount\":null,\"attentionCount\":null,\"evaluateStatus\":null,\"attention\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}", "$.content.id", "$.content.company.avatarUrl", "$.content.company.photoUrlList", "$.content.modified_at"));

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
                .andExpect(partialContent("{\"content\":{\"id\":30,\"creator\":{\"id\":1,\"email\":\"Admin1\",\"register_date\":1546300800000,\"date_of_birth\":0,\"first_name\":\"Admin1\",\"last_name\":\"Admin1\",\"gender\":\"MALE\",\"avatar_url\":\"---\",\"role\":[\"ROOT\"],\"phone_number\":\"12345\"},\"body\":\"何老师你要是被绑架了就扎扎眼！\",\"create_at\":1557999709000,\"is_anonymous\":false,\"modified_at\":1557999709000,\"upvoteCount\":0,\"downvoteCount\":0,\"evaluateStatus\":3},\"status\":{\"code\":201,\"reason\":\"success\"}}", "$.content.id", "$.content.modified_at", "$.content.create_at"));
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
                post(this.urlPrefix + "/articles/1/comments").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(readJson("requests/post-comment.json"))
                        .with(authGenerator.authentication())
        );
        this.mvc.perform(
                get(this.urlPrefix + "/articles/1/comments")
                        .with(authGenerator.authentication())
        )
                .andDo(print());
                //.andExpect(content().json("{\"content\":{\"offset\":0,\"limit\":2147483646,\"data\":[{\"id\":16,\"creator\":{\"id\":2,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"register_date\":1546300800000,\"first_name\":\"DDD\",\"last_name\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":28,\"phone_number\":\"9876543210123\"},\"body\":\"好好好\",\"create_at\":1549929600000,\"is_anonymous\":true,\"modified_at\":1549929600000,\"upvoteCount\":0,\"downvoteCount\":0,\"evaluateStatus\":3}],\"page_count\":0,\"item_count\":1,\"page_index\":0,\"is_first\":true,\"is_last\":false},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

}
