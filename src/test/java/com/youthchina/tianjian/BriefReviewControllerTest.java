package com.youthchina.tianjian;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/*
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:briefreview.xml", "classpath:users.xml", "classpath:comments.xml"})
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
                get(this.urlPrefix + "/editorials/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":1,\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"QQQEERTT\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":null,\"resourceIdList\":[]},\"comments\":{\"comments\":[{\"id\":1,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"qwe\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":null,\"resourceIdList\":[]},\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"is_anonymous\":false}]},\"author\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21}},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
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
        RichTextDTOResponse richTextDTO = new RichTextDTOResponse();
        //language=JSON
        String json = "{\n" +
                "  \"braftEditorRaw\":{\n" +
                "    \"blocks\": [\n" +
                "      {\n" +
                "        \"key\":\"dtj4a\",\n" +
                "        \"text\":\"update\",\n" +
                "        \"type\":\"unstyled\",\n" +
                "        \"depth\":0,\n" +
                "        \"inlineStyleRanges\": [],\n" +
                "        \"entityRanges\": [],\n" +
                "        \"data\":{\n" +
                "        }\n" +
                "      }\n" +
                "    ],\n" +
                "    \"entityMap\":{\n" +
                "    }\n" +
                "  },\n" +
                "  \"previewText\":null,\n" +
                "  \"resourceIdList\": []\n" +
                "}";
        try {
            richTextDTO = new ObjectMapper().readValue(json, RichTextDTOResponse.class);
            System.out.println(richTextDTO);
        } catch (IOException e) {
            Assert.fail();
        }

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
                .andExpect(content().json("{\"content\":{\"id\":1,\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"update\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":null,\"resourceIdList\":[]},\"comments\":{\"comments\":[]},\"author\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21}},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));


    }

    @Test
    public void addBriefReviewTest() throws Exception {
        BriefReviewRequestDTO requestBriefReviewDTO = new BriefReviewRequestDTO();
        RichTextDTOResponse richTextDTO = new RichTextDTOResponse();
        //language=JSON
        String json = "{\n" +
                "  \"braftEditorRaw\":{\n" +
                "    \"blocks\": [\n" +
                "      {\n" +
                "        \"key\":\"dtj4a\",\n" +
                "        \"text\":\"dsafsaf\",\n" +
                "        \"type\":\"unstyled\",\n" +
                "        \"depth\":0,\n" +
                "        \"inlineStyleRanges\": [],\n" +
                "        \"entityRanges\": [],\n" +
                "        \"data\":{\n" +
                "        }\n" +
                "      }\n" +
                "    ],\n" +
                "    \"entityMap\":{\n" +
                "    }\n" +
                "  },\n" +
                "  \"previewText\":null,\n" +
                "  \"resourceIdList\": []\n" +
                "}";
        try {
            richTextDTO = new ObjectMapper().readValue(json, RichTextDTOResponse.class);
            System.out.println(richTextDTO);
        } catch (IOException e) {
            Assert.fail();
        }

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
        CommentRequestDTO requestCommentDTO = new CommentRequestDTO();
        RichTextDTOResponse richTextDTO = new RichTextDTOResponse();
        //language=JSON
        String json = "{\n" +
                "  \"braftEditorRaw\":{\n" +
                "    \"blocks\": [\n" +
                "      {\n" +
                "        \"key\":\"dtj4a\",\n" +
                "        \"text\":\"qqqrrr\",\n" +
                "        \"type\":\"unstyled\",\n" +
                "        \"depth\":0,\n" +
                "        \"inlineStyleRanges\": [],\n" +
                "        \"entityRanges\": [],\n" +
                "        \"data\":{\n" +
                "        }\n" +
                "      }\n" +
                "    ],\n" +
                "    \"entityMap\":{\n" +
                "    }\n" +
                "  },\n" +
                "  \"previewText\":null,\n" +
                "  \"resourceIdList\": []\n" +
                "}";
        try {
            richTextDTO = new ObjectMapper().readValue(json, RichTextDTOResponse.class);
            System.out.println(richTextDTO);
        } catch (IOException e) {
            Assert.fail();
        }

        requestCommentDTO.setBody(richTextDTO);

        requestCommentDTO.setIs_anonymous(true);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String addJson = ow.writeValueAsString(requestCommentDTO);
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
                .andExpect(content().json("{\"content\":{\"comments\":[{\"id\":1,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"qwe\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":null,\"resourceIdList\":[]},\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"is_anonymous\":false}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));

    }
} */