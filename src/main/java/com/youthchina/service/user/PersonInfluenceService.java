package com.youthchina.service.user;

import com.youthchina.domain.tianjian.PersonInfluence;
import com.youthchina.exception.zhongyang.exception.NotFoundException;

public interface PersonInfluenceService {
    public int savePersonInfluence(PersonInfluence personInfluence);

    public int updatePersonInfluence(PersonInfluence personInfluence) throws NotFoundException;

    public PersonInfluence getPersonInfluence(Integer user_id) throws NotFoundException;
}
