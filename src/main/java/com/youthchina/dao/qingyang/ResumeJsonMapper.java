package com.youthchina.dao.qingyang;

import com.youthchina.domain.qingyang.ResumeJson;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface ResumeJsonMapper {
    Integer insertResumeJson(ResumeJson resumeJson);

    ResumeJson selectResumeJson(Integer resumeId);

    List<ResumeJson> selectResumeJsonByStuId(Integer userId);

    Integer deleteResumeJson(Integer resumeId);

    Integer updateResumeJson(ResumeJson resumeJson);


}

