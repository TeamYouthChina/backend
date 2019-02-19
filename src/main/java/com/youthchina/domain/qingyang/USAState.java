package com.youthchina.domain.qingyang;

import java.sql.Timestamp;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-19
 **/
public class USAState {
    private Integer stateId;
    private String stateChn;
    private String stateEng;
    private String stateAbbre;
    private Timestamp startTime;

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateChn() {
        return stateChn;
    }

    public void setStateChn(String stateChn) {
        this.stateChn = stateChn;
    }

    public String getStateEng() {
        return stateEng;
    }

    public void setStateEng(String stateEng) {
        this.stateEng = stateEng;
    }

    public String getStateAbbre() {
        return stateAbbre;
    }

    public void setStateAbbre(String stateAbbre) {
        this.stateAbbre = stateAbbre;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
}
