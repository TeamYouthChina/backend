package com.youthchina.dao.qingyang;

import com.youthchina.domain.qingyang.ResumeJson;
import com.youthchina.domain.qingyang.ResumePDF;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface ResumePDFMapper {
    Integer insertResumePDF(ResumePDF resumePDF);

    ResumePDF selectResumePDF(Integer resumeId);

    List<ResumePDF> selectResumePDFByStuId(Integer userId);

    Integer updateResumePDFName(ResumePDF ResumePDF);

    List<ResumePDF> selectResumePDFByIdList(List<Integer> idList);

    Integer deleteResumePDF(Integer resumeId);
}

