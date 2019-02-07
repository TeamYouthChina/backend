package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.communityQA.PersonInfluence;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface InfluenceMapper {
    PersonInfluence getInfluenceByUserId(Integer user_id);
}
