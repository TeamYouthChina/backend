package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.PDFUploadMapper;
import com.youthchina.domain.tianjian.StuResume;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class PDFUploadServiceImpl implements PDFUploadService {

    @Resource
    PDFUploadMapper pdfUploadMapper;

    @Override
    public StuResume get(Integer id) throws NotFoundException {
        StuResume stuResume = pdfUploadMapper.get(id);
        if(stuResume == null){
            throw new NotFoundException(404,404,"this PDF does not exist");
        }
        return stuResume;
    }

    @Override
    public List<StuResume> get(List<Integer> id){
        List<StuResume> stuResumes = new LinkedList<>();
        for(Integer one : id){
            StuResume stuResume = pdfUploadMapper.get(one);
            stuResumes.add(stuResume);
        }
        return stuResumes;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        pdfUploadMapper.delete(id);
    }

    @Override
    public StuResume update(StuResume stuResume) throws NotFoundException {
        StuResume stuResume1 = pdfUploadMapper.get(stuResume.getResumeId());
        if (stuResume1 != null)
            throw new NotFoundException(404, 404, "this resume is not exist");//todo
        else {
            pdfUploadMapper.update(stuResume);
        }
        return stuResume;
    }

    @Override
    public StuResume add(StuResume entity){
        pdfUploadMapper.add(entity);
        return entity;
    }
}
