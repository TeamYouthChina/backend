package com.youthchina.dao.tianjian;

import com.youthchina.domain.Qinghong.UserInfo;
import com.youthchina.domain.tianjian.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by zhongyangwu on 11/10/18.
 */
@Mapper
@Component
public interface PersonInfluenceMapper {
   int savePersonInfluence(PersonInfluencePoint personInfluencePoint);

   int updatePersonInfluence(PersonInfluencePoint personInfluencePoint);

   PersonInfluencePoint getPersonInfluence(Integer user_id);

   UserInfo getUserInfo(Integer user_id);
}
