package com.youthchina.domain.qingyang;

import java.sql.Timestamp;

public class Degree {
    /* create table SYS_DEGREE
(
	DEGREE_NUM int auto_increment comment '学位编号'
		primary key,
	DEGREE_CHN varchar(100) not null comment '中文描述',
	DEGREE_ENG varchar(100) not null comment '英文描述',
	START_DATE timestamp not null comment '启用时间'
)
comment '学位表';
*/
    private Integer degreeNum;
    private String degreeChn;
    private String degreeEng;
    private Timestamp startDate;

    public Integer getDegreeNum() {
        return degreeNum;
    }

    public void setDegreeNum(Integer degreeNum) {
        this.degreeNum = degreeNum;
    }

    public String getDegreeChn() {
        return degreeChn;
    }

    public void setDegreeChn(String degreeChn) {
        this.degreeChn = degreeChn;
    }

    public String getDegreeEng() {
        return degreeEng;
    }

    public void setDegreeEng(String degreeEng) {
        this.degreeEng = degreeEng;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }
}
