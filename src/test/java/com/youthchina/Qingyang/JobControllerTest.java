package com.youthchina.Qingyang;

/**
 * Created by zhongyangwu on 4/4/19.
 */

/*


@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:New_Company_test.xml", "classpath:New_Dictionary_test.xml", "classpath:New_SYS_test.xml"})
@WebAppConfiguration
public class JobControllerTest {
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
    public void testSearchJob() throws Exception {
        this.mvc.perform(
                post(this.urlPrefix + "/jobs/search?page=1&limit=10")
                        .content("{\n" +
                                "  \"industry\": [\n" +
                                "    \"test\",\n" +
                                "    \"test\"\n" +
                                "  ]\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())

        )
                .andDo(print())
        ;
    }

}

 */