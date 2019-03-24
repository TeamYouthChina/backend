package com.youthchina.domain.qingyang;

import java.sql.Timestamp;

/**
 * @author: Qingyang Zhao
 * @create: 2019-03-24
 **/
public class CompanyPhoto {
    private Integer photoId;
    private String docuLocalId;
    private Timestamp uploadTime;
    private Integer companyId;


    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public String getDocuLocalId() {
        return docuLocalId;
    }

    public void setDocuLocalId(String docuLocalId) {
        this.docuLocalId = docuLocalId;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
