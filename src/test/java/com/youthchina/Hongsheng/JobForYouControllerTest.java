package com.youthchina.Hongsheng;

/**
 * Created by hongshengzhang on 2/24/19.
 */

/*


@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:recommendation.xml"}) //"classpath:company.xml",,  "classpath:location.xml"
//@DatabaseSetup({"classpath:"})
@WebAppConfiguration
public class JobForYouControllerTest {
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
    public void getRecommendInternJobsTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/job-for-you/intern")
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"jobList\":[{\"id\":1,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"北京\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"front\",\"job_description\":\"996\"},{\"id\":2,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"上海\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":3,\"name\":\"front-end\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"上海\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":4,\"name\":\"前端工程师\",\"organization\":{\"id\":2,\"name\":\"百度\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"baidu.com\",\"note\":\"baidu\",\"nation\":\"中国\"},\"location\":\"哥伦比亚特区\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":5,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"哥伦比亚特区\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"front\",\"job_description\":\"996\"},{\"id\":6,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"北京\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":7,\"name\":\"front-end\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"上海\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":8,\"name\":\"前端工程师\",\"organization\":{\"id\":2,\"name\":\"百度\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"baidu.com\",\"note\":\"baidu\",\"nation\":\"中国\"},\"location\":\"上海\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":9,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"哥伦比亚特区\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"front\",\"job_description\":\"996\"},{\"id\":10,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"哥伦比亚特区\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

    @Test
    public void getRecommendGeneralJobTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/job-for-you/general")
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"jobList\":[{\"id\":1,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"北京\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"front\",\"job_description\":\"996\"},{\"id\":2,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"上海\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":3,\"name\":\"front-end\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"上海\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":4,\"name\":\"前端工程师\",\"organization\":{\"id\":2,\"name\":\"百度\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"baidu.com\",\"note\":\"baidu\",\"nation\":\"中国\"},\"location\":\"哥伦比亚特区\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":5,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"哥伦比亚特区\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"front\",\"job_description\":\"996\"},{\"id\":6,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"北京\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":7,\"name\":\"front-end\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"上海\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":8,\"name\":\"前端工程师\",\"organization\":{\"id\":2,\"name\":\"百度\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"baidu.com\",\"note\":\"baidu\",\"nation\":\"中国\"},\"location\":\"上海\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":9,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"哥伦比亚特区\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"front\",\"job_description\":\"996\"},{\"id\":10,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"哥伦比亚特区\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

    @Test
    public void getRecommendCampusJobsTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/job-for-you/campus")
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"jobList\":[{\"id\":1,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"北京\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"front\",\"job_description\":\"996\"},{\"id\":2,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"上海\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":3,\"name\":\"front-end\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"上海\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":4,\"name\":\"前端工程师\",\"organization\":{\"id\":2,\"name\":\"百度\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"baidu.com\",\"note\":\"baidu\",\"nation\":\"中国\"},\"location\":\"哥伦比亚特区\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":5,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"哥伦比亚特区\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"front\",\"job_description\":\"996\"},{\"id\":6,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"北京\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":7,\"name\":\"front-end\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"上海\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":8,\"name\":\"前端工程师\",\"organization\":{\"id\":2,\"name\":\"百度\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"baidu.com\",\"note\":\"baidu\",\"nation\":\"中国\"},\"location\":\"上海\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"},{\"id\":9,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"哥伦比亚特区\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"front\",\"job_description\":\"996\"},{\"id\":10,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"location\":\"哥伦比亚特区\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"job_duty\":\"back\",\"job_description\":\"996\"}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }
}
 */