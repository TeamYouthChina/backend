package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.Discuss;

import java.util.List;

public interface DiscussMapper {
    List<Discuss> getDiscusses(Integer id);
    void add(Discuss discuss);
    void delete(Integer id);
}
