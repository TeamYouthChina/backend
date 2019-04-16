package com.youthchina.service.community;

import com.youthchina.dao.jinhao.CommentMapper;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.domain.jinhao.property.Commentable;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    CommentMapper commentMapper;

    @Resource
    BriefReviewService briefReviewService;

    @Resource
    VideoService videoService;

    @Resource
    AnswerService answerService;

    @Resource
    DiscussService discussService;

    @Resource
    EssayService essayService;

    @Resource
    UserService userService;

    @Override
    public List<Comment> getComments(Commentable entity) {
        List<Comment> comments = commentMapper.getComments(entity.getCommentTargetType(), entity.getId());
        for (Comment comment : comments) {
            try {
                comment.setUser(userService.get(comment.getUser().getId()));
            } catch (NotFoundException e) {
            }
        }
        return comments;
    }

    @Override
    public List<Comment> getComments(Commentable entity, Integer start, Integer end) {
        List<Comment> comments = commentMapper.getLimitedComments(entity.getCommentTargetType(), entity.getId(), start, end - start + 1);
        for (Comment comment : comments) {
            try {
                comment.setUser(userService.get(comment.getUser().getId()));
            } catch (NotFoundException e) {
            }
        }
        return comments;
    }

    @Override
    public void isCommentExist(Integer id) throws NotFoundException {
        if (commentMapper.checkIfCommentExist(id) == null) {
            throw new NotFoundException(404, 404, "该评论不存在");
        }
    }

    @Override
    @Transactional
    public Comment add(Comment comment, Commentable entity) throws NotFoundException {
        Integer type = entity.getCommentTargetType();
        Integer targetId = entity.getId();
        switch (type) {
            case 1:
                essayService.get(targetId);
                break;
            case 2:
                briefReviewService.isBriefReviewExist(targetId);
                break;
            case 3:
                videoService.isVideoExist(targetId);
                break;
            case 4:
                answerService.isAnswerExist(targetId);
                break;
            default:
                throw new NotFoundException(404, 404, "No such type");
        }
        comment.setTargetType(type);
        comment.setTargetId(targetId);
        commentMapper.add(comment);
        return comment;
    }

    @Override
    @Transactional
    public void delete(Commentable entity) {
        List<Comment> comments = getComments(entity);
        for (Comment comment : comments) {
            discussService.deleteAllDiscussOfComment(comment.getId());
        }
        commentMapper.deleteComments(entity.getCommentTargetType(), entity.getId());
    }

    @Override
    public Integer countComments(Commentable entity) {
        return commentMapper.count(entity.getCommentTargetType(), entity.getId());
    }

    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        isCommentExist(id);
        List<Discuss> discusses = discussService.getDiscussesByCommentId(id);
        commentMapper.delete(id);
    }


    @Override
    @Transactional
    public Comment get(Integer id) throws NotFoundException {
        Comment comment = commentMapper.get(id);
        if (comment == null) {
            throw new NotFoundException(404, 404, "This comment dose not exist!");
        }
        comment.setUser(userService.get(comment.getUser().getId()));
        return comment;
    }

}
