package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.AnswerMapper;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.tianjian.RichTextService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{
    @Resource
    AnswerMapper answerMapper;

    @Resource
    RichTextService richTextService;

    @Resource
    QuestionService questionService;

    @Resource
    CommentService commentService;

    @Override
    public void isAnswerExist(Integer id) throws NotFoundException{
        Integer cur = answerMapper.checkIfAnswerExist(id);
        if(cur == null){
            throw new NotFoundException(404,404,"该问题不存在");
        }
    }

    @Override
    public Integer countAnswersOfQuestion(Integer id) {
        return answerMapper.countAnswers(id);
    }

    @Override
    @Transactional
    public Answer get(Integer id) throws NotFoundException {
        Answer answer = answerMapper.get(id);
        if(answer == null){
            throw new NotFoundException(404,404,"没有找到这个回答");
        }
        richTextService.getComRichText(answer);
        answer.setQuestion(questionService.getBasicQuestion(answer.getTargetId()));
        return answer;
    }

    @Override
    @Transactional
    public List<Answer> getAnswers(Integer id, int start, int end){
        List<Answer> answers = answerMapper.getLimitedAnswers(id, start, start-end+1);
        for(Answer answer : answers){
            richTextService.getComRichText(answer);
        }
        return answers;
    }

    @Override
    @Transactional
    public List<Answer> getAnswers(Integer id) {
        List<Answer> answers = answerMapper.getAnswers(id);
        for(Answer answer : answers){
            richTextService.getComRichText(answer);
        }
        return answers;
    }

    @Override
    @Transactional
    public Answer add(Answer answer) throws NotFoundException {
        questionService.isQuestionExist(answer.getTargetId());
        richTextService.addComRichText(answer.getBody());
        answerMapper.add(answer);
        return answer;
    }

    @Override
    @Transactional
    public Answer update(Answer answer) throws NotFoundException {
        isAnswerExist(answer.getId());
        answerMapper.update(answer);
        Answer answer1 = get(1);
        answer.getBody().setTextId(answer1.getBody().getTextId());
        richTextService.updateComRichText(answer.getBody());
        return get(answer.getId());
    }

    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        isAnswerExist(id);
        Answer answer = new Answer();
        answer.setId(id);
        commentService.delete(answer);
        answerMapper.delete(id);
    }

    @Override
    public List<Answer> get(List<Integer> id) throws NotFoundException{
        List<Answer> answers = new LinkedList<>();
        for(Integer one : id){
            Answer answer = answerMapper.get(one);
            if(answer != null){
                try {
                    answer.setQuestion(questionService.getBasicQuestion(answer.getTargetId()));
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
                answers.add(answer);
            }
        }
        return answers;
    }

}
