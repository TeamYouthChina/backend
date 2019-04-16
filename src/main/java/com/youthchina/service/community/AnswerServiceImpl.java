package com.youthchina.service.community;

import com.youthchina.dao.jinhao.AnswerMapper;
import com.youthchina.dao.jinhao.QuestionMapper;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Resource
    AnswerMapper answerMapper;

    @Resource
    RichTextService richTextService;

    @Resource
    QuestionService questionService;

    @Resource
    CommentService commentService;

    @Resource
    UserService userService;

    @Resource
    AttentionService attentionService;

    @Resource
    EvaluateService evaluateService;

    @Resource
    QuestionMapper questionMapper;

    @Override
    public Integer countAnswersOfQuestion(Integer id) {
        return answerMapper.countAnswers(id);
    }

    @Override
    public List<Answer> getMyAnswers(Integer id) {
        List<Answer> answers = answerMapper.getMyAnswers(id);
        for(Answer answer : answers){
            try {
                answer.setUser(userService.get(answer.getUser().getId()));
            } catch (NotFoundException e) {

            }
            richTextService.getComRichText(answer);
            answer.setQuestion(questionService.getBasicQuestion(answer.getTargetId()));
        }
        return answers;
    }

    @Override
    @Transactional
    public Answer get(Integer id) throws NotFoundException {
        Answer answer = answerMapper.get(id);
        if(answer == null){
            throw new NotFoundException(4040,404,"This answer does not exist!");//todo
        }
        answer.setUser(userService.get(answer.getUser().getId()));
        richTextService.getComRichText(answer);
        answer.setQuestion(questionService.getBasicQuestion(answer.getTargetId()));
        return answer;
    }

    @Override
    @Transactional
    public List<Answer> getAnswers(Integer id, int start, int end){
        List<Answer> answers = answerMapper.getLimitedAnswers(id, start, start-end+1);
        for(Answer answer : answers){
            try {
                answer.setUser(userService.get(answer.getUser().getId()));
            } catch (NotFoundException e) {

            }
            richTextService.getComRichText(answer);
        }
        return answers;
    }

    @Override
    @Transactional
    public List<Answer> getAnswers(Integer id) {
        List<Answer> answers = answerMapper.getAnswers(id);
        for(Answer answer : answers){
            try {
                answer.setUser(userService.get(answer.getUser().getId()));
            } catch (NotFoundException e) {

            }
            richTextService.getComRichText(answer);
        }
        return answers;
    }

    @Override
    @Transactional
    public Answer add(Answer answer) throws NotFoundException {
        get(answer.getId());
        richTextService.addComRichText(answer.getBody());
        answerMapper.add(answer);
        return get(answer.getId());
    }

    @Override
    @Transactional
    public Answer update(Answer answer) throws NotFoundException {
        get(answer.getId());
        answerMapper.update(answer);
        Answer answer1 = get(answer.getId());
        answer.getBody().setTextId(answer1.getBody().getTextId());
        richTextService.updateComRichText(answer.getBody());
        return get(answer.getId());
    }

    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {

        Answer answer = new Answer();
        answer.setId(id);
        commentService.delete(answer);
        attentionService.cancel(answer);
        evaluateService.cancel(answer);
        answerMapper.delete(id);
    }


}
