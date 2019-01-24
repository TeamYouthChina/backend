package com.youthchina.Qingyang;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.CompanyVerification;
import com.youthchina.domain.qingyang.Industry;
import com.youthchina.domain.zhongyang.User;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:company.xml"})
@DatabaseTearDown(value = {"classpath:company.xml"}, type = DatabaseOperation.DELETE)
public class CompanyTest {

    @Autowired
    private CompanyMapper companyMapper;


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
            if(companyVerification.getVerifyId() != 1 && companyVerification.getVerifyId()  != 2){
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
        companyMapper.deleteCompanyVerification(1);
    }


//Company CURD

    @Test
    public void GetCompany() {
        Company company = companyMapper.selectCompany(1);
        System.out.println(company.getCompanyName());
        Assert.assertEquals("大疆", company.getCompanyName());
    }


    @Test
    public void testCompanyInsert() {
        Company company = new Company();
        company.setCompanyName("百度");
        company.setCompanyCode("3");
        company.setCompanyCountry("China");
        company.setCompanyIntroduc("baidu");
        company.setCompanyScaleNum(1);
        company.setCompanyNature("nature");
        company.setCompanyLocation("1");
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
            if(company.getCompanyId() != 1 && company.getCompanyId()  != 2){
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
        companyMapper.deleteCompanyInd(1);
        companyMapper.deleteCompany(1);
    }

}
