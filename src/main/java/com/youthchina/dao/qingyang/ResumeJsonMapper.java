package com.youthchina.dao.qingyang;

import com.youthchina.domain.Qinghong.ResumeJson;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.CompanyVerification;
import com.youthchina.domain.qingyang.Industry;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface ResumeJsonMapper {
    Integer insertResumeJson(ResumeJson resumeJson);
    ResumeJson selectResumeJson(Integer resume_id);
    Integer deleteResumeJson(Integer resume_Id);


}

