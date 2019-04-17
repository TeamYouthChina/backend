package com.youthchina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.dto.community.question.QuestionRequestDTO;
import com.youthchina.dto.util.RichTextRequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static com.youthchina.util.CustomMockMvcMatchers.partialContent;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hongshengzhang on 2/10/19.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class QuestionControllerTest extends BaseControllerTest {

    @Test
    public void getAnswersTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/questions/2/answers")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json(readJson("responses/get-questions-answers.json"), false));
    }

    @Test
    public void searchQuestionsTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/questions").param("Company", "百度").param("Job", "")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json(readJson("responses/get-questions.json"), false));
    }

    @Test
    public void getQuestionTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/questions/1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json(readJson("responses/get-questions-1.json"), false));
    }

    @Test
    public void addQuestionTest() throws Exception {
        QuestionRequestDTO questionRequestDTO = new QuestionRequestDTO();
        questionRequestDTO.setTitle("Question No.100");
        String json = "";
        String pre = "pre";
        RichTextRequestDTO richTextDTO = new RichTextRequestDTO();
        richTextDTO.setBraftEditorRaw(json);
        richTextDTO.setPreviewText(pre);
        richTextDTO.setCompiletype(1);
        questionRequestDTO.setBody(richTextDTO);
        //questionDTO.setAbbreviation("Abbreviation of the question No.100");
        questionRequestDTO.setRela_type(2);
        questionRequestDTO.setRela_id(2);
        questionRequestDTO.setIs_anonymous(true);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(questionRequestDTO);
        this.mvc.perform(
                post(this.urlPrefix + "/questions").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print());
    }

    @Test
    public void updateQuestionTest() throws Exception {
        QuestionRequestDTO questionRequestDTO = new QuestionRequestDTO();
        questionRequestDTO.setTitle("How to learn JAVA");
        String json = "";
        String pre = "pre";
        RichTextRequestDTO richTextDTO = new RichTextRequestDTO();
        richTextDTO.setBraftEditorRaw(json);
        richTextDTO.setPreviewText(pre);
        richTextDTO.setCompiletype(1);
        questionRequestDTO.setBody(richTextDTO);
        questionRequestDTO.setIs_anonymous(true);
        //questionDTO.setAbbreviation("Abbreviation of the question No.100");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(questionRequestDTO);

        this.mvc.perform(
                put(this.urlPrefix + "/questions/2").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print());
//                .andExpect(content().json("{\"content\":{\"searchResult\":[{\"jobId\":1,\"jobName\":\"front\",\"jobProfCode\":\"A\",\"jobStartTime\":\"2019-01-01\",\"jobEndTime\":\"2020-01-01\",\"jobType\":1,\"jobDescription\":\"996\",\"jobDuty\":\"front\",\"jobHighlight\":\"50K\",\"jobSalaryFloor\":5000,\"jobSalaryCap\":6000,\"jobLink\":\"job.com\",\"cvReceiMail\":\"youth@china\",\"cvNameRule\":\"nameRule\",\"jobActive\":1,\"jobLocationList\":[{\"region_num\":1,\"region_chn\":\"北京\",\"region_eng\":\"Beijing\",\"region_level\":1,\"region_parent_num\":1,\"start_time\":\"2019-01-01T11:11:22.000+0000\",\"is_delete\":null,\"is_delete_time\":null,\"jobId\":1}],\"jobReqList\":[{\"degreeNum\":1,\"degreeChn\":\"本科\",\"degreeEng\":\"Bachelor\",\"startDate\":\"2019-01-01T11:11:22.000+0000\",\"jobId\":1},{\"degreeNum\":2,\"degreeChn\":\"硕士\",\"degreeEng\":\"Master\",\"startDate\":\"2019-01-02T11:11:22.000+0000\",\"jobId\":1}],\"industries\":[{\"indNum\":1,\"indCode\":\"A\",\"indChn\":\"工\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"A3\",\"startTime\":\"2018-10-11T11:11:22.000+0000\",\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":null,\"jobId\":1},{\"indNum\":2,\"indCode\":\"B\",\"indChn\":\"农\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"B3\",\"startTime\":\"2018-10-11T11:11:22.000+0000\",\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":null,\"jobId\":1}],\"profession\":{\"profNum\":1,\"profCode\":\"A\",\"profParentCode\":\"A\",\"profChn\":\"前端\",\"profEng\":\"frontEnd\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"isDelete\":null,\"isDeleteTime\":null,\"company\":{\"companyId\":1,\"companyName\":\"大疆\",\"companyCode\":\"2\",\"companyIntroduc\":\"无人机\",\"companyNature\":{\"natureNum\":1,\"natureChn\":\"国企\",\"natureEng\":\"public\",\"natureDetail\":\"good\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"companyScale\":{\"scaleNum\":1,\"scaleChn\":\"大\",\"scaleEng\":\"big\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"location\":{\"region_num\":1,\"region_chn\":\"北京\",\"region_eng\":\"Beijing\",\"region_level\":1,\"region_parent_num\":1,\"start_time\":\"2019-01-01T11:11:22.000+0000\",\"is_delete\":null,\"is_delete_time\":null,\"jobId\":null},\"country\":null,\"companyMail\":\"dji@com\",\"companyWebsite\":\"dji.com\",\"companyStartDate\":\"2005-11-20\",\"companyLogo\":\"1\",\"companyVerify\":1,\"userId\":null,\"isDelete\":null,\"isDeleteTime\":null,\"jobs\":null,\"indList\":[{\"indNum\":1,\"indCode\":\"A\",\"indChn\":\"工\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"A3\",\"startTime\":null,\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":1,\"jobId\":null},{\"indNum\":2,\"indCode\":\"B\",\"indChn\":\"农\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"B3\",\"startTime\":null,\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":1,\"jobId\":null}],\"verificationList\":[]},\"hr\":{\"hrId\":1,\"companyId\":1,\"hrOnJob\":1,\"userId\":null,\"isDelete\":null,\"isDeleteTime\":null},\"id\":1}],\"status\":null},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
        this.mvc.perform(
                get(this.urlPrefix + "/questions/2").param("Id", "2")
                        .with(authGenerator.authentication())

        )
                .andDo(print());
        //.andExpect(content().json("{\"content\":{\"id\":2,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":null,\"phonenumber\":\"18463722634\",\"registerDate\":null,\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":null,\"age\":21},\"title\":\"How to learn JAVA\",\"body\":\"I don't know\",\"createAt\":\"2018-12-05T13:32:40.000+0000\",\"editAt\":\"2019-02-14T16:50:27.000+0000\",\"answers\":null,\"invitation\":null,\"labelIds\":null,\"rela_type\":3,\"rela_id\":null,\"abbreviation\":\"Abbreviation of the question No.100\",\"anonymous\":null},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));

    }

    //    @Test
//    public void invitesAnswerTest() throws Exception {
//        List<Integer> userlist = new ArrayList<>();
//        userlist.add(1);
//        userlist.add(2);
//        ObjectMapper mapper = new ObjectMapper();
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        java.lang.String requestJson = ow.writeValueAsString(userlist);
//        System.out.println(requestJson);
//        this.mvc.perform(
//                put(this.urlPrefix + "/questions/2/invite").contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(requestJson)
//                        .with(authGenerator.authentication())
//        )
//                .andDo(print());
//    }
//
//    @Test
//    public void inviteAnswerTest() throws Exception {
//        this.mvc.perform(
//                put(this.urlPrefix + "/questions/2/invite/1")
//                        .with(authGenerator.authentication())
//        )
//                .andDo(print());
//    }
//
    @Test
    public void attentionTest() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/questions/2/attention")
                        .with(authGenerator.authentication())
        )
                .andDo(print());
    }

    @Test
    public void cancelAttention() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/questions/attention/2")
                        .with(authGenerator.authentication())
        )
                .andDo(print());
    }

    @Test
    public void deleteQuestionTest() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/questions/4")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"delete success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
        this.mvc.perform(
                get(this.urlPrefix + "/questions/4")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":null,\"status\":{\"code\":4040,\"reason\":\"没有找到这个问题\"}}", false));

    }

    @Test
    public void testAddAnswer() throws Exception {
        this.mvc.perform(
                post(this.urlPrefix + "/questions/2/answers")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(readJson("requests/post-answer.json"))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(partialContent("{\"content\":{\"body\":{\"braftEditorRaw\":\"adfd\",\"previewText\":\"pre\",\"compiletype\":1},\"is_anonymous\":false,\"creator\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"register_date\":1546300800000,\"first_name\":\"Admin\",\"last_name\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ROOT\"],\"age\":25,\"phone_number\":\"1234657890123\"},\"modified_at\":\"2019-04-17 12:40:33.0\",\"create_at\":\"2019-04-17 12:40:33.0\",\"question\":{\"id\":2,\"creator\":null,\"title\":\"腾讯的问题是什么？\",\"is_anonymous\":true,\"create_at\":1546300800000,\"modified_at\":1546300800000,\"rela_type\":1,\"rela_id\":37,\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<在软件行业，操作系统平台就是那个八，其他的应用软件就是那个二。微软已经踩到了一次狗屎运，得到了软件行业80%的利润，现在，他所需要做的，就是保持住这个地位。但技术不是静止不动的，不断有新的技术生长出来，在成千上万种技术中，有一种会长成参天大树，利润无比丰厚，取代原来的技术平台，成为新的主流趋势。>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"在软件行业，操作系统平台就是那个八，其他的应用软件就是那个二。微软已经踩到了一次狗屎运，得到了软件行业80%的利润，现在，他所需要做的，就是保持住这个地位。但技术不是静止不动的，不断有新的技术生长出来，在成千上万种技术中，有一种会长成参天大树，利润无比丰厚，取代原来的技术平台，成为新的主流趋势。\",\"compiletype\":1}},\"id\":54,\"upvoteCount\":0,\"downvoteCount\":0,\"attentionCount\":0,\"evaluateStatus\":3,\"attention\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}", "$.content.modified_at", "$.content.create_at", "$.content.id"));
    }

//    @Test
//    public void testUserAttentions() throws Exception {
//        this.mvc.perform(
//                get
//                        (this.urlPrefix + "/users/1/attentions").param("type", "Question")
//
//                        .with(authGenerator.authentication())
//        )
//                .andDo(print())
//        ;
//    }
}
