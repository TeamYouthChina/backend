package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.JobHrMapper;
import com.youthchina.domain.qingyang.Job_qingyang;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.NotBelongException;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Resource
    JobHrMapper jobHrMapper;

    public static Job_qingyang job;

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
        jobHrMapper.deleteJob(jobId);
    }

    @Override
    public Job_qingyang getByHr(User user, Integer jobId) throws NotBelongException {
        //默认值
        //return job;

        return jobHrMapper.selectJobByJobId(jobId);
    }

    @Override
    public Job_qingyang get(Integer id) throws NotFoundException {

        //默认值
        //return job;

        //implementation
        return jobHrMapper.selectJobByJobId(id);
    }

    @Override
    public List<Job_qingyang> get(List<Integer> id) throws NotFoundException {
        List<Job_qingyang> jobList = new ArrayList<>();

        //默认值, 可注释掉
        //jobList.add(job);

        //implement
        jobList = jobHrMapper.selectJobByJobIdList(id);

        return jobList;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        jobHrMapper.deleteJob(id);
    }

    @Override
    public Job_qingyang update(Job_qingyang job_qingyang) throws NotFoundException {
        Integer result = jobHrMapper.updateJob(job_qingyang);
        return this.get(result);
    }

    @Override
    public Job_qingyang add(Job_qingyang entity) {
        Integer result = jobHrMapper.insertJob(entity);
        return jobHrMapper.selectJobByJobId(result);
    }

    /*通过行业Id List搜索*/
    public List<Job_qingyang> getByIndustryId(List<Integer> indIds){
        return jobHrMapper.selectByIndustryId(indIds);
    }

    /*通过行业名称搜索 完全匹配*/
    public List<Job_qingyang> getByIndustryString(String ind){
        return jobHrMapper.selectByIndustryString("^" + ind + "$");
    }

}



