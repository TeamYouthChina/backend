package com.youthchina.Qingyang;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-27
 **/

/*
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:New_SYS_test.xml"})
@WebAppConfiguration
public class MeControllerTest {

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
    public void testGetMe() throws Exception {
        Integer id = 1;
        this.mvc.perform(
                get(this.urlPrefix + "/me/")//.param("id", "1").param("detailLevel", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
        //.andExpect(content().json("{\"content\":{\"id\":" +id+ ",\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"status\":{\"code\":2000,\"reason\":\"\"}}",false))
        ;
    }
} */

