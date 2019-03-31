package com.youthchina.tianjian;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dto.community.briefreview.BriefReviewRequestDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.util.RichTextRequestDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:New_Community_test.xml"})
@DatabaseSetup({"classpath:sys.xml"})
@WebAppConfiguration
public class BriefReviewControllerTest {
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
    public void getBriefReviewTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/editorials/5")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":5,\"body\":{\"braftEditorRaw\":{\"resourceIdList\":[],\"braftEditorRaw\":{\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"<有感于腾讯公司的发家史，总觉得腾讯背后有某种强大的力量，能靠微创新（也可称山寨）能发展到现如今的体量也算是世界奇观。靠模仿icq完成了资本的原始积累并实现了滚雪球，可以这么说腾讯的今天是一切都建立在oicq（qq）之上的，从qq堂，qq飞车，qq劲舞，腾讯的发家史就是一个复制粘贴史。。。并且发展到如今规模，企业文化还是坚强的延续下来，复制粘贴的企业文化从高层到底层，已深入骨髓，从领子烂到里子。。对创新型企业来说，腾讯如一颗毒瘤存在，注定不会得到大家尊重。>\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}},\"previewText\":\"<在此填入你的文字>\"},\"previewText\":\"有感于腾讯公司的发家史，总觉得腾讯背后有某种强大的力量，能靠微创新（也可称山寨）能发展到现如今的体量也算是世界奇观。靠模仿icq完成了资本的原始积累并实现了滚雪球，可以这么说腾讯的今天是一切都建立在oicq（qq）之上的，从qq堂，qq飞车，qq劲舞，腾讯的发家史就是一个复制粘贴史。。。并且发展到如今规模，企业文化还是坚强的延续下来，复制粘贴的企业文化从高层到底层，已深入骨髓，从领子烂到里子。。对创新型企业来说，腾讯如一颗毒瘤存在，注定不会得到大家尊重。\",\"compiletype\":1},\"comments\":{\"comments\":[{\"id\":5,\"creator\":{\"id\":6,\"username\":\"GHI\",\"email\":\"123456@789.com\",\"phonenumber\":\"1112223334445\",\"register_date\":\"2019-01-01 00:00:00.0\",\"firstName\":\"GGG\",\"lastName\":\"GGGHHHIII\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":null,\"age\":26},\"body\":\"短评评论5\",\"create_at\":\"2019-02-13T00:00:00.000+0000\",\"is_anonymous\":true}]},\"author\":{\"id\":5,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"firstName\":\"DDD\",\"lastName\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":null,\"age\":28}},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
        // change register_date, real_name
    }

    @Test
    public void deleteBriefReviewTest() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/editorials/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));

    }

    @Test
    public void updateBriefReviewTest() throws Exception {
        BriefReviewRequestDTO requestBriefReviewDTO = new BriefReviewRequestDTO();
        String json = "";
        String pre = "pre";
        RichTextRequestDTO richTextDTO = new RichTextRequestDTO();
        richTextDTO.setBraftEditorRaw(json);
        richTextDTO.setPreviewText(pre);
        richTextDTO.setCompiletype(1);
        requestBriefReviewDTO.setBody(richTextDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String addJson = ow.writeValueAsString(requestBriefReviewDTO);
        this.mvc.perform(
                put(this.urlPrefix + "/editorials/1")
                        .content(addJson)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":1,\"body\":{\"braftEditorRaw\":{\"resourceIdList\":[],\"braftEditorRaw\":{\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"<东哥拿你当兄弟，你问东哥要工资？不就是受点委屈嘛，既然做了东哥兄弟，就要体谅东哥的难处，有啥委屈自己忍着，别一天天叨逼叨没完没了的，抹黑公司形象，损害东哥人设，试问你这么不讲义气，以后东哥还怎么拿你当兄弟？———————————更新———————————没想到随便吐个槽能混这么多赞，看来不认真说几句都过意不去了。首先，目前真相未明，作为旁观者不能轻易站队，很多答案提到了，东哥说快递小哥月收入是1万朝上，而快递小哥说自己是8000元，就算认为东哥说的是税前收入，小哥说的是税后收入，数据还是对不上，究竟是一方在撒谎，还是双方都没撒谎，但东哥因为远离一线，拿到了不实数据，暂时无法求证，只能静待下文。第二，从公关的角度，东哥亲自下场手撕一个快递小哥，是非常掉段的行为。我反正没法想象AT的二马作为老总，能和一线程序员互怼，不管对错，都该交给下面的专人处理，不该由老总上场，否则就有恃强凌弱之嫌，毕竟老总的社会资源太强大了，对员工很不公平，而且舆论一旦发酵，对公司名声和老总人设都是得不偿失，有害无益。第三，东哥这次在知乎被怼成这样，是求仁得仁，大家积怨已久，早就想怼他，只是缺个机会罢了，而不能怨什么水军。平时动辄兄弟，说自己如何如何关爱员工，树了个好大哥的人设，如今一涉及利益立刻凶相毕露，大家骂你一句伪君子有啥不对？当初你diss某云，讽刺同行，高调宣扬自己仗义疏财的时候，可不是今天这样说的吧？>\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}},\"previewText\":\"<在此填入你的文字>\"},\"previewText\":\"东哥拿你当兄弟，你问东哥要工资？不就是受点委屈嘛，既然做了东哥兄弟，就要体谅东哥的难处，有啥委屈自己忍着，别一天天叨逼叨没完没了的，抹黑公司形象，损害东哥人设，试问你这么不讲义气，以后东哥还怎么拿你当兄弟？———————————更新———————————没想到随便吐个槽能混这么多赞，看来不认真说几句都过意不去了。首先，目前真相未明，作为旁观者不能轻易站队，很多答案提到了，东哥说快递小哥月收入是1万朝上，而快递小哥说自己是8000元，就算认为东哥说的是税前收入，小哥说的是税后收入，数据还是对不上，究竟是一方在撒谎，还是双方都没撒谎，但东哥因为远离一线，拿到了不实数据，暂时无法求证，只能静待下文。第二，从公关的角度，东哥亲自下场手撕一个快递小哥，是非常掉段的行为。我反正没法想象AT的二马作为老总，能和一线程序员互怼，不管对错，都该交给下面的专人处理，不该由老总上场，否则就有恃强凌弱之嫌，毕竟老总的社会资源太强大了，对员工很不公平，而且舆论一旦发酵，对公司名声和老总人设都是得不偿失，有害无益。第三，东哥这次在知乎被怼成这样，是求仁得仁，大家积怨已久，早就想怼他，只是缺个机会罢了，而不能怨什么水军。平时动辄兄弟，说自己如何如何关爱员工，树了个好大哥的人设，如今一涉及利益立刻凶相毕露，大家骂你一句伪君子有啥不对？当初你diss某云，讽刺同行，高调宣扬自己仗义疏财的时候，可不是今天这样说的吧？\",\"compiletype\":1},\"comments\":{\"comments\":[{\"id\":1,\"creator\":{\"id\":2,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"firstName\":\"DDD\",\"lastName\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":null,\"age\":28},\"body\":\"短评评论1\",\"create_at\":\"2018-02-03T00:00:00.000+0000\",\"is_anonymous\":false}]},\"author\":{\"id\":3,\"username\":\"GHI\",\"email\":\"123456@789.com\",\"phonenumber\":\"1112223334445\",\"register_date\":\"2019-01-01 00:00:00.0\",\"firstName\":\"GGG\",\"lastName\":\"GGGHHHIII\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":null,\"age\":26}},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));


    }

    @Test
    public void addBriefReviewTest() throws Exception {
        BriefReviewRequestDTO requestBriefReviewDTO = new BriefReviewRequestDTO();
        String json = "";
        String pre = "pre";
        RichTextRequestDTO richTextDTO = new RichTextRequestDTO();
        richTextDTO.setBraftEditorRaw(json);
        richTextDTO.setPreviewText(pre);
        richTextDTO.setCompiletype(1);

        requestBriefReviewDTO.setBody(richTextDTO);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String addJson = ow.writeValueAsString(requestBriefReviewDTO);
        this.mvc.perform(
                post(this.urlPrefix + "/editorials")
                        .content(addJson)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())

        )
                .andDo(print());
    }

    @Test
    public void addBriefReviewCommentsTest() throws Exception {
        CommentRequestDTO commentRequestDTO = new CommentRequestDTO();
        String json = "comment";
        commentRequestDTO.setBody(json);

        commentRequestDTO.setIs_anonymous(true);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String addJson = ow.writeValueAsString(commentRequestDTO);
        this.mvc.perform(
                post(this.urlPrefix + "/editorials/1/comments")
                        .content(addJson)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void addBriefReviewUpvoteTest() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/editorials/1/upvote")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":200,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));

    }

    @Test
    public void getBriefReviewCommentsTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/editorials/1/comments")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"comments\":[{\"id\":1,\"creator\":{\"id\":2,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"firstName\":\"DDD\",\"lastName\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":null,\"age\":28},\"body\":\"短评评论1\",\"create_at\":\"2018-02-03T00:00:00.000+0000\",\"is_anonymous\":false}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));

    }
}