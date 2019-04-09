package com.youthchina.zhongyang;

/**
 * Created by zhongyangwu on 1/2/19.
 */
/*
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup(value = {"classpath:sys.xml"}, type = DatabaseOperation.CLEAN_INSERT)
@WebAppConfiguration
public class UserControllerTest {
    @Autowired
    WebApplicationContext context;

    @Value("${web.url.prefix}")
    private String urlPrefix;

    MockMvc mvc;

    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    JwtService jwtService;
    private AuthGenerator authGenerator = new AuthGenerator();

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void testLogin() throws Exception {
        this.mvc.perform(post(this.urlPrefix + "/login").contentType(MediaType.APPLICATION_JSON_UTF8).content("{\n" +
                "  \"id\": 2,\n" +
                "  \"password\": \"123456\"\n" +
                "}"))
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":2,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"DDD\",\"last_name\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":28},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
                .andExpect(header().exists("X-AUTHENTICATION"));
    }

    @Test
    public void testRegister() throws Exception {
        this.mvc.perform(post(this.urlPrefix + "/applicants/register")
                .content("{\n" +
                        "  \"username\": \"testUser\",\n" +
                        "  \"date_of_birth\": \"1995-11-01\",\n" +
                        "  \"password\": \"123456\",\n" +
                        "  \"phone_number\": \"12315213\",\n" +
                        "  \"email\": \"testNew!@test.com\",\n" +
                        "  \"nation\": \"China\",\n" +
                        "  \"gender\": \"male\",\n" +
                        "  \"age\": 20\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(jsonPath("$.content.username").value("testUser"))
                .andExpect(jsonPath("$.content.email").value("testNew!@test.com"))
                .andExpect(jsonPath("$.content.phonenumber").value("12315213"))
        ;

        this.mvc.perform(post(this.urlPrefix + "/applicants/register")
                .content("{\n" +
                        "  \"username\": \"testUser\",\n" +
                        "  \"date_of_birth\": \"1995-11-01\",\n" +
                        "  \"password\": \"123456\",\n" +
                        "  \"phone_number\": \"12315213\",\n" +
                        "  \"email\": \"testNew!@test.com\",\n" +
                        "  \"nation\": \"China\",\n" +
                        "  \"gender\": \"male\",\n" +
                        "  \"age\": 20\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(content().json("{\"content\":null,\"status\":{\"code\":4000,\"reason\":\"cannot register because there are already user registered with same email or username\"}}"));
    }

}
*/