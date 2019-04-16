package com.youthchina.service.application;

import com.youthchina.dao.qingyang.ResumeJsonMapper;
import com.youthchina.domain.qingyang.ResumeJson;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Qingyang Zhao
 * @create: 2019-04-11
 **/
@Service
public class ResumeJsonServiceImpl implements DomainCRUDService<ResumeJson, Integer> {

    @Autowired
    ResumeJsonMapper resumeJsonMapper;

    @Override
    public ResumeJson get(Integer id) throws NotFoundException {
        ResumeJson resumeJson = resumeJsonMapper.selectResumeJson(id);
        if(resumeJson == null) throw new NotFoundException(4040, 404, "ResumeJson Not Found");
        return resumeJson;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        resumeJsonMapper.deleteResumeJson(id);
    }

    @Override
    public ResumeJson update(ResumeJson resumeJson) throws NotFoundException {
        Integer result = resumeJsonMapper.updateResumeJson(resumeJson);
//        if(result < 1) throw new NotFoundException();
        return this.get(resumeJson.getResumeId());
    }

    @Override
    public ResumeJson add(ResumeJson entity) throws NotFoundException {
        Integer result = resumeJsonMapper.insertResumeJson(entity);

        return this.get(entity.getResumeId());
    }
}
