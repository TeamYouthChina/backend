package com.youthchina.service.jinhao.communityQA;

import com.youthchina.domain.jinhao.recommend.JobRecommendation;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.exception.zhongyang.NotFoundException;

import java.util.List;

public interface RecommendService{
    List<Job> getInternForYou();
    List<Job> getJobForYou();

    List<JobRecommendation> get(List<Integer> ids) throws NotFoundException;
    JobRecommendation get(Integer id) throws NotFoundException;
    JobRecommendation add(JobRecommendation jobRecommendation);
    void delete(Integer id);
    JobRecommendation update(JobRecommendation jobRecommendation);
}
