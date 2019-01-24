package com.youthchina.domain.qingyang;

import java.sql.Timestamp;

public class JobLocation {
/*JOB_LOCATION
地点ID		主键		JOB_REGION_ID	INTEGER	是	否
地点行政区划				JOB_REGION_NUM	INTEGER	否	否	REGION_NUM
职位ID		外键		JOB_ID	INTEGER	否	否
是否删除			0	IS_DELETE	INTEGER	否	否	0-默认不删除
删除时间			NULL	IS_DELETE_TIME	TIMESTAMP	否	是	*/
    private Integer jobRegionId;
    private Integer jobRegionNum;
    private Integer jobId;
    private Integer isDelete;
    private Timestamp isDeleteTime;


    public Integer getJobRegionId() {
        return jobRegionId;
    }

    public void setJobRegionId(Integer jobRegionId) {
        this.jobRegionId = jobRegionId;
    }

    public Integer getJobRegionNum() {
        return jobRegionNum;
    }

    public void setJobRegionNum(Integer jobRegionNum) {
        this.jobRegionNum = jobRegionNum;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
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
