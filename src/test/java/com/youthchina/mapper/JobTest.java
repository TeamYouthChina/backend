package com.youthchina.mapper;


import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.dao.qingyang.JobMapper;
import com.youthchina.dao.qingyang.LocationMapper;
import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.*;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.qingyang.JobServiceImpl;
import com.youthchina.service.qingyang.LocationServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JobTest {

    @Autowired
    private JobMapper jobMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private LocationServiceImpl locationServiceImpl;

    @Autowired
    private JobServiceImpl jobService;

    @Test
    public void testGetJob() { // Base Get
        Job job = jobMapper.selectJobByJobId(1);
        Company company = companyMapper.selectCompany(job.getCompany().getCompanyId());
        Assert.assertEquals("深圳市腾讯计算机系统有限公司", company.getCompanyName());
//        Assert.assertEquals("国企", company.getCompanyNature().getNatureChn());
        Assert.assertEquals(1, job.getIndustries().size());
        Assert.assertEquals("I6510", job.getIndustries().get(0).getIndCode());
        Assert.assertEquals(Integer.valueOf(440100), job.getJobLocationList().get(0).getRegionNum());
//        Assert.assertEquals("前端", job.getProfession().getProfChn());
        Assert.assertEquals(0, job.getJobReqList().size());
    }

    @Test
    public void testGetJobReqList() throws NotFoundException {
        Job job = jobService.get(6);
        Assert.assertEquals(3, job.getJobReqList().size());
        Assert.assertEquals(Integer.valueOf(4), job.getJobReqList().get(0).getDegreeNum());

    }

    @Test
    public void testGetJobLocation() throws Exception {
        Job job = jobMapper.selectJobByJobId(2);
        List<Location> locationList = job.getJobLocationList();

        //Service Test
        job = jobService.get(2);
        Assert.assertEquals("Java/Javascript实习生", job.getJobName());
        Assert.assertEquals(1, job.getJobLocationList().size());
        Assert.assertEquals(Integer.valueOf(440100), job.getJobLocationList().get(0).getRegionId());
        Assert.assertEquals("广东省广州市", job.getJobLocationList().get(0).getRegionName());

    }


    @Test
    public void testGetJobByList() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        List<Job> jobs = jobMapper.selectJobByJobIdList(ids);
        Assert.assertEquals(2, jobs.size());
        for (Job job : jobs) {
            if (job.getJobId() != 1 && job.getJobId() != 2) {
                Assert.fail();
            }
        }
    }

    @Test
    public void testJobInsert() {
        Job job = new Job();

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
        job.setUserId(1);
        jobMapper.insertJob(job);
        List<Industry> industries = new ArrayList<>();
        Industry industry = new Industry();
        industry.setIndCode("1");
        industry.setJobId(job.getJobId());
        industries.add(industry);
        jobMapper.insertJobIndustry(industries);
        List<Degree> degreeList = new ArrayList<>();
        Degree degree = new Degree();
        degree.setDegreeNum(1);
        degree.setJobId(job.getJobId());
        degreeList.add(degree);
        jobMapper.insertJobDegree(job.getId(), degreeList);
        List<Location> locations = new ArrayList<>();
        Location location = new Location();
        location.setRegionNum(1);
        location.setRegionId(444000);
        location.setCountry("CHN");
        locations.add(location);
        job.setJobLocationList(locations);
        jobMapper.insertJobLocation(job.getId(), job.getJobLocationList());
        List<Logo> logoList = new ArrayList<>();
        Logo logo = new Logo();
        logo.setDocuLocalId("2856306669745344512");
        logoList.add(logo);
        job.setLogoList(logoList);
        jobMapper.insertJobLogo(job.getId(), job.getLogoList());
    }

    @Test
    public void testUpdateJobMapper() {
        Job job = jobMapper.selectJobByJobId(1);
        Assert.assertEquals(Integer.valueOf(440100), job.getJobLocationList().get(0).getRegionNum());
        job.getCompany().setCompanyId(2);
        jobMapper.updateJob(job);
        jobMapper.deleteJobLocation(job.getJobId());
        jobMapper.insertJobLocation(job.getId(), job.getJobLocationList());
        jobMapper.deleteJobIndustry(job.getJobId());
        jobMapper.insertJobIndustry(job.getIndustries());
        jobMapper.deleteJobDegree(job.getJobId());
//        Assert.assertEquals(0, job.getJobReqList().size());
//        jobMapper.insertJobDegree(job.getId(), job.getJobReqList());
    }

    @Test
    public void testUpdateJobService() throws NotFoundException {
        Job job = jobService.get(1);
        job.getCompany().setCompanyId(2);
        job = jobService.update(job);
        Assert.assertEquals(0, job.getJobReqList().size());
        Assert.assertEquals("产品信息管理实习生", job.getJobName());
        Assert.assertEquals(Integer.valueOf(2), job.getCompany().getId());
        Assert.assertEquals("中国石油天然气股份有限公司", job.getCompany().getCompanyName());
    }

    @Test
    public void testDeleteJob() {
        Job job = new Job();
        job.setJobId(1);
//        jobMapper.deleteJobDegree(job.getJobId());
//        jobMapper.deleteJobIndustry(job.getJobId());
//        jobMapper.deleteJobLocation(job.getJobId());
//        jobMapper.deleteJobLogo(job.getId());
        jobMapper.deleteJob(job.getJobId());
    }

    @Test
    public void testDeleteJob2() {
        Job job = new Job();
        job.setJobId(1);
        jobMapper.deleteJob(job.getJobId());
    }

    @Test
    public void testJobSearch() {
        List<Job> jobList = jobMapper.getJobByMore(null, "开发", null, null,
                null, null, null, null, null, null,
                null, null, null);
        Assert.assertEquals(5, jobList.size());
        //TODO:All
        //Base Test
//        List<Job> jobList = jobMapper.getJobByMore(null, "front", null, "大疆",
//                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
//                null, null, null);
//        Assert.assertEquals(0, jobList.size());
//        Assert.assertEquals("大疆", jobList.get(0).getCompany().getCompanyName());
//
//        //TODO: Location test
////        jobList = jobMapper.getJobByMore(null,null,null,null,
////                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
////                null, null, null);
////        Assert.assertEquals(1, jobList.size());
////
////        jobList = jobMapper.getJobByMore(null,null,null,null,
////                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
////                null, null, null);
////        Assert.assertEquals(4, jobList.size());
//
//        //degree test
//        List<Degree> degreeList = new ArrayList<>();
//        Degree degree = new Degree();
//        degree.setDegreeNum(1);
//        degreeList.add(degree);
//        jobList = jobMapper.getJobByMore(null, null, null, null,
//                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
//                null, degreeList, null);
//        Assert.assertEquals(2, jobList.size());
//
//        Degree degree2 = new Degree();
//        degree2.setDegreeNum(2);
//        degreeList.add(degree2);
//        jobList = jobMapper.getJobByMore(null, null, null, null,
//                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
//                null, degreeList, null);
//        Assert.assertEquals(4, jobList.size());
//
//        degreeList.remove(0);
//        jobList = jobMapper.getJobByMore(null, null, null, null,
//                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
//                null, degreeList, null);
//        Assert.assertEquals(3, jobList.size());
//
//        // Industry Test
//        List<Industry> industryList = new ArrayList<>();
//        Industry industry = new Industry();
//        industry.setIndCode("A");
//        industryList.add(industry);
//        jobList = jobMapper.getJobByMore(null, null, null, null,
//                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
//                null, null, industryList);
//        Assert.assertEquals(1, jobList.size());
//
//        Industry industry2 = new Industry();
//        industry2.setIndCode("B");
//        industryList.add(industry2);
//        jobList = jobMapper.getJobByMore(null, null, null, null,
//                Date.valueOf("2000-1-1"), Date.valueOf("2999-1-1"), 1, 0, null, null,
//                null, null, industryList);
//        Assert.assertEquals(4, jobList.size());
//
//        //Search Job by Name
//        jobList = jobMapper.getJobByMore(null, "front", null, null,
//                null, null, null, null, null, null,
//                null, null, null);
//        Assert.assertEquals(2, jobList.size());
//
//        jobList = jobMapper.getJobByMore(null, "前端", null, null,
//                null, null, null, null, null, null,
//                null, null, null);
//        Assert.assertEquals(1, jobList.size());
//
//        //All job
//        jobList = jobMapper.getJobByMore(null, null, null, null,
//                null, null, null, null, null, null,
//                null, null, null);
//        Assert.assertEquals(4, jobList.size());
//
//        //Job Salary
//        jobList = jobMapper.getJobByMore(null, null, null, null,
//                null, null, null, null, 6500, null,
//                null, null, null);
//        Assert.assertEquals(2, jobList.size());


    }

    @Test
    public void testJobCollection() {
        // TODO:
//        Job job = jobMapper.selectJobByJobId(1);
//        Assert.assertEquals(Integer.valueOf(2), job.getCollectNum());
//
//        job = jobMapper.selectJobByJobId(2);
//        Assert.assertEquals(Integer.valueOf(0), job.getCollectNum());
    }

    @Test
    public void testAllJob() {
        List<Job> jobList = jobMapper.selectAllJob();
        Assert.assertEquals(18, jobList.size());
    }

    @Test
    public void testJobSearchMapper() {
        //SalaryCap
        List<Job> jobs = jobMapper.getJobByMore(null, null, null, null,
                null, null, null, null, 6500, null,
                null, null, null);
        Assert.assertEquals(11, jobs.size());

    }

    @Test
    public void testGetJobByUserId(){
        List<Job> jobList = jobService.getJobByUserId(1);
        Assert.assertEquals(18, jobList.size());

        jobList = jobService.getJobByUserId(2);
        Assert.assertEquals(0, jobList.size());

    }

    @Test
    public void isCollectTest(){
        Integer res = jobMapper.isCollect(1 ,10);
        Assert.assertEquals(Integer.valueOf(1), res);
        res = jobMapper.isCollect(1,1);
        Assert.assertEquals(null, res);
        boolean isCollected = jobService.isCollected(1,10);
        Assert.assertEquals(true, isCollected);
        isCollected = jobService.isCollected(1,1);
        Assert.assertEquals(false, isCollected);

    }
}