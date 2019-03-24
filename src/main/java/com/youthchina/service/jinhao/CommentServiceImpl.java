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

    @Resource
    BriefReviewService briefReviewService;

    @Resource
    VideoService videoService;

    @Resource
    AnswerService answerService;

    @Override
    public void getComments(Commentable entity) {
        List<Comment> comments = commentMapper.getComments(entity.getCommentTargetType(), entity.getId());
        for(Comment comment : comments){
            comment.setUser(userMapper.findOne(comment.getUserId()));
        }
        entity.setComments(comments);
    }

    @Override
    public void isCommentExist(Integer id) throws NotFoundException {
        if(commentMapper.checkIfCommentExist(id) == null){
            throw new NotFoundException(404,404,"该评论不存在");
        }
    }

    @Override
    public Comment add(Comment comment, Commentable entity) throws NotFoundException{
        Integer type = entity.getCommentTargetType();
        Integer targetId = entity.getId();
        switch (type){
            case 2: briefReviewService.isBriefReviewExist(targetId); break;
            case 3: videoService.isVideoExist(targetId); break;
            case 4: answerService.isAnswerExist(targetId); break;
            default:
                throw new NotFoundException(404,404,"No such type");
        }
        comment.setTargetType(type);
        comment.setTargetId(targetId);
        commentMapper.add(comment);
        return comment;
    }


    @Override
    public Comment add(Comment comment) {
        return null;
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
