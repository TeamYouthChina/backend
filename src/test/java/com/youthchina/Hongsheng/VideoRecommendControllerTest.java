package com.youthchina.Hongsheng;

/*



@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:recommendation.xml"})
@DatabaseSetup({"classpath:videos.xml"})
@WebAppConfiguration
public class VideoRecommendControllerTest {
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
    public void getRecommendVideoTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/videos")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
 */