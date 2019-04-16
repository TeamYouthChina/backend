package com.youthchina.service.community;

import com.youthchina.dao.jinhao.QuestionMapper;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Resource
    QuestionMapper questionMapper;

    @Resource
    RichTextService richTextService;

    @Resource
    AnswerService answerService;

    @Resource
    UserService userService;

    @Resource
    EvaluateService evaluateService;

    @Resource
    AttentionService attentionService;

    @Override
    @Transactional
    public Question getBasicQuestion(Integer id){
        Question question = questionMapper.get(id);
        if(question == null) return null;
        try {
            question.setUser(userService.get(question.getUser().getId()));
        } catch (NotFoundException e) {

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
        if (question == null) {
            throw new NotFoundException(4040, 404, "没有找到这个问题");//todo
        }
        question.setUser(userService.get(question.getUser().getId()));
        richTextService.getComRichText(question);
        question.setAnswers(answerService.getAnswers(id));
        return question;
    }

    @Override
    @Transactional
    public List<Question> get(Integer relaType, Integer relaId){
        List<Question> questions = questionMapper.getListQuestions(relaType, relaId);
        List<Question> questionsReturn = new ArrayList<>();
        Iterator it = questions.iterator();
        while(it.hasNext()){
            Question question = (Question) it.next();
            richTextService.getComRichText(question);
            question.setAnswers(answerService.getAnswers(question.getId()));
            try {
                question.setUser(userService.get(question.getUser().getId()));
            } catch (NotFoundException e) {

            }
            questionsReturn.add(question);
        }
        return questionsReturn;
    }

    @Override
    public List<Question> getMyQuestion(Integer id) {
        List<Question> questions = questionMapper.getMyQuestion(id);
        for(Question question : questions){
            try {
                question.setUser(userService.get(question.getUser().getId()));
            } catch (NotFoundException e) {

            }
            richTextService.getComRichText(question);
        }
        return questions;
    }

    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        get(id);
        List<Answer> answers = answerService.getAnswers(id);
        for(Answer answer : answers){
            try {
                answerService.delete(answer.getId());
            } catch (NotFoundException e) {

            }
        }
        Question question = new Question();
        question.setId(id);
        evaluateService.cancel(question);
        attentionService.cancel(question);
        questionMapper.delete(id);
    }

    @Override
    @Transactional
    public Question update(Question question) throws NotFoundException {
        get(question.getId());
        questionMapper.edit(question);
        Question question1 = get(question.getId());
        question.getBody().setTextId(question1.getBody().getTextId());
        richTextService.updateComRichText(question.getBody());
        return question;
    }

    @Override
    @Transactional
    public Question add(Question entity) throws NotFoundException {
        richTextService.addComRichText(entity.getBody());
        questionMapper.add(entity);
        return get(entity.getId());
    }
}
