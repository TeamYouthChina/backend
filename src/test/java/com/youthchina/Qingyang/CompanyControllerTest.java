package com.youthchina.Qingyang;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-16
 **/
/*
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:company.xml"})
@WebAppConfiguration
public class CompanyControllerTest {

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
    public void testgetCompany() throws Exception {
        Integer id = 1;
        this.mvc.perform(
                get(this.urlPrefix + "/companies/" + id)//.param("id", "1").param("detailLevel", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":" + id + ",\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testAddCompany() throws Exception {
        CompanyRequestDTO companyRequestDTO = new CompanyRequestDTO();
        companyRequestDTO.setName("Vavle");
        Location location = new Location();
        location.setRegion_num(1);
        companyRequestDTO.setLocation(new LocationDTO(location));
        Country country = new Country();
        country.setCountryAbbre("USA");
        companyRequestDTO.setNation(new NationDTO(country));
        companyRequestDTO.setWebsite("vavle.com");
        companyRequestDTO.setAvatarUrl("vavle.com/AvatarUrl");
        companyRequestDTO.setNote("Steam");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String insertJson = ow.writeValueAsString(companyRequestDTO);


        this.mvc.perform(
                post(this.urlPrefix + "/companies")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(insertJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"name\":\"Vavle\",\"location\":{\"nation_code\":\"CHN\",\"location_code\":\"1\"},\"website\":\"vavle.com\",\"nation\":{\"countryAbbre\":\"USA\"},\"avatarUrl\":\"vavle.com/AvatarUrl\",\"note\":\"Steam\",\"userId\":1},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testUpdateCompany() throws Exception {
        int id = 1;
        CompanyRequestDTO companyRequestDTO = new CompanyRequestDTO();
        companyRequestDTO.setId(id);
        companyRequestDTO.setName("Vavle");
        Location location = new Location();
        location.setRegion_num(1);
        companyRequestDTO.setLocation(new LocationDTO(location));
        Country country = new Country();
        country.setCountryAbbre("USA");
        companyRequestDTO.setNation(new NationDTO(country));
        companyRequestDTO.setWebsite("vavle.com");
        companyRequestDTO.setAvatarUrl("vavle.com/AvatarUrl");
        companyRequestDTO.setNote("Steam");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String insertJson = ow.writeValueAsString(companyRequestDTO);

        System.out.println(insertJson);
        this.mvc.perform(
                put(this.urlPrefix + "/companies/" + id)
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(insertJson)

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":" + id + ",\"name\":\"Vavle\",\"location\":{\"nation_code\":\"CHN\",\"location_code\":\"1\"},\"website\":\"vavle.com\",\"nation\":{\"countryAbbre\":\"USA\"},\"avatarUrl\":\"vavle.com/AvatarUrl\",\"note\":\"Steam\",\"userId\":1},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;

    }


}
*/