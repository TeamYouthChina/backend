package com.youthchina.dao.qingyang;

import com.youthchina.domain.Qinghong.ResumeJson;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface ResumeJsonMapper {
    Integer insertResumeJson(ResumeJson resumeJson);
    ResumeJson selectResumeJson(Integer resume_id);
    Integer deleteResumeJson(Integer resume_id);


}

