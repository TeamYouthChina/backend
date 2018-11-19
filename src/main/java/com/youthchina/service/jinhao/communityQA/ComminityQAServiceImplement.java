package com.youthchina.service.jinhao.communityQA;

import com.youthchina.dao.jinhao.CommunityQAMapper;
import com.youthchina.domain.jinhao.communityQA.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class ComminityQAServiceImplement implements CommunityQAService {
    @Resource
    CommunityQAMapper communityQAMapper;

    @Override
    @Transactional
    public List<QuestionAndPopAnswer> listAllQuestionAndPopAnswer() {
        List<Question> questions = listQuestion();
        List<QuestionAndPopAnswer> res = new LinkedList<>();
        for  (Question question : questions) {
            List<QuestionAnswer> answers = listAllAnswer(question.getQues_id());
            int max = 0;
            QuestionAnswer popAnswer = null;
            for (QuestionAnswer answer: answers) {
                int cur = countAgreement(answer.getAnswer_id());
                if(cur >= max){
                    max = cur;
                    popAnswer = answer;
                }
            }
            StuInfo stuInfo = null;
            if(popAnswer != null){
                int user_id = popAnswer.getUser_id();
                stuInfo = getStuInfo(user_id);
            }

            QuestionAndPopAnswer questionAndPopAnswer = new QuestionAndPopAnswer(question, popAnswer, stuInfo, max);
            res.add(questionAndPopAnswer);
        }
        return res;
    }


    /**
     * 添加问题，建立问题和用户的映射，建立问题和标签的映射
     * @param question 要添加的问题的对象
     * @param user_id 提出问题的用户的id
     * @param labels 添加的标签list
     * @return 添加成功返回1
     */
    @Override
    @Transactional
    public Integer addQuestion(Question question, Integer user_id, List<Label> labels) {
        communityQAMapper.addQuestion(question);
        communityQAMapper.addLabels(labels, question.getQues_id());
        return communityQAMapper.createMapBetweenQuestionAndUser(question.getQues_id(), user_id);
    }

    /**
     * 拿到某个问题的详细信息
     * @param ques_id 问题id
     * @return 返回一个question对象
     */
    @Override
    public Question getQuestion(Integer ques_id) {
        return communityQAMapper.getQuestion(ques_id);
    }

    /**
     * 拿到某个问题的标签
     * @param ques_id 问题id
     * @return 返回一个包含问题所有标签的list
     */
    @Override
    public List<Label> getLabels(Integer ques_id) {
        return communityQAMapper.listAllQuesetionLabel(ques_id);
    }

    /**
     * 编辑某个问题
     * @param question 要编辑的问题的对象
     * @return 找不到问题不操作，返回0；更新成功返回1
     */
    @Override
    @Transactional
    public Integer updateQuestion(Question question) {
        if(getQuestion(question.getQues_id()) != null) {
            return communityQAMapper.editQuestion(question);
        }else {
            return 0;
        }
    }

    /**
     * 删除某个问题: 更新问题表的是否删除字段为1,同时会删除问题和标签的映射
     * @param question 要删除的问题对象
     * @return 找不到问题不操作，返回0；删除成功返回1
     */
    @Override
    @Transactional
    public Integer deleteQuesiton(Question question) {
        if(getQuestion(question.getQues_id()) != null) {
            communityQAMapper.deleteQuestionLabel(question.getQues_id());
            return communityQAMapper.deleteQuestion(question);
        }else {
            return 0;
        }
    }

    /**
     * 拿到某个问题的所有回答
     * @param ques_id 问题的id
     * @return 问题不存在返回null, 存在则返回包含所有回答的list
     */
    @Override
    public List<QuestionAnswer> listAllAnswer(Integer ques_id) {
        if(getQuestion(ques_id) != null){
            return communityQAMapper.listAllQuestionAnswer(ques_id);
        }else {
            return null;
        }
    }

    /**
     * 添加回答，建立回答和问题的映射
     * @param questionAnswer 要添加的回答的对象
     * @param ques_id 要回答的问题的id
     * @return 添加成功返回1
     */
    @Override
    @Transactional
    public Integer addAnswer(QuestionAnswer questionAnswer, Integer ques_id) {
        communityQAMapper.addAnswerToQuestion(questionAnswer);
        return communityQAMapper.createMapBetweenQuestionAndAnswer(ques_id, questionAnswer.getAnswer_id());
    }

    /**
     * 根据answer_id拿到指定的回答信息
     * @param answer_id 回答的id
     * @return 返回一个QuestionAnswer对象
     */
    @Override
    public QuestionAnswer getAnswer(Integer answer_id) {
        return communityQAMapper.getAnswer(answer_id);
    }

    /**
     * 编辑回答
     * @param questionAnswer 要编辑的回答对象
     * @return 如果回答不存在，不操作，返回0； 编辑成功返回1
     */
    @Override
    @Transactional
    public Integer editAnswer(QuestionAnswer questionAnswer) {
        if(getAnswer(questionAnswer.getAnswer_id()) != null){
            return communityQAMapper.editAnswer(questionAnswer);
        }else {
            return 0;
        }
    }

    /**
     * 删除回答
     * @param questionAnswer 要删除的回答对象
     * @return 如果回答不存在，不操作，返回0； 删除成功返回1
     */
    @Override
    @Transactional
    public Integer deleteAnswer(QuestionAnswer questionAnswer) {
        if(getAnswer(questionAnswer.getAnswer_id()) != null){
            return communityQAMapper.deleteAnswer(questionAnswer);
        }else{
            return 0;
        }
    }

    /**
     * 检查是否关注过问题
     * @param user_id 用户id
     * @param ques_id 问题id
     * @return 如果关注过这个问题返回一个有内容的关注对象，否则返回null
     */
    @Override
    public QuestionAttention isQuestionAttention(Integer user_id, Integer ques_id) {
        return communityQAMapper.isQuestionAttention(user_id,ques_id);
    }

    /**
     * 关注问题，先检查问题是否存在，不存在则不操作，返回0；如果该用户没有关注过这个问题，则插入关注，并且建立关注和问题的映射，否则直接
     * 更新关注表的字段
     * @param ques_id 被关注的问题id
     * @param questionAttention 关注问题对象
     * @return 关注成功返回1
     */
    @Override
    @Transactional
    public Integer attentionQuestion(Integer ques_id, QuestionAttention questionAttention) {
        if(getQuestion(ques_id) != null){
            if(isQuestionAttention(questionAttention.getUser_id(), ques_id) == null){
                communityQAMapper.addAttentionToQuestion(questionAttention);
                return communityQAMapper.createMapBetweenAttentionAndQuestion(ques_id, questionAttention);
            }else{
                return communityQAMapper.reAddAttentionToQuestion(questionAttention);
            }
        }else {
            return 0;
        }
    }

    /**
     * 拿到某个回答的点赞数
     * @param answer_id 回答的id
     * @return 返回回答的点赞数
     */
    @Override
    public Integer countAgreement(Integer answer_id) {
        return communityQAMapper.countAgreement(answer_id);
    }

    /**
     * 根据角色id拿到对应的用户信息
     * @param user_id 角色id
     * @return 返回一个用户信息的对象
     */
    @Override
    public StuInfo getStuInfo(Integer user_id) {
        return communityQAMapper.getStuInfo(user_id);
    }

    /**
     * 拿到前10个问题的基本信息，问答板块主页要用
     * @return 返回问题对象的list
     */
    @Override
    public List<Question> listQuestion() {
        return communityQAMapper.listQuestion();
    }
}
