package com.youthchina.tianjian;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
/*
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:answers.xml", "classpath:comments.xml", "classpath:users.xml"})
@WebAppConfiguration
public class AnswerControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Value("${web.url.prefix}")
    private String urlPrefix;

    private AuthGenerator authGenerator = new AuthGenerator();

    MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    public void testGetAnswer() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/answers/1").param("id", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print());
        // .andExpect(content().json("{\"content\":{\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"这是第一个回答\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":null,\"resourceIdList\":[]},\"is_anonymous\":false,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},\"modified_at\":\"2018-12-04 13:32:40.0\",\"create_at\":\"2018-12-04 13:32:40.0\",\"question\":{\"id\":1,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},\"title\":\"第一个问题\",\"is_anonymous\":true,\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"rela_type\":1,\"rela_id\":3,\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"Body of the question 1 but42\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"Abbreviation of the question 1 but 42\",\"resourceIdList\":[]}},\"id\":1},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));

    }

    @Test
    public void testUpdateAnswer() throws Exception {
        SimpleAnswerRequestDTO simpleAnswerDTO = new SimpleAnswerRequestDTO();
        simpleAnswerDTO.setIs_anonymous(true);
        RichTextDTOResponse richTextDTO = new RichTextDTOResponse();
        //language=JSON
        String json = "{\n" +
                "  \"braftEditorRaw\":{\n" +
                "    \"blocks\": [\n" +
                "      {\n" +
                "        \"key\":\"dtj4a\",\n" +
                "        \"text\":\"qweertyuiop\",\n" +
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

        simpleAnswerDTO.setBody(richTextDTO);


        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String searchJson = ow.writeValueAsString(simpleAnswerDTO);
        this.mvc.perform(
                put(this.urlPrefix + "/answers/1")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(searchJson)
        )
                .andDo(print());
    }

    @Test
    public void testDeleteAnswer() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/answers/1")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print());
    }

    @Test
    public void testAddAnswerComment() throws Exception {
        SimpleAnswerRequestDTO simpleAnswerDTO = new SimpleAnswerRequestDTO();
        RichTextDTOResponse richTextDTO = new RichTextDTOResponse();
        //language=JSON
        String json = "{\n" +
                "  \"braftEditorRaw\":{\n" +
                "    \"blocks\": [\n" +
                "      {\n" +
                "        \"key\":\"dtj4a\",\n" +
                "        \"text\":\"yes 2/24/2019\",\n" +
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

        simpleAnswerDTO.setBody(richTextDTO);
        simpleAnswerDTO.setIs_anonymous(false);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String addJson = ow.writeValueAsString(simpleAnswerDTO);

        this.mvc.perform(
                post(this.urlPrefix + "/answers/1/comments")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(addJson)
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void testAddUpvote() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/answers/1/upvote")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void testGetAllComments() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/answers/1/comments")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"comments\":[{\"id\":1,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"good answer1\",\"resourceIdList\":[]},\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"is_anonymous\":false},{\"id\":3,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"good answer3\",\"resourceIdList\":[]},\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"is_anonymous\":false},{\"id\":5,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"good answer5\",\"resourceIdList\":[]},\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"is_anonymous\":false}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

}*/
