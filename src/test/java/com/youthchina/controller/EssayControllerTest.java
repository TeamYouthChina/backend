package com.youthchina.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.dto.community.article.EssayRequestDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by hongshengzhang on 2/17/19.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:New_Community_test.xml"})
@DatabaseSetup({"classpath:New_Company_test.xml"})
@DatabaseSetup({"classpath:sys.xml"})
@WebAppConfiguration
public class EssayControllerTest {

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
                get(this.urlPrefix + "/articles/2")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":2,\"title\":\"为什么富人会越富，穷人还是穷人？\",\"company\":null,\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"modified_at\":\"2019-01-01T00:00:00.000+0000\",\"author\":{\"id\":2,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"firstName\":\"DDD\",\"lastName\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":null,\"age\":28},\"body\":{\"braftEditorRaw\":{\"resourceIdList\":[],\"braftEditorRaw\":{\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"<依据我身边的经验，在二三线城市的年轻人，月薪基本会稳定在四千左右。我是山东的，山东这边拿得上台面的城市，就举青岛吧，认识的一个做房产销售的姑娘，从济南打拼到青岛，天天加班，工资在五六千左右。一个朋友做摄影的工作室，作为领头人，工资也才拿四千多。而在发达一些的地方，同等资质的年轻人，月薪大概会翻番。我一个朋友跟我讲她的小闺蜜，在深圳做文员，连ppt也做不好的主儿，在深圳拿六千。而题主你在知乎见到的月薪过万的，大多数是有点儿本事的那种。我一个学妹就这种人，有点儿本事，上大学的时候就很活跃，毕业去了北京做金融，月薪好像刚去就过万了。她的朋友圈和知乎里那些大神一样，天天就是配个苹果在咖啡厅里在办公室里写表格，要不然就是新马泰旅游，还养了只拉布拉多。去年人家还结婚了，在北京，据说是男友炒股挣了钱。所以我觉得月薪过万不算普遍。第一月薪过万，得满足你有本事或者踩了狗屎，或者有本事到随便踩狗屎。会钻营，会取巧，会吃苦，会进取，哪一样干到极致，月薪过万都不是梦。有能耐，到哪儿都吃香。而我们很大一部分人，既没有本事，也没把握好环境。有的人北京待好几年工资都上不了五位数，有的人就能。这没法说，成功的途径很多，但哪一条路都不会特别好走。第二是行业的问题。恕我见识少，我是没见过技术要求低的岗位上出现过月薪过万的人。平时打个表格的小职员，公司呼来喝去的，哪能过万啊。你看那些发帖的，做销售的，干工程的，自主创业的，哪有个街道清洁的的出来说自己月薪过万。而从目前的环境看，技术要求低的岗位，仍然是主流啊，毕竟是基层。基层待遇好了，谁还想着往上爬啊是吧。第三就是某些答主金玉其外败絮其中了。不少人有虚荣心，无可厚非，但很多人的虚荣心和本身的价值不匹配。你说你用的是苹果嘴上抹的是YSL挎的是香奈儿去的是游轮度假，家里摆的是顶配电脑外加所有主机约的是极品大长腿，可有多少是真正靠自己，不是靠着家庭背景和另一半得来的，有多少是在自己月薪过万的承受范围内得来的。有的人，父母给买套房觉得是自己的，另一半给买到购物车清空觉得是自己的，还着上万的花呗觉得是自己的。拜托，吃了上顿没下顿的主儿，自己心里没点儿数是怎么着。还有我觉得最重要的是，普遍这个词儿怎么定义。我是觉得，十个人里面，一个人月薪过万我就认为这事儿普遍了。但现在的情况是，十亿人里，有几十万人挣得多，在社交圈子里一晒，人们就觉得普遍。网络拉近了人们的距离，也拉低了人们的智商和下限。综上，我觉得不算普遍。月薪过万的人虽然很多，真的很多。但这世上，多的还是苟且着的普通人。以上都是一家之言，冒犯了诸位，请多包涵。数据对不对的，反正我这个没见过什么世面的人，对于身边的感触就是这样，错了也没法改咯。在知乎灌水一年，看惯了某些人的沾沾自喜，多少有些反胃。人与人之间的交往，本不该有这么多的优越感。大家开开心心地坐在苞谷地里一块儿聊聊天喝喝酒多好。非得坐在埃菲尔铁塔尖儿上冻屁股，我还跟你喝酒呢，去你的吧。>\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}},\"previewText\":\"<在此填入你的文字>\"},\"previewText\":\"依据我身边的经验，在二三线城市的年轻人，月薪基本会稳定在四千左右。我是山东的，山东这边拿得上台面的城市，就举青岛吧，认识的一个做房产销售的姑娘，从济南打拼到青岛，天天加班，工资在五六千左右。一个朋友做摄影的工作室，作为领头人，工资也才拿四千多。而在发达一些的地方，同等资质的年轻人，月薪大概会翻番。我一个朋友跟我讲她的小闺蜜，在深圳做文员，连ppt也做不好的主儿，在深圳拿六千。而题主你在知乎见到的月薪过万的，大多数是有点儿本事的那种。我一个学妹就这种人，有点儿本事，上大学的时候就很活跃，毕业去了北京做金融，月薪好像刚去就过万了。她的朋友圈和知乎里那些大神一样，天天就是配个苹果在咖啡厅里在办公室里写表格，要不然就是新马泰旅游，还养了只拉布拉多。去年人家还结婚了，在北京，据说是男友炒股挣了钱。所以我觉得月薪过万不算普遍。第一月薪过万，得满足你有本事或者踩了狗屎，或者有本事到随便踩狗屎。会钻营，会取巧，会吃苦，会进取，哪一样干到极致，月薪过万都不是梦。有能耐，到哪儿都吃香。而我们很大一部分人，既没有本事，也没把握好环境。有的人北京待好几年工资都上不了五位数，有的人就能。这没法说，成功的途径很多，但哪一条路都不会特别好走。第二是行业的问题。恕我见识少，我是没见过技术要求低的岗位上出现过月薪过万的人。平时打个表格的小职员，公司呼来喝去的，哪能过万啊。你看那些发帖的，做销售的，干工程的，自主创业的，哪有个街道清洁的的出来说自己月薪过万。而从目前的环境看，技术要求低的岗位，仍然是主流啊，毕竟是基层。基层待遇好了，谁还想着往上爬啊是吧。第三就是某些答主金玉其外败絮其中了。不少人有虚荣心，无可厚非，但很多人的虚荣心和本身的价值不匹配。你说你用的是苹果嘴上抹的是YSL挎的是香奈儿去的是游轮度假，家里摆的是顶配电脑外加所有主机约的是极品大长腿，可有多少是真正靠自己，不是靠着家庭背景和另一半得来的，有多少是在自己月薪过万的承受范围内得来的。有的人，父母给买套房觉得是自己的，另一半给买到购物车清空觉得是自己的，还着上万的花呗觉得是自己的。拜托，吃了上顿没下顿的主儿，自己心里没点儿数是怎么着。还有我觉得最重要的是，普遍这个词儿怎么定义。我是觉得，十个人里面，一个人月薪过万我就认为这事儿普遍了。但现在的情况是，十亿人里，有几十万人挣得多，在社交圈子里一晒，人们就觉得普遍。网络拉近了人们的距离，也拉低了人们的智商和下限。综上，我觉得不算普遍。月薪过万的人虽然很多，真的很多。但这世上，多的还是苟且着的普通人。以上都是一家之言，冒犯了诸位，请多包涵。数据对不对的，反正我这个没见过什么世面的人，对于身边的感触就是这样，错了也没法改咯。在知乎灌水一年，看惯了某些人的沾沾自喜，多少有些反胃。人与人之间的交往，本不该有这么多的优越感。大家开开心心地坐在苞谷地里一块儿聊聊天喝喝酒多好。非得坐在埃菲尔铁塔尖儿上冻屁股，我还跟你喝酒呢，去你的吧。\",\"compiletype\":1},\"is_anonymous\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }


    @Test
    public void addEssayTest() throws Exception {
        String json = "{\"braftEditorRaw\":{\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"<asdasd>\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}},\"previewText\":\"<在此填入你的文字>\",\"resourceIdList\":[]}";
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

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(requestEssayDTO);

        this.mvc.perform(
                post(this.urlPrefix + "/articles").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print());
    }

    @Test
    public void updateEssayTest() throws Exception {
        EssayRequestDTO requestEssayDTO = new EssayRequestDTO();
        requestEssayDTO.setTitle("This is a new Title 1");

        String json = "";
        String pre = "pre";
        RichTextRequestDTO richTextDTO = new RichTextRequestDTO();
        richTextDTO.setBraftEditorRaw(json);
        richTextDTO.setPreviewText(pre);
        richTextDTO.setCompiletype(1);

        requestEssayDTO.setBody(richTextDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(requestEssayDTO);

        this.mvc.perform(

                put(this.urlPrefix + "/articles/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print());
        //               .andExpect(content().json("{\"content\":{\"id\":1,\"title\":\"This is a new Title 1\",\"company\":null,\"create_at\":null,\"modified_at\":\"2019-04-03T13:47:18.437+0000\",\"author\":{\"id\":1,\"username\":\"YihaoGuo\",\"email\":\"test@test.com\",\"phonenumber\":\"2022922222\",\"register_date\":null,\"firstName\":\"John\",\"lastName\":\"Doe\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":[\"APPLICANT\"],\"age\":0},\"body\":{\"braftEditorRaw\":null,\"previewText\":\"pre\",\"compiletype\":1},\"is_anonymous\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));

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
        CommentRequestDTO commentRequestDTO = new CommentRequestDTO();
        commentRequestDTO.setIs_anonymous(false);

        String json = "";
        String pre = "pre";
        RichTextRequestDTO richTextDTO = new RichTextRequestDTO();
        richTextDTO.setBraftEditorRaw(json);
        richTextDTO.setPreviewText(pre);
        richTextDTO.setCompiletype(1);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(commentRequestDTO);

        this.mvc.perform(
                post(this.urlPrefix + "/articles/1/comments").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void updateAttention() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/articles/1/attention").contentType(MediaType.APPLICATION_JSON_UTF8)
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
                .andExpect(content().json("{\"content\":{\"comments\":[{\"id\":16,\"creator\":{\"id\":2,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"firstName\":\"DDD\",\"lastName\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":null,\"age\":28},\"body\":\"好好好\",\"create_at\":\"2019-02-12T00:00:00.000+0000\",\"is_anonymous\":true}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

    @Test
    public void countEssay(){
        System.out.println(communityMapper.countEssay());
    }

    @Test
    public void upvoteTest() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/articles/1/upvote").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void downvoteTest() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/articles/1/downvote").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }
}
