package com.youthchina.domain.qingyang;

import com.youthchina.util.zhongyang.HasId;

/**
 * Created by zhongyangwu on 11/21/18.
 */
public class Student implements HasId<Integer> {
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
