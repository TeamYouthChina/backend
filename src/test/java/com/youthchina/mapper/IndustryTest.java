package com.youthchina.mapper;

/*
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:company.xml"})
public class IndustryTest {

    @Autowired
    private CompanyMapper companyMapper;

    @Test
    public void testGetIndustry() {
        Industry industry = companyMapper.selectIndustry(2);
        Assert.assertEquals("农", industry.getIndChn());
    }


    @Test
    public void testIndustryInsert() {
        Industry industry = new Industry();
        industry.setIndCode("A");
        industry.setIndChn("商");
        industry.setIndEng("business");
        industry.setIndLevel(1);
        industry.setIndParentCode("A3");
        industry.setStartTime(Timestamp.valueOf("2012-12-12 12:12:12"));
        companyMapper.insertIndustry(industry);
    }

    @Test
    public void testGetIndustryByList() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        List<Industry> industries = companyMapper.selectIndustryByIdList(ids);
        Assert.assertEquals(2, industries.size());
        for (Industry industry : industries) {
            if (industry.getIndNum() != 1 && industry.getIndNum() != 2) {
                Assert.fail();
            }
        }
    }

    @Test
    public void testUpdateIndustry() {
        Industry industry = companyMapper.selectIndustry(2);
        industry.setIndChn("互联网");
        companyMapper.updateIndustry(industry);
    }

    @Test
    public void testDeleteIndustry() {
        companyMapper.deleteIndustry(1);
    }
} */
