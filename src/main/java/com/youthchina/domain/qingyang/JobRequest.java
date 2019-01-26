package com.youthchina.domain.qingyang;

public class JobRequest {
    /*JOB_DEGREE_REQUIRE
学历要求ID		主键		JOB_REQUIRE_ID	INTEGER	是	否
学历编号				JOB_DEGREE_NUM	INTEGER	否	否	DEGREE_NUM
职位ID		外键		JOB_ID	INTEGER	否	否
是否删除			0	IS_DELETE	INTEGER	否	否	0-默认不删除
删除时间			NULL	IS_DELETE_TIME	TIMESTAMP	否	是
     */
    private Integer jobRequireId;
    private Integer jobDegreeNum;
    private Integer jobId;

    public Integer getJobRequireId() {
        return jobRequireId;
    }

    public void setJobRequireId(Integer jobRequireId) {
        this.jobRequireId = jobRequireId;
    }

    public Integer getJobDegreeNum() {
        return jobDegreeNum;
    }

    public void setJobDegreeNum(Integer jobDegreeNum) {
        this.jobDegreeNum = jobDegreeNum;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }
}
