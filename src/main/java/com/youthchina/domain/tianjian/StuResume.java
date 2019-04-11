package com.youthchina.domain.tianjian;
import java.sql.Timestamp;

public class StuResume {
    private Integer resumeId;
    private String resumeName;
    private String docuLocalId;
    private Integer generateMethod;
    private Timestamp generateTime;
    private Integer stuId;
    private Integer isDelete;
    private Timestamp isDeleteTime;

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public String getDocuLocalId() {
        return docuLocalId;
    }

    public void setDocuLocalId(String docuLocalId) {
        this.docuLocalId = docuLocalId;
    }


    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public Integer getGenerateMethod() {
        return generateMethod;
    }

    public void setGenerateMethod(Integer generateMethod) {
        this.generateMethod = generateMethod;
    }

    public Timestamp getGenerateTime() {
        return generateTime;
    }

    public void setGenerateTime(Timestamp generateTime) {
        this.generateTime = generateTime;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Timestamp getIsDeleteTime() {
        return isDeleteTime;
    }

    public void setIsDeleteTime(Timestamp isDeleteTime) {
        this.isDeleteTime = isDeleteTime;
    }
}
