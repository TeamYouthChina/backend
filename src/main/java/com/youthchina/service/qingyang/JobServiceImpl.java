package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.JobMapper;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.NotBelongException;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class JobServiceImpl implements JobService {

    @Resource
    JobMapper jobMapper;

    public static Job job;

    //给前端的默认值
    public static void initialization(){
        job.setJobId(1);
        job.setCompanyId(1);
        job.setJobName("前端");
        job.setJobStartTime(new Date(20181212));
        job.setJobEndTime(new Date(20190101));
        job.setJobDescription("吃苦耐劳");
        job.setJobTime(1); // 全职1
        job.setJobLocation("北京");
        job.setCvReceiMail("test@test.test");
        job.setJobActive(1); // 1，2，3,4,5
    }

    @Override
    public void delete(User user, Integer jobId) throws NotFoundException {
        jobMapper.deleteJob(jobId);
    }

    @Override
    public Job getByHr(User user, Integer jobId) throws NotBelongException {
        //默认值
        //return job;

        return jobMapper.selectJobByJobId(jobId);
    }

    @Override
    public Map<String, List<Job>> getJobByIndustries(List<String> industries) {
        return null;
    }

    @Override
    public Map<String, List<Job>> getJobByTag(List<String> tags) {
        //todo: implement
        return null;
    }

    @Override
    public Job get(Integer id) throws NotFoundException {

        //默认值
        //return job;

        //implementation
        return jobMapper.selectJobByJobId(id);
    }

    @Override
    public List<Job> get(List<Integer> id) throws NotFoundException {
        List<Job> jobList = new ArrayList<>();

        //默认值, 可注释掉
        //jobList.add(job);

        //implement
        jobList = jobMapper.selectJobByJobIdList(id);

        return jobList;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        jobMapper.deleteJob(id);
    }

    @Override
    public Job update(Job job_qingyang) throws NotFoundException {
        Integer result = jobMapper.updateJob(job_qingyang);
        return this.get(result);
    }

    @Override
    public Job add(Job entity) {
        Integer result = jobMapper.insertJob(entity);
        return jobMapper.selectJobByJobId(result);
    }

    /*通过行业Id List搜索*/
    public List<Job> getByIndustryId(List<Integer> indIds){
        return jobMapper.selectByIndustryId(indIds);
    }

    /*通过行业名称搜索 完全匹配*/
    public List<Job> getByIndustryString(String ind){
        return jobMapper.selectByIndustryString("^" + ind + "$");
    }

}



