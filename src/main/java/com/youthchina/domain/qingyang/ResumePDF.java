package com.youthchina.domain.qingyang;

/**
 * @author: Qingyang Zhao
 * @create: 2019-05-05
 **/
public class ResumePDF {


    private Integer resumeId; //'主键ID'
    private String resumeName; //'简历文件名',
    private String docuLocalId; //'本地文件编号',
    private Integer generateMethod; //'简历生成方式',
    public final static int  GENERATED = 999; // 系统生成
    public final static int UPLOADED = 900; // 手动上传
    private Integer stuId; // '用户ID',

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public String getResumeName() {
        return resumeName;
    }

    public void setResumeName(String resumeName) {
        this.resumeName = resumeName;
    }

    public String getDocuLocalId() {
        return docuLocalId;
    }

    public void setDocuLocalId(String docuLocalId) {
        this.docuLocalId = docuLocalId;
    }

    public Integer getGenerateMethod() {
        return generateMethod;
    }

    public void setGenerateMethod(Integer generateMethod) {
        this.generateMethod = generateMethod;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }
}
