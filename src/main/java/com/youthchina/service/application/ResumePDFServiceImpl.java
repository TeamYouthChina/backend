package com.youthchina.service.application;

import com.youthchina.dao.qingyang.ResumeJsonMapper;
import com.youthchina.dao.qingyang.ResumePDFMapper;
import com.youthchina.domain.qingyang.ResumeJson;
import com.youthchina.domain.qingyang.ResumePDF;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Qingyang Zhao
 * @create: 2019-04-11
 **/
@Service
public class ResumePDFServiceImpl implements DomainCRUDService<ResumePDF, Integer> {

    @Autowired
    ResumePDFMapper resumePDFMapper;

    @Override
    public ResumePDF get(Integer id) throws NotFoundException {
        ResumePDF resumePDF = resumePDFMapper.selectResumePDF(id);
        if(resumePDF == null) throw new NotFoundException(4040, 404, "resumePDF Not Found");
        return resumePDF;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        resumePDFMapper.deleteResumePDF(id);
    }

    @Override
    public ResumePDF update(ResumePDF resumePDF) throws NotFoundException {
        Integer result = resumePDFMapper.updateResumePDFName(resumePDF);
//        if(result < 1) throw new NotFoundException();
        return this.get(resumePDF.getResumeId());
    }

    @Override
    public ResumePDF add(ResumePDF entity) throws NotFoundException {
        Integer result = resumePDFMapper.insertResumePDF(entity);
        return this.get(entity.getResumeId());
    }
}
