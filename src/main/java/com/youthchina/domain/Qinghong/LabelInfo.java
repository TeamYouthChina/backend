package com.youthchina.domain.Qinghong;

import java.util.Date;

/**
 * @program: youthchina
 * @description: 标签表的添加
 * @author: Qinghong Wang
 * @create: 2019-01-27 16:53
 **/
public class LabelInfo {
    private Integer label_id;
    private Integer label_level;
    private String label_code;
    private String label_parent_code;
    private String label_chn;
    private String label_eng;
    private Date start_time;
    private Integer is_delete;
    private Integer is_delete_time;

    public Integer getLabel_id() {
        return label_id;
    }

    public void setLabel_id(Integer label_id) {
        this.label_id = label_id;
    }

    public Integer getLabel_level() {
        return label_level;
    }

    public void setLabel_level(Integer label_level) {
        this.label_level = label_level;
    }

    public String getLabel_code() {
        return label_code;
    }

    public void setLabel_code(String label_code) {
        this.label_code = label_code;
    }

    public String getLabel_parent_code() {
        return label_parent_code;
    }

    public void setLabel_parent_code(String label_parent_code) {
        this.label_parent_code = label_parent_code;
    }

    public String getLabel_chn() {
        return label_chn;
    }

    public void setLabel_chn(String label_chn) {
        this.label_chn = label_chn;
    }

    public String getLabel_eng() {
        return label_eng;
    }

    public void setLabel_eng(String label_eng) {
        this.label_eng = label_eng;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Integer getIs_delete_time() {
        return is_delete_time;
    }

    public void setIs_delete_time(Integer is_delete_time) {
        this.is_delete_time = is_delete_time;
    }
}
