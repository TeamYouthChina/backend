package com.youthchina.service.community;

import com.youthchina.domain.jinhao.property.IsExist;
import com.youthchina.exception.zhongyang.exception.NotFoundException;

public interface IsExistService {
    void isExist(IsExist entity) throws NotFoundException;
}
