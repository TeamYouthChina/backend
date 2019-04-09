package com.youthchina.dao.tianjian;

import com.youthchina.domain.qingyang.Logo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
/**
 * Created by zhongyangwu on 11/10/18.
 */
@Mapper
@Component
public interface LogoUploadMapper {
    void add(Logo logo);
    Logo get(Integer logoId);
    void update(Logo logo);
    void delete(Integer logoId);
}
