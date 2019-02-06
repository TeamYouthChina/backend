package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.PersonInfluenceMapper;
import com.youthchina.domain.tianjian.PersonInfluence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PersonInfluenceServiceImpl")
public class PersonInfluenceServiceImpl implements PersonInfluenceService {
    @Autowired
    PersonInfluenceMapper personInfluenceMapper;
    @Override
    public int savePersonInfluence(PersonInfluence personInfluence) {
        personInfluenceMapper.savePersonInfluence(personInfluence);
        return 0;
    }


    @Override
    public int updatePersonInfluence(PersonInfluence personInfluence) {
        personInfluenceMapper.updatePersonInfluence(personInfluence);
        return 0;
    }

    @Override
    public int getPersonInfluence(Integer user_id) {
        personInfluenceMapper.getPersonInfluence(user_id);
        return 0;
    }
}
