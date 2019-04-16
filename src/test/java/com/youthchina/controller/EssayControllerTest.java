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
                .andExpect(content().json("{\"content\":{\"id\":4,\"title\":\"腾讯咋样\",\"company\":{\"id\":37,\"name\":\"深圳市腾讯计算机系统有限公司\",\"avatarUrl\":null,\"location\":null,\"website\":\"https://www.tencent.com\",\"note\":\"腾讯科技股份有限公司（港交所：700）是中国规模最大的互联网公司，1998年11月由马化腾、张志东、陈一丹、许晨晔、曾李青5位创始人共同创立，总部位于深圳南山区腾讯大厦。腾讯业务拓展... \",\"nation\":\"中国\",\"photoUrlList\":null,\"jobCount\":18,\"collected\":false},\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"modified_at\":\"2019-01-01T00:00:00.000+0000\",\"author\":{\"id\":4,\"username\":\"ABC\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"AAA\",\"last_name\":\"AAABBBCCC\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":null,\"age\":25},\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<— —这是百度最后的背水一战，但我并不乐观2018年的某一天，当我想找个地方周末去玩一下时，我打开了微信，点开了专做宁波同城游的一个公众号，很轻松的报名参团。那一瞬间，我突然明白了很多，我以前没有想清楚的问题百度这些年，为什么一意孤行的越来越封闭，为什么顶着大家的一片骂名，被从里到外的批评也毫无改观。为什么换了那么多高管，硅谷大牛，新锐青年，也没有解决问题为什么喊了那么多的狼性，野性，兽性，都没有挽回颓势为什么百度不好好的做搜索引擎？这么一个简单的问题背后非不为也，而是不能也百度的信息之海，已经从根本上枯竭了，百度没有能力，也没有机会去抓取到优质的信息了。最简单的例子，当你第一次搜索到淘宝，美团，去哪儿，当你知道这里拥有你想要的一切，你便再没有动机，跑去百度上搜索，哪里衣服好看，哪家店好吃了电商，美食，旅游这些商业化程度最高，搜索最频繁，最容易赚钱的领域，早早的都脱离百度自成一体了。其次，这些年来，大家对搜索引擎的商业模式，都存在一个误判人们常常称百度为互联网时代，信息高速公路上的收费站，搜索引擎坐在那里就能收钱但实际上，现实中的收费站你去要交钱，回来也要交钱，日复一日的每天路过都能收你的钱而百度，实际上往往只能收一次：所谓信息，只有当你第一次接触到时，才是信息后面，可能就会成为日常的服务了，这些频繁的服务，是可以支撑用户绕过你百度的收费站的。大量的信息在新时代变成了服务。百度将自己的未来押在了AI赛道，他认为未来需要强有力的AI来处理海量的信息。>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"— —这是百度最后的背水一战，但我并不乐观2018年的某一天，当我想找个地方周末去玩一下时，我打开了微信，点开了专做宁波同城游的一个公众号，很轻松的报名参团。那一瞬间，我突然明白了很多，我以前没有想清楚的问题百度这些年，为什么一意孤行的越来越封闭，为什么顶着大家的一片骂名，被从里到外的批评也毫无改观。为什么换了那么多高管，硅谷大牛，新锐青年，也没有解决问题为什么喊了那么多的狼性，野性，兽性，都没有挽回颓势为什么百度不好好的做搜索引擎？这么一个简单的问题背后非不为也，而是不能也百度的信息之海，已经从根本上枯竭了，百度没有能力，也没有机会去抓取到优质的信息了。最简单的例子，当你第一次搜索到淘宝，美团，去哪儿，当你知道这里拥有你想要的一切，你便再没有动机，跑去百度上搜索，哪里衣服好看，哪家店好吃了电商，美食，旅游这些商业化程度最高，搜索最频繁，最容易赚钱的领域，早早的都脱离百度自成一体了。其次，这些年来，大家对搜索引擎的商业模式，都存在一个误判人们常常称百度为互联网时代，信息高速公路上的收费站，搜索引擎坐在那里就能收钱但实际上，现实中的收费站你去要交钱，回来也要交钱，日复一日的每天路过都能收你的钱而百度，实际上往往只能收一次：所谓信息，只有当你第一次接触到时，才是信息后面，可能就会成为日常的服务了，这些频繁的服务，是可以支撑用户绕过你百度的收费站的。大量的信息在新时代变成了服务。百度将自己的未来押在了AI赛道，他认为未来需要强有力的AI来处理海量的信息。\",\"compiletype\":1},\"is_anonymous\":false,\"upvoteCount\":0,\"downvoteCount\":0,\"attentionCount\":0,\"evaluateStatus\":3,\"attention\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
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
                .andExpect(partialContent("{\"content\":{\"id\":91,\"title\":\"This is a new article Title\",\"company\":{\"id\":1,\"name\":\"中国石油化工股份有限公司\",\"avatarUrl\":\"http://youthchina-static-file.oss-us-west-1.aliyuncs.com/2858461058387939328?Expires=1555182706&OSSAccessKeyId=LTAId2jIl3ln6iIe&Signature=%2FMmba6SRKwexpEieJg5BEzy3Cro%3D\",\"location\":\"110000\",\"website\":\"http://www.sinopec.com\",\"note\":\"中国石油化工股份有限公司是一家上中下游一体化、石油石化主业突出、拥有比较完备销售网络、境内外上市的股份制企业。中国石化是由中国石油化工集团公司依据《中华人民共和国公司法... \",\"nation\":\"中国\",\"photoUrlList\":[\"http://youthchina-static-file.oss-us-west-1.aliyuncs.com/2856327168068161536?Expires=1555182706&OSSAccessKeyId=LTAId2jIl3ln6iIe&Signature=ryxKAFqAOahtUue9j5mK44S%2FKIQ%3D\",\"http://youthchina-static-file.oss-us-west-1.aliyuncs.com/2856327168068161536?Expires=1555182706&OSSAccessKeyId=LTAId2jIl3ln6iIe&Signature=ryxKAFqAOahtUue9j5mK44S%2FKIQ%3D\"],\"jobCount\":0,\"collected\":false},\"create_at\":\"2019-04-13T18:11:46.235+0000\",\"modified_at\":\"2019-04-13T18:11:46.235+0000\",\"author\":{\"id\":1,\"username\":\"YihaoGuo\",\"email\":\"test@test.com\",\"phonenumber\":\"2022922222\",\"register_date\":null,\"first_name\":\"John\",\"last_name\":\"Doe\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":[\"APPLICANT\"],\"age\":0},\"body\":{\"braftEditorRaw\":\"wqewqewqe\",\"previewText\":\"pre\",\"compiletype\":1},\"is_anonymous\":false,\"upvoteCount\":null,\"downvoteCount\":null,\"attentionCount\":null,\"evaluateStatus\":null,\"attention\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}","$.content.create_at","$.content.id","$.content.company.avatarUrl","$.content.company.photoUrlList","$.content.modified_at"));
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
                .andExpect(partialContent("{\"content\":{\"id\":1,\"title\":\"This is a new Title 1\",\"company\":{\"id\":1,\"name\":\"中国石油化工股份有限公司\",\"avatarUrl\":\"http://youthchina-static-file.oss-us-west-1.aliyuncs.com/2858461058387939328?Expires=1555187531&OSSAccessKeyId=LTAId2jIl3ln6iIe&Signature=MNtDTNaVpYpc33APop7nRsBeJao%3D\",\"location\":\"110000\",\"website\":\"http://www.sinopec.com\",\"note\":\"中国石油化工股份有限公司是一家上中下游一体化、石油石化主业突出、拥有比较完备销售网络、境内外上市的股份制企业。中国石化是由中国石油化工集团公司依据《中华人民共和国公司法... \",\"nation\":\"中国\",\"photoUrlList\":[\"http://youthchina-static-file.oss-us-west-1.aliyuncs.com/2856327168068161536?Expires=1555187531&OSSAccessKeyId=LTAId2jIl3ln6iIe&Signature=%2BtsP0WmZ%2FBmyAPDqkCPz%2BM0TbXU%3D\",\"http://youthchina-static-file.oss-us-west-1.aliyuncs.com/2856327168068161536?Expires=1555187531&OSSAccessKeyId=LTAId2jIl3ln6iIe&Signature=%2BtsP0WmZ%2FBmyAPDqkCPz%2BM0TbXU%3D\"],\"jobCount\":0,\"collected\":false},\"create_at\":null,\"modified_at\":\"2019-04-13T19:32:11.323+0000\",\"author\":{\"id\":1,\"username\":\"YihaoGuo\",\"email\":\"test@test.com\",\"phonenumber\":\"2022922222\",\"register_date\":null,\"first_name\":\"John\",\"last_name\":\"Doe\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":[\"APPLICANT\"],\"age\":0},\"body\":{\"braftEditorRaw\":\"werr\",\"previewText\":\"pre\",\"compiletype\":1},\"is_anonymous\":false,\"upvoteCount\":null,\"downvoteCount\":null,\"attentionCount\":null,\"evaluateStatus\":null,\"attention\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}","$.content.id","$.content.company.avatarUrl","$.content.company.photoUrlList","$.content.modified_at"));

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
              .andExpect(partialContent("{\"content\":{\"id\":93,\"creator\":{\"id\":1,\"username\":\"YihaoGuo\",\"email\":\"test@test.com\",\"phonenumber\":\"2022922222\",\"register_date\":null,\"first_name\":\"John\",\"last_name\":\"Doe\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":[\"APPLICANT\"],\"age\":0},\"body\":\"何老师你要是被绑架了就扎扎眼！\",\"create_at\":\"2019-04-13T19:35:34.726+0000\",\"is_anonymous\":false,\"modified_at\":\"2019-04-13T19:35:34.726+0000\",\"upvoteCount\":null,\"downvoteCount\":null,\"evaluateStatus\":null},\"status\":{\"code\":201,\"reason\":\"success\"}}","$.content.id","$.content.modified_at","$.content.create_at"));
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
                .andExpect(content().json("{\"content\":{\"offset\":0,\"limit\":2147483646,\"data\":[],\"page_count\":0,\"item_count\":0,\"page_index\":0,\"is_first\":true,\"is_last\":false},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void countEssay() {

    }
}
