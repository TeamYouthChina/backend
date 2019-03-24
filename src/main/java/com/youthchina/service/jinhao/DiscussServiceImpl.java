package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.DiscussMapper;
import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.exception.zhongyang.NotFoundException;

import javax.annotation.Resource;
import java.util.List;

public class DiscussServiceImpl implements DiscussService{
    @Resource
    DiscussMapper discussMapper;

    @Override
    public List<Discuss> getDiscusses(Integer id) throws NotFoundException {
        List<Discuss> discusses = discussMapper.getDiscusses(id);
        if(discusses.size() == 0){
            throw new NotFoundException(404,404,"该评论没有讨论");
        }
        return discusses;
    }

    @Override
    public Discuss get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Discuss> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Discuss update(Discuss discuss) throws NotFoundException {
        return null;
    }

    @Override
    public Discuss add(Discuss entity) {
        return null;
    }
}
