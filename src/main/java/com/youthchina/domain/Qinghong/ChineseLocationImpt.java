package com.youthchina.domain.Qinghong;

import com.youthchina.dao.Qinghong.ApplicantMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: V-0.1
 * @description: 对于中国地区接口的实现
 * @author: Qinghong Wang
 * @create: 2019-01-22 10:30
 **/
public class ChineseLocationImpt implements Location.ChineseLocation {
    @Autowired
    ApplicantMapper applicantMapper;

    @Override
    public String toString(Integer location_num) {
        return null;
    }
}
