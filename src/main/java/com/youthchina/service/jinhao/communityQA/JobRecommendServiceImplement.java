package com.youthchina.service.jinhao.communityQA;

import com.youthchina.dao.jinhao.RecommendMapper;
import com.youthchina.dao.qingyang.JobMapper;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.qingyang.JobService;
import com.youthchina.service.qingyang.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobRecommendServiceImplement implements JobRecommendService {

    @Resource
    RecommendMapper recommendMapper;

    @Resource
    JobMapper jobMapper;

    @Autowired
    JobServiceImpl jobService;

    @Override
    public List<Job> getInternForYou() {
        List<Integer> jobIds = recommendMapper.getRandomIntern();
        List<Job> jobs = new ArrayList<>();
        for (Integer id : jobIds) {
            jobs.add(jobMapper.selectJobByJobId(id));
        }
        for (Job job : jobs) {
            jobService.setJobLocation(job);
        }
        return jobs;
    }

    @Override
    public List<Job> getJobForYou() {
        List<Integer> jobIds = recommendMapper.getRandomJob();
        List<Job> jobs = new ArrayList<>();
        for (Integer id : jobIds) {
            jobs.add(jobMapper.selectJobByJobId(id));
        }
        for (Job job : jobs) {
            jobService.setJobLocation(job);
        }
        return jobs;
    }

    @Override
    public Job get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Job> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Job update(Job job) throws NotFoundException {
        return null;
    }

    @Override
    public Job add(Job entity) {
        return null;
    }
}
