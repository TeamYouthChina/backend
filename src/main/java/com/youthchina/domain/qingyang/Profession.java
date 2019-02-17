package com.youthchina.domain.qingyang;

import java.sql.Timestamp;

public class Profession {
/*
create table if not exists SYS_PROF_CLASS
(
	PROF_NUM int auto_increment comment '职业编号'
		primary key,
	PROF_LEVEL int not null comment '职业分级',
	PROF_CODE varchar(20) not null comment '职业代码',
	PROF_PARENT_CODE varchar(20) not null comment '上级职业代码',
	PROF_CHN varchar(100) not null comment '中文描述',
	PROF_ENG varchar(100) null comment '英文描述',
	START_TIME timestamp not null comment '启用时间',
	IS_DELETE int default 0 null comment '是否删除',
	IS_DELETE_TIME timestamp null comment '删除时间'
)
comment '职业信息分类表';
*/

    private Integer profNum;
    private Integer profLevel;
    private String profCode;
    private String profParentCode;
    private String profChn;
    private String profEng;
    private Timestamp startTime;

    public Integer getProfLevel() {
        return profLevel;
    }

    public void setProfLevel(Integer profLevel) {
        this.profLevel = profLevel;
    }

    public Integer getProfNum() {
        return profNum;
    }

    public void setProfNum(Integer profNum) {
        this.profNum = profNum;
    }

    public String getProfCode() {
        return profCode;
    }

    public void setProfCode(String profCode) {
        this.profCode = profCode;
    }

    public String getProfParentCode() {
        return profParentCode;
    }

    public void setProfParentCode(String profParentCode) {
        this.profParentCode = profParentCode;
    }

    public String getProfChn() {
        return profChn;
    }

    public void setProfChn(String profChn) {
        this.profChn = profChn;
    }

    public String getProfEng() {
        return profEng;
    }

    public void setProfEng(String profEng) {
        this.profEng = profEng;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
}
