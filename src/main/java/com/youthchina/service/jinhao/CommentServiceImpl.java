package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.CommentMapper;
import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.property.Commentable;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Resource
    DiscussService discussService;

    @Override
    public List<Comment> getComments(Commentable entity) {
        List<Comment> comments = commentMapper.getComments(entity.getCommentTargetType(), entity.getId());
        for(Comment comment : comments){
            comment.setUser(userMapper.findOne(comment.getUserId()));
        }
        return comments;
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
    @Transactional
    public void delete(Commentable entity) {
        getComments(entity);
        List<Comment> comments = entity.getComments();
        for(Comment comment : comments){
            discussService.deleteAllDiscussOfComment(comment.getId());
        }
        commentMapper.deleteComments(entity.getCommentTargetType(), entity.getId());
    }


    @Override
    public Comment add(Comment comment) {
        return null;
    }

    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        isCommentExist(id);
        discussService.deleteAllDiscussOfComment(id);
        commentMapper.delete(id);
    }


    @Override
    public Comment get(Integer id) throws NotFoundException {
        Comment comment = commentMapper.get(id);
        if(comment == null){
            throw new NotFoundException(404,404,"没有找到这个评论");
        }
        return comment;
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
