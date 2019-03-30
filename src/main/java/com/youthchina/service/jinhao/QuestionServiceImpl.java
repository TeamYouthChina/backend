package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.QuestionMapper;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.tianjian.RichTextService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    QuestionMapper questionMapper;

    @Resource
    RichTextService richTextService;

    @Resource
    AnswerService answerService;

    @Override
    public void isQuestionExist(Integer id) throws NotFoundException{
        if(questionMapper.checkIfQuestionExist(id) == null){
            throw new NotFoundException(404,404,"The question does not exist");
        }

    }

    @Override
    @Transactional
    public Question getBasicQuestion(Integer id) throws NotFoundException {
        Question question = questionMapper.get(id);
        if(question == null){
            throw new NotFoundException(404,404,"没有找到这个问题");//todo
        }
        richTextService.getComRichText(question);
        return question;
    }

    @Override
    public Integer count() {
        return questionMapper.count();
    }

    @Override
    @Transactional
    public Question get(Integer id) throws NotFoundException {
        Question question = questionMapper.get(id);
        if(question == null){
            throw new NotFoundException(404,404,"没有找到这个问题");//todo
        }
        richTextService.getComRichText(question);
        question.setAnswers(answerService.getAnswers(id));
        return question;
    }

    @Override
    @Transactional
    public List<Question> get(List<Integer> id) throws NotFoundException{
        List<Question> questions = new LinkedList<>();
        for(Integer one : id){
            Question question = questionMapper.get(one);
            if(question != null){
                richTextService.getComRichText(question);
                question.setAnswers(answerService.getAnswers(one));
                questions.add(question);
            }
        }
        if(questions.size() == 0){
            throw new NotFoundException(404,4040,"没有根据这些id找到问题");
        }
        return questions;
    }

    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        isQuestionExist(id);
        List<Answer> answers = answerService.getAnswers(id);
        for(Answer answer : answers){
            answerService.delete(answer.getId());
        }
        questionMapper.delete(id);
    }

    @Override
    @Transactional
    public Question update(Question question) throws NotFoundException {
        isQuestionExist(question.getId());
        questionMapper.edit(question);
        richTextService.updateComRichText(question.getBody());
        return question;
    }

    @Override
    @Transactional
    public Question add(Question entity) {
        questionMapper.add(entity);
        richTextService.addComRichText(entity.getBody());
        return entity;
    }
}
