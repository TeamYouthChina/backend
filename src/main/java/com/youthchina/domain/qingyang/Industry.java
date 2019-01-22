package com.youthchina.domain.qingyang;

import java.sql.Timestamp;

/*
create table SYS_IND_CLASS
(
	IND_NUM int auto_increment comment '行业编号'
		primary key,
	IND_CODE varchar(10) not null comment '行业代码',
	IND_CHN varchar(100) not null comment '行业中文名称',
	IND_ENG varchar(100) not null comment '行业英文名称',
	IND_LEVEL int not null comment '行业分类级别',
	IND_PARENT_CODE varchar(10) not null comment '上级行业代码',
	START_TIME timestamp not null comment '启用时间',
	IS_DELETE int default 0 null comment '是否删除',
	IS_DELETE_TIME timestamp null comment '删除时间'
)
comment '行业信息分类表';
*/
public class Industry {
    private Integer indNum;
    private String indCode;
    private String indChn; //中文名
    private String indEng;
    private Integer indLevel;
    private String indParentCode;
    private Timestamp startTime;
    private Integer isDelete;
    private Timestamp isDeleteTime;

    public Industry(){

    }

    public Integer getIndNum() {
        return indNum;
    }

    public void setIndNum(Integer indNum) {
        this.indNum = indNum;
    }

    public String getIndChn() {
        return indChn;
    }

    public void setIndChn(String indChn) {
        this.indChn = indChn;
    }

    public String getIndEng() {
        return indEng;
    }

    public void setIndEng(String indEng) {
        this.indEng = indEng;
    }

    public Integer getIndLevel() {
        return indLevel;
    }

    public void setIndLevel(Integer indLevel) {
        this.indLevel = indLevel;
    }

    public String getIndParentCode() {
        return indParentCode;
    }

    public void setIndParentCode(String indParentCode) {
        this.indParentCode = indParentCode;
    }

    public String getIndCode() {
        return indCode;
    }

    public void setIndCode(String indCode) {
        this.indCode = indCode;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
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
