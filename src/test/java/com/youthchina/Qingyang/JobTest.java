package com.youthchina.Qingyang;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.dao.qingyang.JobMapper;
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
@DatabaseSetup({"classpath:company.xml"})
public class JobTest {

    @Autowired
    private JobMapper jobMapper;
    private CompanyMapper companyMapper;

    @Test
    public void testGetJob() {
        Job job = jobMapper.selectJobByJobId(1);
        Assert.assertEquals("大疆", job.getCompany().getCompanyName());
        Assert.assertEquals("国企", job.getCompany().getCompanyNature().getNatureChn());
        Assert.assertEquals(1, job.getIndustries().size());
        Assert.assertEquals("A", job.getIndustries().get(0).getIndCode());
        Assert.assertEquals("北京", job.getJobLocationList().get(0).getRegion_chn());
        Assert.assertEquals("前端", job.getProfession().getProfChn());
    }


    @Test
    public void testGetJobByList() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        List<Job> jobs = jobMapper.selectJobByJobIdList(ids);
        Assert.assertEquals(2, jobs.size());
        for (Job job : jobs) {
            if(job.getJobId() != 1 && job.getJobId()  != 2){
                Assert.fail();
            }
        }
    }

    @Test
    public void testJobInsert() {
        Job job = new Job();
        Hr hr = new Hr();
        hr.setHrId(1);
        job.setHr(hr);
        Company company = new Company();
        company.setCompanyId(1);
        job.setCompany(company);
        job.setJobName("全栈");
        job.setJobProfCode("111aa");
        job.setJobProfCode("1");
        job.setJobStartTime(Date.valueOf("2019-1-1"));
        job.setJobEndTime(Date.valueOf("2020-1-1"));
        job.setJobTime(1);
        job.setJobDescription("fullStack");
        job.setJobDuty("fullStack");
        job.setJobHighlight("80K");
        job.setCvReceiMail("youth@china");
        job.setCvNameRule("rule");
        job.setJobActive(1);
        jobMapper.insertJob(job);
        List<Industry> industries = new ArrayList<>();
        Industry industry = new Industry();
        industry.setIndCode("1");
        industry.setJobId(job.getJobId());
        industries.add(industry);
        jobMapper.insertJobIndustry(industries);
        List<Degree> degrees = new ArrayList<>();
        Degree degree = new Degree();
        degree.setDegreeNum(1);
        degree.setJobId(job.getJobId());
        degrees.add(degree);
        jobMapper.insertJobDegree(degrees);
        List<Location> locations = new ArrayList<>();
        Location location = new Location();
        location.setRegion_num(1);
        location.setJobId(job.getJobId());
        locations.add(location);
        jobMapper.insertJobLocation(locations);
    }

    @Test
    public void testUpdateJob(){
        Job job = jobMapper.selectJobByJobId(1);
        Assert.assertEquals("Beijing", job.getJobLocationList().get(0).getRegion_eng());
        job.getCompany().setCompanyId(2);
        jobMapper.updateJob(job);
        jobMapper.deleteJobLocation(job.getJobId());
        jobMapper.insertJobLocation(job.getJobLocationList());
        jobMapper.deleteJobIndustry(job.getJobId());
        jobMapper.insertJobIndustry(job.getIndustries());
        jobMapper.deleteJobDegree(job.getJobId());
        jobMapper.insertJobDegree(job.getJobReqList());
    }

    @Test
    public void testDeleteJob(){
        Job job = new Job();
        job.setJobId(1);
        jobMapper.deleteJobDegree(job.getJobId());
        jobMapper.deleteJobIndustry(job.getJobId());
        jobMapper.deleteJobLocation(job.getJobId());
        jobMapper.deleteJob(job.getJobId());
    }
}
