package com.youthchina.domain.qingyang;

import java.sql.Timestamp;

public class Hr_qingyang {
/*
招聘者ID		主键		HR_ID	    INTEGER		否
所属企业				COMPANY_ID	INTEGER		否	企业ID
在职验证				HR_ON_JOB	INTEGER		否	1-已验证，2-未验证
用户ID		外键		USER_ID	    INTEGER		否
是否删除				IS_DELETE	INTEGER		否	0-默认不删除
删除时间				IS_DELETE_TIME	TIMESTAMP		是	*/

    private Integer hrId;
    private Integer companyId;
    private Integer hrOnJob;
    private Integer userId;
    private Integer isDelete;
    private Timestamp isDeleteTime;

    public Integer getHrId() {
        return hrId;
    }

    public void setHrId(Integer hrId) {
        this.hrId = hrId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getHrOnJob() {
        return hrOnJob;
    }

    public void setHrOnJob(Integer hrOnJob) {
        this.hrOnJob = hrOnJob;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
