package com.youthchina.service.community;

import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.exception.zhongyang.exception.NotFoundException;

import java.util.List;

public interface DiscussService{
    Discuss add(Discuss discuss) throws NotFoundException;

    void delete(Integer id) throws NotFoundException;

    Discuss get(Integer id) throws NotFoundException;

    List<Discuss> getDiscussesByCommentId(Integer id);

    List<Discuss> getDiscussesByCommentId(Integer id, Integer start, Integer limit);

    Integer count(Integer id);
}
