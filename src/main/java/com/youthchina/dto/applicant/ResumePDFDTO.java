package com.youthchina.dto.applicant;

import com.youthchina.domain.qingyang.ResumePDF;
import com.youthchina.dto.RequestDTO;
import com.youthchina.dto.ResponseDTO;

/**
 * @author: Qingyang Zhao
 * @create: 2019-05-06
 **/
public class ResumePDFDTO implements RequestDTO<ResumePDF>, ResponseDTO<ResumePDF> {


    private Integer id;
    private String fileId;
    private String name;

    public ResumePDFDTO() {
    }

    public ResumePDFDTO(ResumePDF resumePDF) {
        this.convertToDTO(resumePDF);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public ResumePDF convertToDomain() {
        ResumePDF resumePDF = new ResumePDF();
        if(this.id != null){
            resumePDF.setResumeId(this.id);
        }
        if(this.fileId != null){
            resumePDF.setDocuLocalId(this.fileId);
        }
        resumePDF.setResumeName(this.name);
        return resumePDF;
    }


    @Override
    public void convertToDTO(ResumePDF domain) {
        this.id = domain.getResumeId();
        this.name = domain.getResumeName();
        this.fileId = domain.getDocuLocalId();
    }
}
