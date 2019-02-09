package com.youthchina.service.tianjian;

import com.youthchina.domain.tianjian.PersonInfluence;

public interface PersonInfluenceService {
    public int savePersonInfluence(PersonInfluence personInfluence);

    public int updatePersonInfluence(PersonInfluence personInfluence);

    public int getPersonInfluence(Integer user_id);
}
