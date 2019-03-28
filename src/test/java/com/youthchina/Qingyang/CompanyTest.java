package com.youthchina.Qingyang;

/*
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:company.xml", "classpath:location.xml"})
public class CompanyTest {

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private HrMapper hrMapper;

    @Autowired
    private LocationMapper locationMapper;


    //CompanyVerification CURD
    @Test
    public void testGetVerification() {
        CompanyVerification companyVerification = companyMapper.selectCompanyVerification(1);
        Assert.assertEquals(Integer.valueOf(1), companyVerification.getCompanyId());
    }

    @Test
    public void testGetVerificationByList() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);

        List<CompanyVerification> companyVerifications = companyMapper.selectCompanyVerificationByIdList(ids);
        Assert.assertEquals(2, companyVerifications.size());
        for (CompanyVerification companyVerification : companyVerifications) {
            if (companyVerification.getVerifyId() != 1 && companyVerification.getVerifyId() != 2) {
                Assert.fail();
            }
        }
    }

    @Test
    public void testCompanyVerificationInsert() {
        CompanyVerification verification = new CompanyVerification();
        verification.setCompanyId(2);
        verification.setOperUserId(1);
        verification.setVerifyTime(Date.valueOf("2019-01-01"));
        verification.setVerifyEndTime(Date.valueOf("2020-01-01"));
        companyMapper.insertCompanyVerification(verification);
    }

    @Test
    public void testUpdateCompanyVerification() {
        CompanyVerification verification = companyMapper.selectCompanyVerification(1);
        verification.setVerifyEndTime(Date.valueOf("9999-01-01"));
        companyMapper.updateCompanyVerification(verification);
    }

    @Test
    public void testDeleteCompanyVerification() {
        companyMapper.deleteCompanyVerificationById(1);
    }


//Company CURD

    @Test
    public void GetCompany() {
        Company company = companyMapper.selectCompany(1);
        System.out.println(company.getCompanyName());
        Assert.assertEquals("大疆", company.getCompanyName());
        Assert.assertEquals(Integer.valueOf(1), company.getCompanyVerify());

    }


    @Test
    public void testCompanyInsert() {
        Company company = new Company();
        company.setCompanyName("百度");
        company.setCompanyCode("3");

        Country country = new Country();
        country.setCountryAbbre("CHN");
        company.setCountry(country);

        company.setCompanyIntroduc("baidu");

        CompanyScale companyScale = new CompanyScale();
        companyScale.setScaleNum(1);
        company.setCompanyScale(companyScale);

        CompanyNature companyNature = new CompanyNature();
        companyNature.setNatureNum(1);
        company.setCompanyNature(companyNature);

        Location location = new Location();
        location.setRegion_num(1);
        company.setLocation(location);


        company.setCompanyMail("baidu@com");
        company.setCompanyWebsite("baidu.com");
        company.setCompanyStartDate(Date.valueOf("2006-1-20"));
        company.setCompanyLogo("1");
        company.setCompanyVerify(1);
        company.setUserId(1);


        companyMapper.insertCompany(company);

        List<Industry> industryList = new ArrayList<>();
        Industry industry = new Industry();
        industry.setIndCode("AAA");
        industry.setCompanyId(company.getCompanyId());
        industryList.add(industry);
        company.setIndList(industryList);

        companyMapper.insertCompanyInd(company.getIndList());
    }

    @Test
    public void testGetCompanyByList() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);

        List<Company> companies = companyMapper.selectCompanyByIdList(ids);
        Assert.assertEquals(2, companies.size());
        for (Company company : companies) {
            if (company.getCompanyId() != 1 && company.getCompanyId() != 2) {
                Assert.fail();
            }
        }
    }

    @Test
    public void testUpdateCompany() {
        Company company = companyMapper.selectCompany(1);
        company.setCompanyName("QQ");
        companyMapper.updateCompany(company);
        companyMapper.deleteCompanyInd(company.getCompanyId());
        companyMapper.insertCompanyInd(company.getIndList());
    }

    @Test
    public void testDeleteCompany() {
        Integer comId = 1;
        jobMapper.deleteJobByComId(comId);
        hrMapper.deleteHrByComId(comId);
        companyMapper.deleteCompanyVerificationByComId(comId);
        companyMapper.deleteCompanyEmployee(comId);
        companyMapper.deleteCompanyEvaluate(comId);
        companyMapper.deleteCompanyPhoto(comId);
        companyMapper.deleteStudentComCollection(comId);
        companyMapper.deleteCompanyInd(comId);
        companyMapper.deleteCompany(comId);
    }

    @Test
    public void testGetCompanyByName() {
        List<Company> companyList = companyMapper.selectCompanyByName("腾讯");
        Assert.assertEquals(2, companyList.size());
        Assert.assertEquals("腾讯", companyList.get(0).getCompanyName());
        Assert.assertEquals("腾讯深圳总公司", companyList.get(1).getCompanyName());

        companyList = companyMapper.selectCompanyByName("腾牛讯");
        Assert.assertEquals(1, companyList.size());
    }

    @Test
    public void testGetCompanyLocation() {
        Company company = companyMapper.selectCompany(1);
        Assert.assertEquals(Integer.valueOf(1), company.getLocation().getRegion_num());
        Location location = company.getLocation();
        String region = "" + location.getRegion_num();
        if (region.charAt(0) == '9') {
            location = locationMapper.getUSALocation(location.getRegion_num());
        } else {
            location = locationMapper.getChnLocation(location.getRegion_num());
        }
        company.setLocation(location);
        Assert.assertEquals("北京", company.getLocation().getRegion_chn());

    }

    @Test
    public void testGetCompanyCollectionNum() {
        Company company = companyMapper.selectCompany(1);
        Assert.assertEquals(Integer.valueOf(2), company.getCollectNum());
    }


} */
