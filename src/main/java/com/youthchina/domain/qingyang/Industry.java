package com.youthchina.domain.qingyang;

import java.sql.Timestamp;

/*行业编号		IND_NUM	        INTEGER	    否	行业分类编号
行业中文名称		IND_CHN	        VARCHAR(100)否
行业英文名称		IND_ENG	        VARCHAR(100)否
行业分类级别		IND_LEVEL	    INTEGER	    否
上级行业代码		IND_PARENT_NUM	INTEGER	    否
启用时间		    START_DATE	    TIMESTAMP	否	系统时间戳
是否删除			IS_DELETE	    INTEGER		否	0-默认不删除
删除时间(可空)	IS_DELETE_TIME	TIMESTAMP	是	*/
public class Industry {
    private Integer indNum;
    private String indChn; //中文名
    private String indEng;
    private Integer indLevel;
    private Integer indParentNum;
    private Timestamp startDate;
    private Integer isDelete;
    private Timestamp isDeleteTime;
    private Integer companyId;

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

    public Integer getIndParentNum() {
        return indParentNum;
    }

    public void setIndParentNum(Integer indParentNum) {
        this.indParentNum = indParentNum;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
