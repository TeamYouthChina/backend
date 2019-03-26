package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.DiscussMapper;
import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

public class DiscussServiceImpl implements DiscussService{
    @Resource
    DiscussMapper discussMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    CommentService commentService;

    @Override
    @Transactional
    public List<Discuss> getDiscusses(Integer id) throws NotFoundException {
        List<Discuss> discusses = discussMapper.getDiscusses(id);
        if(discusses.size() == 0){
            throw new NotFoundException(404,404,"该评论没有讨论");
        }
        for(Discuss discuss : discusses){
            discuss.setUser(userMapper.findOne(discuss.getUserId()));
        }
        return discusses;
    }

    @Override
    public void isDiscussExist(Integer id) throws NotFoundException {
        if(discussMapper.checkIfDiscussExist(id) == null){
            throw new NotFoundException(404,404,"没有找到这个讨论");
        }
    }

    @Override
    public void deleteAllDiscussOfComment(Integer id) {
        discussMapper.deleteAllDiscussOfComment(id);
    }

    @Override
    @Transactional
    public Discuss add(Discuss discuss) throws NotFoundException {
        commentService.isCommentExist(discuss.getCommentId());
        discussMapper.add(discuss);
        return discuss;
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
        discussMapper.delete(id);
    }

    @Override
    public Discuss update(Discuss discuss) throws NotFoundException {
        return null;
    }
}
