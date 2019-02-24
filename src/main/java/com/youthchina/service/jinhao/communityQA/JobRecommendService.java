package com.youthchina.service.jinhao.communityQA;


import com.youthchina.domain.qingyang.Job;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface JobRecommendService extends DomainCRUDService<Job, Integer> {
    List<Job> getInternForYou();
    List<Job> getJobForYou();

//    List<Job> get(List<Integer> ids) throws NotFoundException;
//    Job get(Integer id) throws NotFoundException;
//    Job add(Job jobRecommendation);
//    void delete(Integer id);
//    Job update(Job jobRecommendation);
}
