package com.youthchina.Hongsheng;

/**
 * Created by hongshengzhang on 2/26/19.
 */

/*



@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:recommendation.xml"})
@WebAppConfiguration
public class UserRecommendationControllerTest {
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
    public void getRecommendUserTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/users")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"users\":[{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},{\"id\":2,\"username\":\"zhid d\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},{\"id\":3,\"username\":\"zhid d\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},{\"id\":4,\"username\":\"zhid d\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},{\"id\":5,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},{\"id\":6,\"username\":\"zhid d\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},{\"id\":7,\"username\":\"zhid d\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},{\"id\":8,\"username\":\"zhid d\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},{\"id\":9,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},{\"id\":10,\"username\":\"zhid d\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }
}
 */