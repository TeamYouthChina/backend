package com.youthchina.service.jinhao.communityQA;

import com.youthchina.dao.jinhao.CommunityQAMapper;
import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Service
public class CommunityQAServiceImplement implements CommunityQAService {
    @Resource
    CommunityQAMapper communityQAMapper;

    @Override
    @Transactional
    public List<QuestionAndPopAnswer> listAllQuestionAndPopAnswer() throws NotFoundException{
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
        communityQAMapper.createMapBetweenQuestionAndUser(question.getQues_id(), user_id);
        return 1;
    }

    /**
     * 拿到问题，如果问题不存在，抛出异常
     * @param ques_id
     * @return 返回一个问题的对象
     * @throws NotFoundException
     */
    @Override
    public Question getQuestion(Integer ques_id) throws NotFoundException {
        Question question = communityQAMapper.getQuestion(ques_id);
        if(question == null){
            throw new NotFoundException(404, 404, "没有找到问题");
        }else {
            return question;
        }
    }

    /**
     * 拿到问题的标签，如果标签不存在，抛出异常
     * @param ques_id
     * @return 包含所有标签的list
     * @throws NotFoundException
     */
    @Override
    public List<Label> getLabels(Integer ques_id) throws NotFoundException{
        List<Label> labels = communityQAMapper.listAllQuesetionLabel(ques_id);
        if(labels == null){
            throw new NotFoundException(404, 404, "没有找到该问题的标签");
        }else{
            return labels;
        }
    }

    /**
     * 编辑某个问题，如果问题不存在，抛出异常
     * @param question
     * @return 编辑成功返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer updateQuestion(Question question) throws NotFoundException{
        getQuestion(question.getQues_id());
        communityQAMapper.editQuestion(question);
        return 1;
    }

    /**
     * 删除某个问题, 如果问题不存在，抛出异常（问题：要不要同时删除标签，如果删除的话，恢复问题的时候要重新添加标签）
     * @param question
     * @return 删除成功返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer deleteQuesiton(Question question) throws NotFoundException{
        getQuestion(question.getQues_id());
        communityQAMapper.deleteQuestion(question);
        return 1;
    }

    /**
     * 拿到某个问题的所有回答，如果问题不存在，则抛出异常，如果没有找到任何回答，也抛出异常
     * @param ques_id
     * @return 包含所有回答的list
     * @throws NotFoundException
     */
    @Override
    public List<QuestionAnswer> listAllAnswer(Integer ques_id) throws NotFoundException{
        getQuestion(ques_id);
        List<QuestionAnswer> answers = communityQAMapper.listAllQuestionAnswer(ques_id);
        if(answers == null){
            throw new NotFoundException(404, 404, "没有找到该问题的回答");
        }else {
            return answers;
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
        communityQAMapper.createMapBetweenQuestionAndAnswer(ques_id, questionAnswer.getAnswer_id());
        return 1;
    }

    /**
     * 得到某个回答，如果该回答不存在，抛出异常
     * @param answer_id 回答的id
     * @return 返回回答
     * @throws NotFoundException
     */
    @Override
    public QuestionAnswer getAnswer(Integer answer_id) throws NotFoundException{
        QuestionAnswer questionAnswer =  communityQAMapper.getAnswer(answer_id);
        if(questionAnswer == null){
            throw new NotFoundException(404, 404, "没有找到该回答");
        }else {
            return questionAnswer;
        }
    }

    /**
     * 编辑回答，如果回答不存在，抛出异常
     * @param questionAnswer 要编辑的回答对象
     * @return 编辑成功返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer editAnswer(QuestionAnswer questionAnswer) throws NotFoundException{
        getAnswer(questionAnswer.getAnswer_id());
        communityQAMapper.editAnswer(questionAnswer);
        return 1;
    }

    /**
     * 删除回答，如果回答不存在，抛出异常
     * @param questionAnswer 要删除的回答
     * @return 删除成功，返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer deleteAnswer(QuestionAnswer questionAnswer) throws NotFoundException{
        getAnswer(questionAnswer.getAnswer_id());
        communityQAMapper.deleteAnswer(questionAnswer);
        return 1;
    }

    /**
     * 检查是否关注过问题(这个步骤一般都是和拿到回答一起出现，所以没有判断回答是否存在)
     * @param user_id 用户id
     * @param ques_id 问题id
     * @return 如果关注过，返回关注id，如果没有关注过，返回0
     */
    @Override
    public Integer isQuestionEverAttention(Integer user_id, Integer ques_id){
        Integer atten_id = communityQAMapper.isQuestionEverAttention(user_id, ques_id);
        if(atten_id == null){
            return 0;
        }else {
            return atten_id;
        }
    }

    /**
     * 检查是否被关注
     * @param atten_id 关注的id
     * @return 如果关注返回1，如果没有返回0
     */
    @Override
    public Integer isQestionAttention(Integer atten_id) {
        return communityQAMapper.isQuestionAttention(atten_id);
    }

    /**
     * 关注问题，如果问题不存在，抛出异常，如果存在则关注；如果问题已经被该用户关注过，则只需要修改字段，如果没有被关注过，则添加记录，
     * 并且建立用户和关注的映射
     * @param ques_id 被关注的问题的id
     * @param questionAttention 关注对象，对象里面包括用户的id
     * @return  关注成功返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer attentionQuestion(Integer ques_id, QuestionAttention questionAttention) throws NotFoundException{
        getQuestion(ques_id);
        Integer atten_id = questionAttention.getAtten_id();
        if(atten_id != null){
            communityQAMapper.reAddAttentionToQuestion(questionAttention);
        }else {
            communityQAMapper.addAttentionToQuestion(questionAttention);
            atten_id = questionAttention.getAtten_id();
            communityQAMapper.createMapBetweenAttentionAndQuestion(ques_id, questionAttention);
        }
        return atten_id;
    }

    /**
     * 得到指定关注id的关注记录，如果不存在，抛出异常
     * @param atten_id 关注的id
     * @return 返回指定id的关注对象
     * @throws NotFoundException
     */
    @Override
    public QuestionAttention getAttention(Integer atten_id) throws NotFoundException{
        QuestionAttention questionAttention= communityQAMapper.getAttention(atten_id);
        if(questionAttention == null){
            throw new NotFoundException(404, 404, "该关注不存在");
        }else{
            return questionAttention;
        }
    }

    /**
     * 取消关注问题，如果该条关注记录不存在，则抛出异常，否则取消成功返回1
     * @param questionAttention 关注对象
     * @return 取消成功返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer cancelAttentionQuestion(QuestionAttention questionAttention) throws NotFoundException{
        getAttention(questionAttention.getAtten_id());
        communityQAMapper.cancelAttention(questionAttention);
        return 1;
    }

    /**
     * 拿到某个问题的关注数
     * @param ques_id  问题的id
     * @return 返回关注的人数
     */
    @Override
    public Integer countFollwers(Integer ques_id) {
        return communityQAMapper.countTheFollower(ques_id);
    }

    /**
     * 拿到某个回答的点赞数
     * @param answer_id 回答的id
     * @return 回答的点赞数
     */
    @Override
    public Integer countAgreement(Integer answer_id) {
        return communityQAMapper.countAgreement(answer_id);
    }

    /**
     * 拿到某个回答的反对数
     * @param answer_id 回答的id
     * @return 回答的反对数
     */
    @Override
    public Integer countDisagreement(Integer answer_id) {
        return communityQAMapper.countDisagreement(answer_id);
    }

    /**
     * 判断用户是否评价过这个回答
     * @param user_id 用户id
     * @param answer_id 回答id
     * @return 如果评价过返回评价的id，如果没有，返回0
     */
    @Override
    public Integer isEverEvaluate(Integer user_id, Integer answer_id) {
        Integer evaluate_id = communityQAMapper.isEverEvaluate(user_id, answer_id);
        if(evaluate_id == null){
            return 0;
        }else {
            return evaluate_id;
        }
    }

    /**
     * 查看用户对这个回答的评价状态
     * @param evaluate_id 评价的id
     * @return 赞同返回1，不赞同返回2，取消返回3
     */
    @Override
    public Integer evaluateStatus(Integer evaluate_id){
        return communityQAMapper.evaluateStatus(evaluate_id);
    }

    /**
     * 评价回答，包括赞同、取消赞同、不赞同、取消不赞同
     * @param answer_id 回答的id
     * @param answerEvaluate 评价回答的对象
     * @return 评价成功返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer evaluateAnswer(Integer answer_id, AnswerEvaluate answerEvaluate) throws NotFoundException{
        getAnswer(answer_id);
        Integer evaluate_id = answerEvaluate.getEvaluate_id();
        if(evaluate_id != null){
            communityQAMapper.reEvaluateAnswer(answerEvaluate);
        }else {
            communityQAMapper.addEvaluateToAnswer(answerEvaluate);
            evaluate_id = answerEvaluate.getEvaluate_id();
            communityQAMapper.createMapBetweenAnswerAndEvaluate(answerEvaluate.getEvaluate_id(), answer_id);
        }
        return evaluate_id;
    }

    /**
     * 列出这个回答的所有评论
     * @param answer_id 回答的id
     * @return 返回包含评论对象的list
     * @throws NotFoundException
     */
    @Override
    public List<AnswerComment> getAllAnswerComments(Integer answer_id) throws NotFoundException{
        getAnswer(answer_id);
        List<AnswerComment> answerComments = communityQAMapper.listAllAnswerComment(answer_id);
        if(answerComments == null){
            throw new NotFoundException(404, 404, "找不到这个回答的评论");
        }else {
            return answerComments;
        }
    }

    /**
     * 拿到某个评论
     * @param comment_id 评论的id
     * @return 返回评论对象
     * @throws NotFoundException
     */
    @Override
    public AnswerComment getComment(Integer comment_id) throws NotFoundException{
        AnswerComment answerComment = communityQAMapper.getComment(comment_id);
        if(answerComment == null){
            throw new NotFoundException(404,404,"找不到该评论");
        }else {
            return answerComment;
        }
    }

    /**
     * 评论回答，如果回答不存在，抛出异常
     * @param answer_id 回答的id
     * @param answerComment 要添加的评论的对象
     * @return 评论成功返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer addCommentToAnswer(Integer answer_id, AnswerComment answerComment) throws NotFoundException{
        getAnswer(answer_id);
        communityQAMapper.addCommentToAnswer(answerComment);
        communityQAMapper.createMapBetweenAnswerAndComment(answer_id, answerComment.getComment_id());
        return 1;
    }

    /**
     * 删除评论，评论不存在则抛出异常
     * @param answerComment 要删除的评论的对象
     * @return 删除成功返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer deleteComment(AnswerComment answerComment) throws  NotFoundException{
        getComment(answerComment.getComment_id());
        communityQAMapper.deleteComment(answerComment);
        return 1;
    }

    /**
     * 返回评论的点赞数
     * @param comment_id 评论的id
     * @return 点赞数
     */
    @Override
    public Integer countAgreementOfComment(Integer comment_id) {
        return communityQAMapper.countCommentAgreement(comment_id);
    }

    /**
     * 检查用户是否评价过这个评论
     * @param user_id 用户的id
     * @param comment_id 评论的id
     * @return 如果评价过，则返回评价的id，如果没有返回0
     */
    @Override
    public Integer isEverEvaluateComment(Integer user_id, Integer comment_id) {
        Integer evaluate_id = communityQAMapper.isEverEvaluateComment(user_id, comment_id);
        if(evaluate_id == null){
            return 0;
        }else {
            return evaluate_id;
        }
    }

    /**
     * 查看用户对该评论的评价状态
     * @param evaluate_id 评价的id
     * @return 1是赞同，2是返回，3是取消
     */
    @Override
    public Integer commentEvaluateStatus(Integer evaluate_id) {
        return communityQAMapper.commentEvaluateStatus(evaluate_id);
    }

    /**
     * 评价评论，如果评价对象里面的评价id为空，说明没有评价过，则插入评价并且建立映射，如果不为空，说明评价过，则更新字段
     * @param comment_id 评论的id
     * @param commentEvaluate 评价的对象
     * @return 返回评价id
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer evaluateComment(Integer comment_id, CommentEvaluate commentEvaluate) throws NotFoundException{
        getComment(comment_id);
        Integer evaluate_id = commentEvaluate.getEvaluate_id();
        if(evaluate_id == null){
            communityQAMapper.addEvaluateToComment(commentEvaluate);
            evaluate_id = commentEvaluate.getEvaluate_id();
            communityQAMapper.createMapBetweenCommentAndEvaluate(evaluate_id, comment_id);
        }else {
            communityQAMapper.reEvaluateComment(commentEvaluate);
        }
        return evaluate_id;
    }

    /**
     * 列出该评论的所有讨论
     * @param comment_id 评论的id
     * @return 返回包含所有讨论的list
     * @throws NotFoundException
     */
    @Override
    public List<CommentDiscuss> listAllCommentDiscuss(Integer comment_id) throws NotFoundException{
        getComment(comment_id);
        List<CommentDiscuss> commentDiscusses = communityQAMapper.listAllCommentDiscuss(comment_id);
        if(commentDiscusses == null){
            throw new NotFoundException(404,404,"没找到该问题的讨论");
        }else {
            return commentDiscusses;
        }
    }

    /**
     * 得到某个讨论
     * @param discuss_id 讨论的id
     * @return 返回该讨论对象
     * @throws NotFoundException
     */
    @Override
    public CommentDiscuss getDiscuss(Integer discuss_id) throws NotFoundException{
        CommentDiscuss commentDiscuss = communityQAMapper.getDiscuss(discuss_id);
        if(commentDiscuss == null){
            throw new NotFoundException(404,404,"没有找到这个讨论");
        }else {
            return commentDiscuss;
        }
    }

    /**
     * 添加讨论，如果要讨论的评论不存在，则抛出异常
     * @param comment_id 要讨论的评论的id
     * @param commentDiscuss 要添加的讨论的对象
     * @return 添加成功返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer addDiscuss(Integer comment_id, CommentDiscuss commentDiscuss) throws NotFoundException{
        getComment(comment_id);
        communityQAMapper.addDiscuss(commentDiscuss);
        communityQAMapper.createMapBetweenDiscussAndComment(commentDiscuss.getDiscuss_id(), comment_id);
        return 1;
    }

    /**
     * 列出该讨论的点赞数
     * @param discuss_id 讨论的id
     * @return 返回点赞数
     */
    @Override
    public Integer countAgreementOfDiscuss(Integer discuss_id) {
        return communityQAMapper.countDiscussAgreement(discuss_id);
    }

    /**
     * 删除某个讨论
     * @param commentDiscuss 要更新的讨论对象
     * @return 删除成功返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer deleteDiscuss(CommentDiscuss commentDiscuss) throws NotFoundException{
        getDiscuss(commentDiscuss.getDiscuss_id());
        communityQAMapper.deleteDiscuss(commentDiscuss);
        return 1;
    }

    /**
     * 检查是否评价过这个讨论
     * @param user_id 用户的id
     * @param discuss_id 讨论的id
     * @return 如果没有评价过，返回0；否则返回评价的id
     */
    @Override
    public Integer isEverEvaluateDiscuss(Integer user_id, Integer discuss_id) {
        Integer evaluate_id = communityQAMapper.isEverEvaluateDiscuss(user_id,discuss_id);
        if(evaluate_id == null){
            return 0;
        }else {
            return evaluate_id;
        }
    }

    /**
     *  返回评价的状态
     * @param evaluate_id 评价的id
     * @return 如果赞同返回1，取消，返回3
     */
    @Override
    public Integer discussEvaluateStatus(Integer evaluate_id) {
        return communityQAMapper.discussEvaluateStatus(evaluate_id);
    }

    /**
     * 评价这个讨论，如果没有评价过，则添加评价并且建立映射；如果评价过，则更新字段
     * @param discuss_id 讨论的id
     * @param discussEvaluate 评价的对象
     * @return 返回评价id
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer evaluateDiscuss(Integer discuss_id, DiscussEvaluate discussEvaluate) throws NotFoundException{
        getDiscuss(discuss_id);
        Integer evaluate_id = discussEvaluate.getEvaluate_id();
        if(evaluate_id == null){
            communityQAMapper.addEvaluateToDiscuss(discussEvaluate);
            evaluate_id = discussEvaluate.getEvaluate_id();
            communityQAMapper.createMapBetweenDiscussAndEvaluate(evaluate_id, discuss_id);
        }else {
            communityQAMapper.reEvaluateDiscuss(discussEvaluate);
        }
        return evaluate_id;
    }

    /**
     * 邀请某人回答问题
     * @param answerInvitation 邀请的对象
     * @param ques_id 问题的id
     * @param invit_user_id 邀请人的id
     * @param invited_user_id 被邀请人的id
     * @return 邀请成功返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer invitToAnswer(AnswerInvitation answerInvitation, Integer ques_id,
                                 Integer invit_user_id, Integer invited_user_id) throws NotFoundException{
        getQuestion(ques_id);
        communityQAMapper.addInvitation(answerInvitation);
        communityQAMapper.createMapBetweenInvitationAndQuestion(answerInvitation.getInvit_id(),
                ques_id,invit_user_id,invited_user_id);
        return 1;
    }

    /**
     * 得到某个邀请
     * @param invit_id 邀请的id
     * @return 如果没找到，则抛出异常，如果找到了，返回找到的邀请
     * @throws NotFoundException
     */
    @Override
    public AnswerInvitation getInvitation(Integer invit_id) throws NotFoundException{
        AnswerInvitation answerInvitation = communityQAMapper.getInvitation(invit_id);
        if(answerInvitation == null){
            throw new NotFoundException(404,404,"没有找到该邀请");
        }else {
            return answerInvitation;
        }
    }

    /**
     * 接受或者拒绝某个邀请
     * @param answerInvitation 邀请的对象
     * @return 更改成功返回1, 没找到邀请，抛出异常
     * @throws NotFoundException
     */
    @Override
    public Integer acceptOrRefuseInvitation(AnswerInvitation answerInvitation) throws NotFoundException{
        getInvitation(answerInvitation.getInvit_id());
        communityQAMapper.updateStatusOfInvitation(answerInvitation);
        return 1;
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
