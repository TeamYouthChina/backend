package com.youthchina.Qingyang;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.qingyang.CompanyEmployeeMapper;
import com.youthchina.domain.qingyang.CompanyEmployee;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.qingyang.CompanyEmployeeCURDService;
import com.youthchina.service.qingyang.CompanyEmployeeCURDServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.sql.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:New_Company_test.xml", "classpath:companyEmployee.xml"})
public class CompanyEmployeeTest {

    @Autowired
    private CompanyEmployeeMapper companyEmployeeMapper;

    @InjectMocks
    private CompanyEmployeeCURDService service = new CompanyEmployeeCURDServiceImpl();

    @Test
    public void testGetCompanyEmployee() {
        CompanyEmployee employee = companyEmployeeMapper.getCompanyEmployee(1);
        Assert.assertEquals(Integer.valueOf(1), employee.getCompanyId());
    }

    @Test
    public void testInsertCompanyEmployeeMapper() throws NotFoundException {
        Integer result;
        CompanyEmployee employee = new CompanyEmployee();
        employee.setCompanyId(3);
        employee.setUserId(1);
        employee.setWorkStartTime(Date.valueOf("1999-01-01"));
        employee.setWorkEndTime(Date.valueOf("2999-01-01"));
        companyEmployeeMapper.insertCompanyEmployee(employee);//.thenReturn(employee.getCompanyId());
        //Assert.assertEquals(service.get(employee.getCompanyId()), employee);
    }
    

}
