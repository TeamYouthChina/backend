package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.AnswerMapper;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.tianjian.RichTextService;
import com.youthchina.service.zhongyang.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    UserService userService;

    @Override
    public void isAnswerExist(Integer id) throws NotFoundException{
        Integer cur = answerMapper.checkIfAnswerExist(id);
        if(cur == null){
            throw new NotFoundException(404,404,"该问题不存在");
        }
    }
    @Override
    @Transactional
    public Answer get(Integer id) throws NotFoundException {
        Answer answer = answerMapper.get(id);
        if(answer == null){
            throw new NotFoundException(404,404,"没有找到这个回答");
        }
        answer.setUser(userService.get(answer.getUserId()));
        answer.setRichText(richTextService.getComRichText(id,4));
        answer.setQuestion(questionService.getBasicQuestion(answer.getTargetId()));
        return answer;
    }

    @Override
    @Transactional
    public List<Answer> getAnswers(Integer id) throws NotFoundException{
        List<Answer> answers = answerMapper.getAnswers(id);
        if(answers.size() == 0){
            throw new NotFoundException(404,404,"这个问题没有回答");
        }
        for(Answer answer : answers){
            answer.setUser(userService.get(answer.getUserId()));
            answer.setRichText(richTextService.getComRichText(answer.getId(),4));
        }
        return answers;
    }

    @Override
    @Transactional
    public Answer add(Answer answer) throws NotFoundException {
        questionService.isQuestionExist(answer.getTargetId());
        answerMapper.add(answer);
        richTextService.addComRichText(answer.getRichText());
        return answer;
    }

    @Override
    @Transactional
    public Answer update(Answer answer) throws NotFoundException {
        isAnswerExist(answer.getId());
        answerMapper.update(answer);
        richTextService.updateComRichText(answer.getRichText());
        return answer;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        //todo
    }

    @Override
    public List<Answer> get(List<Integer> id) throws NotFoundException {
        return null;
    }

}
