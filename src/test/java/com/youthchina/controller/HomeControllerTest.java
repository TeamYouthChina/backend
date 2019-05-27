package com.youthchina.controller;

/*
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:recommendation.xml"})
@WebAppConfiguration
public class HomeControllerTest {
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
    public void getNewJob() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/home/new")
        ).andDo(print())
                .andExpect(status().is2xxSuccessful())
        ;
    }

    @Test
    public void getHotJob() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/home/hot")
        ).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
*/