package com.youthchina.service.community;

import com.youthchina.domain.jinhao.property.IsExist;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IsExistServiceImpl implements IsExistService {

    @Resource
    QuestionService questionService;

    @Resource
    AnswerService answerService;

    @Resource
    EssayService essayService;

    @Resource
    BriefReviewService briefReviewService;

    @Resource
    VideoService videoService;

    @Resource
    CommentService commentService;

    @Resource
    DiscussService discussService;
    @Override
    public void isExist(IsExist entity) throws NotFoundException {
        Integer type = entity.getExistType();
        Integer id = entity.getId();
        switch (type) {
            case 1: questionService.get(id); break;
            case 2: answerService.get(id); break;
            case 3: essayService.get(id); break;
            case 4: briefReviewService.get(id); break;
            case 5: videoService.get(id); break;
            case 6: commentService.get(id); break;
            case 7: discussService.get(id); break;
        }
    }
}
