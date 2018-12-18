package com.youthchina.domain.qingyang;

import java.sql.Date;
import java.sql.Timestamp;

/*
认证ID		主键		VERIFY_ID	    INTEGER	否
认证时间				VERIFY_TIME	    DATE	否
到期时间				VERIFY_END_TIME	DATE    否
操作人				OPER_USER_ID	INTEGER	否
企业ID		外键		COMPANY_ID	    INTEGER	否
是否删除				IS_DELETE	    INTEGER	否	0-默认不删除
删除时间(可空)		IS_DELETE_TIME	TIMESTAMP是	*/
public class CompanyVerification_qingyang {

    private Integer verifyId;
    private Date verifyTime;
    private Date verifyEndTime;
    private Integer operUserId;
    private Integer companyId;
    private Integer isDelete;
    private Timestamp isDeleteTime;

    public Integer getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(Integer verifyId) {
        this.verifyId = verifyId;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Date getVerifyEndTime() {
        return verifyEndTime;
    }

    public void setVerifyEndTime(Date verifyEndTime) {
        this.verifyEndTime = verifyEndTime;
    }

    public Integer getOperUserId() {
        return operUserId;
    }

    public void setOperUserId(Integer operUserId) {
        this.operUserId = operUserId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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
