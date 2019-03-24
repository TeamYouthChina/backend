package com.youthchina.domain.qingyang;

import java.sql.Timestamp;

/**
 * @author: Qingyang Zhao
 * @create: 2019-03-24
 **/
public class CompanyEvaluate {
    private Integer evaluId;
    private Integer targetType;
    private Integer targetId;
    private Integer userId;
    private Integer evaluType;
    private Timestamp evaluTime;

    public Integer getEvaluId() {
        return evaluId;
    }

    public void setEvaluId(Integer evaluId) {
        this.evaluId = evaluId;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEvaluType() {
        return evaluType;
    }

    public void setEvaluType(Integer evaluType) {
        this.evaluType = evaluType;
    }

    public Timestamp getEvaluTime() {
        return evaluTime;
    }

    public void setEvaluTime(Timestamp evaluTime) {
        this.evaluTime = evaluTime;
    }
}
