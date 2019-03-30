package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.Discuss;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DiscussMapper {
    List<Discuss> getDiscusses(Integer id);
    Discuss get(Integer id);
    void add(Discuss discuss);
    void delete(Integer id);
    Integer checkIfDiscussExist(Integer id);
    void deleteAllDiscussOfComment(Integer id);
    Integer count(Integer id);
}
