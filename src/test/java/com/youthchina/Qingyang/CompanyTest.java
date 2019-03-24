package com.youthchina.Qingyang;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.dao.qingyang.JobMapper;
import com.youthchina.dao.qingyang.LocationMapper;
import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


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
        company.setCompanyVerify(1);
        company.setUserId(1);


        companyMapper.insertCompany(company);

        List<Industry> industryList = new ArrayList<>();
        Industry industry = new Industry();
        industry.setIndCode("AAA");
        industryList.add(industry);
        company.setIndList(industryList);

        companyMapper.insertCompanyInd(company.getId(), company.getIndList());


        List<Logo> logoList = new ArrayList<>();
        Logo logo = new Logo();
        logo.setDocuLocalId("logodoc");
        logoList.add(logo);
        company.setLogoList(logoList);

        companyMapper.insertCompanyLogo(company.getId(), company.getLogoList());

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
        companyMapper.insertCompanyInd(company.getId(), company.getIndList());
    }

    @Test
    public void testDeleteCompany() {
        Integer comId = 1;
        jobMapper.deleteJobByComId(comId);
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

    @Test
    public void testGetCompanyLogo(){
        Company company = companyMapper.selectCompany(1);
        Logo logo = company.getLogoList().get(0);
        Assert.assertEquals("COMLOGO1", logo.getDocuLocalId());

    }

    @Test
    public void testGetCompanyPhoto(){
        Company company = companyMapper.selectCompany(1);
        CompanyPhoto photo = company.getPhotoList().get(0);
        Assert.assertEquals("ComPhoto1", photo.getDocuLocalId());
    }

    @Test
    public void testUpdateCompanyPhoto(){
        Company company = companyMapper.selectCompany(1);
        CompanyPhoto photo = company.getPhotoList().get(0);
        photo.setDocuLocalId("CompanyPhotoUpdate");
        companyMapper.updateCompany(company);
        company = companyMapper.selectCompany(company.getCompanyId());
        Assert.assertEquals("CompanyPhotoUpdate", photo.getDocuLocalId());
    }


}
