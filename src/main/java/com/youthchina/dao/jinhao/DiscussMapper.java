package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.Discuss;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DiscussMapper {
    List<Discuss> getDiscusses(Integer id);
    List<Discuss> getLimitedDiscusses(@Param("id") Integer id, @Param("start") Integer start, @Param("rows") Integer rows);
    Discuss get(Integer id);
    void add(Discuss discuss);
    void delete(Integer id);
    Integer checkIfDiscussExist(Integer id);
    void deleteAllDiscussOfComment(Integer id);
    Integer count(Integer id);
    List<Discuss> getMyDiscuss(Integer id);
}
