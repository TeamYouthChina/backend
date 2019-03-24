package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.CommentMapper;
import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.property.Commentable;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentMapper commentMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public void getComments(Commentable entity) {
        List<Comment> comments = commentMapper.getComments(entity.getCommentTargetType(), entity.getId());
        for(Comment comment : comments){
            comment.setUser(userMapper.findOne(comment.getUserId()));
        }
        entity.setComments(comments);
    }


    @Override
    public Comment add(Comment comment) {
        commentMapper.add(comment);
        return comment;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        //todo
    }


    @Override
    public Comment get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Comment> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public Comment update(Comment comment) throws NotFoundException {
        return null;
    }
}
