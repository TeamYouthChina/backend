package com.youthchina.service.tianjian;

import com.youthchina.domain.tianjian.PersonInfluencePoint;

public interface PersonInfluenceService {
    public int savePersonInfluence(PersonInfluencePoint personInfluencePoint);

    public int updatePersonInfluence(PersonInfluencePoint personInfluencePoint);

    public int getPersonInfluence(Integer user_id);
}
