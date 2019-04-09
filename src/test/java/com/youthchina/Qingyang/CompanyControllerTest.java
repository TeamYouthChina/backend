package com.youthchina.Qingyang;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-16
 **/
/*
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:New_Company_test.xml","classpath:New_Dictionary_test.xml","classpath:New_SYS_test.xml"})
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
    public void testGetCompany() throws Exception {
        Integer id = 1;
        this.mvc.perform(
                get(this.urlPrefix + "/companies/" + id)//.param("id", "1").param("detailLevel", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                //TODO : "avatarUrl":null,???
                .andExpect(content().json("{\"content\":{\"id\":1,\"name\":\"中国石油化工股份有限公司\",\"location\":\"北京市\",\"website\":\"http://www.sinopec.com\",\"note\":\"中国石油化工股份有限公司是一家上中下游一体化、石油石化主业突出、拥有比较完备销售网络、境内外上市的股份制企业。中国石化是由中国石油化工集团公司依据《中华人民共和国公司法... \",\"nation\":\"中国\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testGetRecommendFiveCompany() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/companies/")//.param("id", "1").param("detailLevel", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":{\"id\":" + id + ",\"name\":\"\",\"avatarUrl\":\"\",\"location\":\"\",\"website\":\"\",\"note\":\"\",\"nation\":\"\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
                //.andExpect(content().json("{\"content\":{\"id\":1,\"name\":\"中国石油化工股份有限公司\",\"avatarUrl\":null,\"location\":\"北京市\",\"website\":\"http://www.sinopec.com\",\"note\":\"中国石油化工股份有限公司是一家上中下游一体化、石油石化主业突出、拥有比较完备销售网络、境内外上市的股份制企业。中国石化是由中国石油化工集团公司依据《中华人民共和国公司法... \",\"nation\":null},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testDeleteCompany() throws Exception {
        Integer id = 1;
        this.mvc.perform(
                delete(this.urlPrefix + "/companies/" + id)
                        .with(authGenerator.authentication())

        )
                .andDo(print())
        ;
    }




    @Test
    public void testAddCompany() throws Exception {
        CompanyRequestDTO companyRequestDTO = new CompanyRequestDTO();
        companyRequestDTO.setName("Vavle");
        Location location = new Location();
        location.setRegionId(994701);
        location.setCountry("USA");
        companyRequestDTO.setLocation(new LocationDTO(location));
        companyRequestDTO.setNation("USA");
        companyRequestDTO.setWebsite("vavle.com");
        //companyRequestDTO.setAvatarUrl("vavle.com/AvatarUrl");
        companyRequestDTO.setNote("Steam");


        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String insertJson = ow.writeValueAsString(companyRequestDTO);
        System.out.println(insertJson);

        this.mvc.perform(
                post(this.urlPrefix + "/companies")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(insertJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                //,"avatarUrl":"vavle.com/AvatarUrl"
                .andExpect(content().json("{\"content\":{\"name\":\"Vavle\",\"location\":\"Berkeley\",\"website\":\"vavle.com\",\"note\":\"Steam\",\"nation\":\"美国\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))

        ;
    }

    @Test
    public void testUpdateCompany() throws Exception {
        int id = 1;
        CompanyRequestDTO companyRequestDTO = new CompanyRequestDTO();
        companyRequestDTO.setId(id);
        companyRequestDTO.setName("Vavle");
        Location location = new Location();
        location.setRegionId(994701);
        location.setCountry("USA");
        companyRequestDTO.setLocation(new LocationDTO(location));
        companyRequestDTO.setNation("USA");
        companyRequestDTO.setWebsite("vavle.com");
        //companyRequestDTO.setAvatarUrl("vavle.com/AvatarUrl");
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
                //,"avatarUrl":"vavle.com/AvatarUrl"
                .andExpect(content().json("{\"content\":{\"id\":" + id + ",\"name\":\"Vavle\",\"location\":\"Berkeley\",\"website\":\"vavle.com\",\"note\":\"Steam\",\"nation\":\"美国\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;

    }


}
*/