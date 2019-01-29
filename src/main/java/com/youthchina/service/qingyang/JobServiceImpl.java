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

    @Override
    public void delete(User user, Integer jobId) throws NotFoundException {
        jobMapper.deleteJob(jobId);
    }

    @Override
    public Job getByHr(User user, Integer jobId) throws NotBelongException {
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
        List<Job> jobList = jobMapper.selectJobByJobIdList(id);
        return jobList;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        jobMapper.deleteJob(id);
        jobMapper.deleteJobDegree(id);
        jobMapper.deleteJobIndustry(id);
        jobMapper.deleteJobLocation(id);
        jobMapper.deleteJob(id);
    }

    @Override
    public Job update(Job job) throws NotFoundException {
        jobMapper.updateJob(job);
        jobMapper.deleteJobLocation(job.getJobId());
        jobMapper.insertJobLocation(job.getJobLocationList());
        jobMapper.deleteJobIndustry(job.getJobId());
        jobMapper.insertJobIndustry(job.getIndustries());
        jobMapper.deleteJobDegree(job.getJobId());
        jobMapper.insertJobDegree(job.getJobReqList());
        return jobMapper.selectJobByJobId(job.getJobId());
    }

    @Override
    public Job add(Job entity) {
        Integer result = jobMapper.insertJob(entity);
        jobMapper.insertJobIndustry(entity.getIndustries());
        jobMapper.insertJobDegree(entity.getJobReqList());
        jobMapper.insertJobLocation(entity.getJobLocationList());
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



