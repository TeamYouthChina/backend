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
@DatabaseSetup({"classpath:company.xml"})
public class JobTest {

    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private LocationMapper locationMapper;

    @Test
    public void testGetJob() { // Base Get
        Job job = jobMapper.selectJobByJobId(1);
        Assert.assertEquals("大疆", job.getCompany().getCompanyName());
        Assert.assertEquals("国企", job.getCompany().getCompanyNature().getNatureChn());
        Assert.assertEquals(2, job.getIndustries().size());
        Assert.assertEquals("A", job.getIndustries().get(0).getIndCode());
        Assert.assertEquals(Integer.valueOf(1), job.getJobLocationList().get(0).getRegion_num());
        Assert.assertEquals("前端", job.getProfession().getProfChn());

    }

    @Test
    public void testGetJobLocation(){
        Job job = jobMapper.selectJobByJobId(2);
        List<Location> locationList = job.getJobLocationList();
        String region0 = "" + locationList.get(0).getRegion_num();
        if(region0.charAt(0) == '9'){
            locationList = locationMapper.getUSALocationByLocationList(locationList);
        } else  {
            locationList = locationMapper.getChnLocationByLocationList(locationList);
        }
        Assert.assertEquals("Shanghai", locationList.get(0).getRegion_eng());

        job = jobMapper.selectJobByJobId(4);
        locationList = job.getJobLocationList();
        region0 = "" + locationList.get(0).getRegion_num();
        if(region0.charAt(0) == '9'){
            locationList = locationMapper.getUSALocationByLocationList(locationList);
        } else  {
            locationList = locationMapper.getChnLocationByLocationList(locationList);
        }
        Assert.assertEquals("District of Columbia", locationList.get(0).getRegion_eng());

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
        job.setJobType(1);
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
        locations.add(location);
        job.setJobLocationList(locations);
        jobMapper.insertJobLocation(job.getId(), job.getJobLocationList());
    }

    @Test
    public void testUpdateJob(){
        Job job = jobMapper.selectJobByJobId(1);
        Assert.assertEquals(Integer.valueOf(1), job.getJobLocationList().get(0).getRegion_num());
        job.getCompany().setCompanyId(2);
        jobMapper.updateJob(job);
        jobMapper.deleteJobLocation(job.getJobId());
        jobMapper.insertJobLocation(job.getId(), job.getJobLocationList());
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

    @Test
    public void testJobSearch(){

        //Base Test
        List<Job> jobList = jobMapper.getJobByMore(null,"front",null,"大疆",
                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
                null, null, null);
        Assert.assertEquals(2, jobList.size());
        Assert.assertEquals("大疆", jobList.get(0).getCompany().getCompanyName());

        //TODO: Location test
//        jobList = jobMapper.getJobByMore(null,null,null,null,
//                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
//                null, null, null);
//        Assert.assertEquals(1, jobList.size());
//
//        jobList = jobMapper.getJobByMore(null,null,null,null,
//                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
//                null, null, null);
//        Assert.assertEquals(4, jobList.size());

        //degree test
        List<Degree> degreeList = new ArrayList<>();
        Degree degree = new Degree();
        degree.setDegreeNum(1);
        degreeList.add(degree);
        jobList = jobMapper.getJobByMore(null,null,null,null,
                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
                null, degreeList, null);
        Assert.assertEquals(2, jobList.size());

        Degree degree2 = new Degree();
        degree2.setDegreeNum(2);
        degreeList.add(degree2);
        jobList = jobMapper.getJobByMore(null,null,null,null,
                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
                null, degreeList, null);
        Assert.assertEquals(4, jobList.size());

        degreeList.remove(0);
        jobList = jobMapper.getJobByMore(null,null,null,null,
                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
                null, degreeList, null);
        Assert.assertEquals(3, jobList.size());

        // Industry Test
        List<Industry> industryList = new ArrayList<>();
        Industry industry = new Industry();
        industry.setIndCode("A");
        industryList.add(industry);
        jobList = jobMapper.getJobByMore(null,null,null,null,
                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
                null, null, industryList);
        Assert.assertEquals(1, jobList.size());

        Industry industry2 = new Industry();
        industry2.setIndCode("B");
        industryList.add(industry2);
        jobList = jobMapper.getJobByMore(null,null,null,null,
                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
                null, null, industryList);
        Assert.assertEquals(4, jobList.size());

        //Search Job by Name
        jobList = jobMapper.getJobByMore(null,"front",null,null,
                null, null, null, null, null, null,
                null, null, null);
        Assert.assertEquals(2, jobList.size());

        jobList = jobMapper.getJobByMore(null,"前端",null,null,
                null, null, null, null, null, null,
                null, null, null);
        Assert.assertEquals(1, jobList.size());

        //All job
        jobList = jobMapper.getJobByMore(null,null,null,null,
                null, null, null, null, null, null,
                null, null, null);
        Assert.assertEquals(4, jobList.size());

        //Job Salary
        jobList = jobMapper.getJobByMore(null,null,null,null,
                null, null, null, null, 6500, null,
                null, null, null);
        Assert.assertEquals(2, jobList.size());



    }
}
