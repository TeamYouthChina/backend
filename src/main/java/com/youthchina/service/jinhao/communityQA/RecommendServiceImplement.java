package com.youthchina.service.jinhao.communityQA;

import com.youthchina.dao.jinhao.JobRecommendMapper;
import com.youthchina.domain.jinhao.recommend.JobRecommendation;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.exception.zhongyang.NotFoundException;

import javax.annotation.Resource;
import java.util.List;

public class RecommendServiceImplement implements RecommendService{

    @Resource
    JobRecommendMapper jobRecommendMapper;

    @Override
    public List<Job> getInternForYou() {
        return null;
    }

    @Override
    public List<Job> getJobForYou() {
        return null;
    }

    @Override
    public List<JobRecommendation> get(List<Integer> ids) throws NotFoundException {
        return null;
    }

    @Override
    public JobRecommendation get(Integer id) throws NotFoundException {
        JobRecommendation jobRecommendation =  jobRecommendMapper.get(id);
        if(jobRecommendation == null){
            throw new NotFoundException(4040,404,"This recommendation does not exist!");
        }else {
            return jobRecommendation;
        }
    }

    @Override
    public JobRecommendation add(JobRecommendation jobRecommendation) {
        jobRecommendMapper.add(jobRecommendation);
        return jobRecommendation;
    }

    @Override
    public void delete(Integer id) {
        jobRecommendMapper.delete(id);
    }

    @Override
    public JobRecommendation update(JobRecommendation jobRecommendation) {
        jobRecommendMapper.update(jobRecommendation);
        return jobRecommendation;
    }
}
