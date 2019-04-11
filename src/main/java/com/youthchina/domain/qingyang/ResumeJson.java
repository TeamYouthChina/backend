package com.youthchina.domain.qingyang;

import java.sql.Timestamp;

/**
 * @author: Qingyang Zhao
 * @create: 2019-04-11
 **/
public class ResumeJson {
    private Integer resumeId;
    private String jsonContent;
    private Integer userId;
    private Timestamp createTime;

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
