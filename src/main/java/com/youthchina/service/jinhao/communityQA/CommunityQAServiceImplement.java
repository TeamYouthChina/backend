package com.youthchina.service.jinhao.communityQA;

import com.youthchina.dao.jinhao.CommunityQAMapper;
import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.dao.qingyang.JobMapper;
import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommunityQAServiceImplement implements CommunityQAService {
    @Resource
    CommunityQAMapper communityQAMapper;

    @Resource
    CompanyMapper companyMapper;

    @Resource
    JobMapper jobHrMapper;


    @Override
    public boolean isAnswerBelongToQuestion(Integer answer_id, Integer ques_id) {
        return communityQAMapper.isAnswerBelongToQuestion(answer_id, ques_id);
    }

    @Override
    @Transactional
    public Question getQuestionInfoById(Integer ques_id) throws NotFoundException {
        Question question = communityQAMapper.getQuestionById(ques_id);
        if(question == null){
            throw new NotFoundException(404,404,"没有找到这个问题");
        }
        QuestionReleTypeAndId questionReleTypeAndId = communityQAMapper.getQuestionReleTypeAndReleId(ques_id);
        if(questionReleTypeAndId.getRele_type() == 2){
            Company company = companyMapper.selectCompany(questionReleTypeAndId.getRele_id());
            question.setCompany(company);
        }else if(questionReleTypeAndId.getRele_type() == 3){
            Job job = jobHrMapper.selectJobByJobId(questionReleTypeAndId.getRele_id());
            question.setJob(job);
        }
        return question;
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
    public Integer addQuestion(Question question, Integer user_id, List<Integer> labels, Integer rele_type, Integer rele_id) {
        communityQAMapper.addQuestion(question);
        communityQAMapper.addLabels(labels, question.getQues_id());
        communityQAMapper.createMapBetweenQuestionAndUser(question.getQues_id(), user_id, rele_type, rele_id);
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
     * 列出某用户提出过的问题,如果没提出过问题，抛出异常
     * @param user_id 用户的id
     * @return 返回问题的list
     * @throws NotFoundException
     */
    @Override
    public List<Question> listMyQuestions(Integer user_id) throws NotFoundException{
        List<Question> questions = communityQAMapper.getMyQuestions(user_id);
        if(questions == null){
            throw new NotFoundException(404, 404, "用户还没有提出过问题");
        }else {
            return questions;
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
            throw new NotFoundException(404, 404, "该问题没有标签");
        }else{
            return labels;
        }
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
    public Integer addAnswer(QuestionAnswer questionAnswer, Integer ques_id, Integer answer_level) {
        communityQAMapper.addAnswerToQuestion(questionAnswer);
        communityQAMapper.createMapBetweenQuestionAndAnswer(ques_id, questionAnswer.getAnswer_id(), answer_level);
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
     * 得到问题收到的回答的条数
     * @param ques_id 问题的id
     * @return 返回回答的条数
     */
    @Override
    public Integer countAnswer(Integer ques_id) {
        return communityQAMapper.countAnswer(ques_id);
    }

    /**
     * 列出用户做出过的回答，如果没有，抛出异常
     * @param user_id 用户的id
     * @return 返回回答的list
     * @throws NotFoundException
     */
    @Override
    public List<QuestionAnswer> listMyAnswers(Integer user_id) throws NotFoundException{
        List<QuestionAnswer> questionAnswers = communityQAMapper.listMyAnswer(user_id);
        if(questionAnswers == null){
            throw new NotFoundException(404,404,"用户没有回答过问题");
        }else {
            return questionAnswers;
        }
    }

    /**
     * 检查用户是否关注这个问题，如果关注，QuestionAttention的atten_canecl字段为1，如果用户从来没关注过问题，则返回null
     * @param user_id 用户id
     * @param ques_id 问题id
     * @return 返回问题关注对象
     */
    @Override
    public QuestionAttention isQuestionAttention(Integer user_id, Integer ques_id) throws NotFoundException {
        QuestionAttention questionAttention =  communityQAMapper.isQuestionAttention(user_id, ques_id);
        if(questionAttention == null){
            throw new NotFoundException(404, 404, "用户没有关注过这个问题");
        }else {
            return questionAttention;
        }
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
            communityQAMapper.createMapBetweenAttentionAndQuestion(ques_id, atten_id);
        }
        return 1;
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
     * 列出用户关注的问题，如果没有抛出异常
     * @param user_id 用户的id
     * @return 返回关注的问题的list
     * @throws NotFoundException
     */
    @Override
    public List<Question> listMyAttenQuestion(Integer user_id) throws NotFoundException{
       List<Question> questions = communityQAMapper.listMyAttenQuestion(user_id);
       if(questions == null){
           throw new NotFoundException(404,404,"该用户没有关注的问题");
       }else {
           return questions;
       }
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
     * 查看用户对这个回答的评价状态，evaluate_type：1是赞同，2是反对，3是取消；如果没有评价过抛出异常
     * @param user_id 用户id
     * @param answer_id 回答id
     * @return 返回回答评价对象
     */
    @Override
    public AnswerEvaluate evaluateStatus(Integer user_id, Integer answer_id) throws NotFoundException{
        AnswerEvaluate answerEvaluate = communityQAMapper.evaluateStatus(user_id, answer_id);
        if(answerEvaluate == null){
            throw new NotFoundException(404,404,"该用户没有评价过这个回答");
        }else {
            return answerEvaluate;
        }
    }

    /**
     * 拿到某个回答评价，如果没有，抛出异常
     * @param evaluate_id 评价的id
     * @return 返回得到的回答评价
     * @throws NotFoundException
     */
    @Override
    public AnswerEvaluate getAnswerEvaluate(Integer evaluate_id) throws NotFoundException{
        AnswerEvaluate answerEvaluate = communityQAMapper.getAnswerEvaluate(evaluate_id);
        if(answerEvaluate == null){
            throw new NotFoundException(404, 404, "没有找到该答案评价");
        }else{
            return answerEvaluate;
        }
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
            getAnswerEvaluate(evaluate_id);
            communityQAMapper.reEvaluateAnswer(answerEvaluate);
        }else {
            communityQAMapper.addEvaluateToAnswer(answerEvaluate);
            evaluate_id = answerEvaluate.getEvaluate_id();
            communityQAMapper.createMapBetweenAnswerAndEvaluate(answerEvaluate.getEvaluate_id(), answer_id);
        }
        return evaluate_id;
    }

    /**
     * 得到用户赞同过的回答,没有的话抛出异常
     * @param user_id 用户的id
     * @return 返回用户赞同过的回答的list
     * @throws NotFoundException
     */
    @Override
    public List<QuestionAnswer> listMyAgreeAnswer(Integer user_id) throws NotFoundException {
        List<QuestionAnswer> questionAnswers = communityQAMapper.listMyAgreeAnswer(user_id);
        if(questionAnswers == null){
            throw new NotFoundException(404,404,"该用户没有赞同的回答");
        }else {
            return questionAnswers;
        }
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
    public Integer addCommentToAnswer(Integer answer_id, AnswerComment answerComment, Integer comment_level)
            throws NotFoundException{
        getAnswer(answer_id);
        communityQAMapper.addCommentToAnswer(answerComment);
        communityQAMapper.createMapBetweenAnswerAndComment(answer_id, answerComment.getComment_id(),comment_level);
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
     * 查看用户对这个评论的评价状态，如果没有评价过，抛出异常
     * @param user_id 用户id
     * @param comment_id 评论的id
     * @return 返回评价对象
     * @throws NotFoundException
     */
    @Override
    public CommentEvaluate commentEvaluateStatus(Integer user_id, Integer comment_id) throws NotFoundException{
        CommentEvaluate commentEvaluate = communityQAMapper.commentEvaluateStatus(user_id, comment_id);
        if(commentEvaluate == null){
            throw new NotFoundException(404,404,"用户没有评价过这个评论");
        }else {
            return commentEvaluate;
        }
    }

    /**
     * 评价评论，如果评价对象里面的评价id为空，说明没有评价过，则插入评价并且建立映射，如果不为空，说明评价过，则更新字段
     * @param comment_id 评论的id
     * @param commentEvaluate 评价的对象
     * @return 评价成功返回1
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
            communityQAMapper.getCommentEvaluate(evaluate_id);
            communityQAMapper.reEvaluateComment(commentEvaluate);
        }
        return 1;
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
    public Integer addDiscuss(Integer comment_id, CommentDiscuss commentDiscuss, Integer discuss_level) throws NotFoundException{
        getComment(comment_id);
        communityQAMapper.addDiscuss(commentDiscuss);
        communityQAMapper.createMapBetweenDiscussAndComment(commentDiscuss.getDiscuss_id(), comment_id,discuss_level);
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
     * 检查用户对该讨论的评价状态
     * @param user_id 用户的id
     * @param discuss_id 讨论的id
     * @return 返回评价对象
     * @throws NotFoundException 没有评价过。抛出异常
     */
    @Override
    public DiscussEvaluate discussEvaluateStatus(Integer user_id, Integer discuss_id) throws NotFoundException {
        DiscussEvaluate discussEvaluate = communityQAMapper.discussEvaluateStatus(user_id, discuss_id);
        if(discussEvaluate == null){
            throw new NotFoundException(404,404,"没有评价过");
        }else {
            return discussEvaluate;
        }
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
     * 列出用户收到的邀请
     * @param user_id 用户的id
     * @return 返回收到的邀请的list
     * @throws NotFoundException 如果没有，抛出异常
     */
    @Override
    public List<AnswerInvitation> listInvitationGot(Integer user_id) throws NotFoundException {
        List<AnswerInvitation> answerInvitations = communityQAMapper.listInvitationGot(user_id);
        if(answerInvitations == null){
            throw new NotFoundException(404,404,"还没有邀请");
        }else {
            return answerInvitations;
        }
    }

    /**
     * 列出用户邀请过的来回答这个问题的其他用户的id
     * @param user_id 用户id
     * @param ques_id 问题id
     * @return 返回已经被邀请过的用户的id
     * @throws NotFoundException 如果没有邀请过任何用户，抛出异常
     */
    @Override
    public List<Integer> listUsersInvitedByMeToQuestion(Integer user_id, Integer ques_id) throws NotFoundException{
        List<Integer> usersInvited = communityQAMapper.listUsersInvitedByMeToQuestion(user_id, ques_id);
        if(usersInvited == null){
            throw new NotFoundException(404,404,"你还没有邀请过用户回答这问题");
        }else {
            return usersInvited;
        }
    }

    /**
     * 邀请某人回答问题
     * @param answerInvitation 邀请的对象
     * @param ques_id 问题的id
     * @param invited_user_id 被邀请人的id
     * @return 邀请成功返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer invitToAnswer(AnswerInvitation answerInvitation, Integer ques_id,
                                 Integer invited_user_id) throws NotFoundException{
        getQuestion(ques_id);
        communityQAMapper.addInvitation(answerInvitation);
        communityQAMapper.createMapBetweenInvitationAndQuestion(answerInvitation.getInvit_id(),
                invited_user_id);
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
     * 拿到前10个问题的基本信息，问答板块主页要用
     * @return 返回问题对象的list
     */
    @Override
    public List<Question> listQuestion() throws NotFoundException{
        List<Question> questions =  communityQAMapper.listQuestion();
        if(questions == null){
            throw new NotFoundException(404,404,"没有任何问题");
        }else {
            return questions;
        }
    }


    /**
     * 拿到前十个视频
     * @return 返回前十个视频的list
     * @throws NotFoundException 没有找到抛出异常
     */
    @Override
    public List<Video> listFirstTenVideos() throws NotFoundException{
        List<Video> videos= communityQAMapper.listFirstTenVideos();
        if(videos == null){
            throw new NotFoundException(404,404,"没有任何视频");
        }else {return videos;}
    }

    /**
     * 拿到该用户发布的所有视频
     * @param user_id 用户的id
     * @return 返回视频的list
     * @throws NotFoundException 没有发布过视频抛出异常
     */
    @Override
    public List<Video> listAllMyVideos(Integer user_id) throws NotFoundException{
        List<Video> videos = communityQAMapper.listAllMyVideos(user_id);
        if(videos == null){
            throw new NotFoundException(404,404,"该用户没有发过视频");
        }else {
            return videos;
        }
    }

    /**
     * 发布视频
     * @param video 要发布的视频对象
     * @param user_id 发布视频的用户的id
     * @return 发布成功返回1
     */
    @Override
    @Transactional
    public Integer addVideo(Video video, Integer user_id) {
        communityQAMapper.addVideo(video);
        communityQAMapper.createMapBetweenVideoAndUser(video.getVideo_id(), user_id);
        return 1;
    }

    /**
     * 拿到某个视频，如果视频不存在，则抛出异常
     * @param video_id 视频的id
     * @return 返回得到的视频对象
     * @throws NotFoundException
     */
    @Override
    public Video getVideo(Integer video_id) throws NotFoundException {
        Video video = communityQAMapper.getVideo(video_id);
        if(video == null){
            throw new NotFoundException(404, 404, "没有找到该视频");
        }else {
            return video;
        }
    }

    /**
     * 删除某个视频，如果视频不存在，则抛出异常
     * @param video 要删除的视频对象
     * @return 删除成功返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer deleteVideo(Video video) throws NotFoundException{
        getVideo(video.getVideo_id());
        communityQAMapper.deleteVideo(video);
        return 1;
    }

    /**
     * 查看用户对该视频的关注状态
     * @param video_id 视频的id
     * @param user_id 用户的id
     * @return 返回视频关注对象
     * @throws NotFoundException 没有关注过。抛出异常
     */
    @Override
    public VideoAttention videoAttentionStatus(Integer video_id, Integer user_id) throws NotFoundException{
        VideoAttention videoAttention = communityQAMapper.videoAttentionStatus(video_id, user_id);
        if(videoAttention == null){
            throw new NotFoundException(404,404,"用户没有关注过这个视频");

        }else {
            return videoAttention;
        }
    }

    /**
     * 关注视频，如果用户从没关注过，则添加新关注，并且建立映射，否则重新关注
     * @param videoAttention 视频关注对象
     * @param video_id 视频id
     * @return 返回关注id
     */
    @Override
    @Transactional
    public Integer attentionVideo(VideoAttention videoAttention, Integer video_id) throws NotFoundException{
        getVideo(video_id);
        Integer atten_id = videoAttention.getAtten_id();
        if(atten_id == null){
            communityQAMapper.addAttentionToVideo(videoAttention);
            atten_id = videoAttention.getAtten_id();
            communityQAMapper.createMapBetweenAttentionAndVideo(atten_id, video_id);
        }else{
            communityQAMapper.reAddAttentionToVideo(videoAttention);
        }
        return atten_id;
    }

    /**
     * 拿到某个视频关注
     * @param atten_id 关注id
     * @return 如果找到了，返回找到的视频关注对象，否则抛出异常
     * @throws NotFoundException
     */
    @Override
    public VideoAttention getVideoAttetion(Integer atten_id) throws NotFoundException{
        VideoAttention videoAttention = communityQAMapper.getVideoAttention(atten_id);
        if(videoAttention == null){
            throw new NotFoundException(404, 404, "没有找到这个视频关注");
        }else {
            return videoAttention;
        }
    }

    /**
     * 取消关注某个视频，如果该关注对象不存在，则抛出异常
     * @param videoAttention 视频关注对象
     * @return 取消成功，返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer cancelAttenVideo(VideoAttention videoAttention) throws NotFoundException{
        getVideoAttetion(videoAttention.getAtten_id());
        communityQAMapper.cancelAttentionVideo(videoAttention);
        return 1;
    }

    /**
     * 评论视频，如果视频不存在，抛出异常
     * @param videoComment 视频评论对象
     * @param video_id 视频id
     * @param comment_level 评论的楼层
     * @return 评论成功，返回1
     * @throws NotFoundException
     */
    @Override
    public Integer commentVideo(VideoComment videoComment, Integer video_id, Integer comment_level) throws NotFoundException{
        getVideo(video_id);
        communityQAMapper.addCommentToVideo(videoComment);
        communityQAMapper.createMapBetweenCommentAndVideo(videoComment.getComment_id(), video_id, comment_level);
        return 1;
    }

    /**
     * 拿到某条视频评论，如果没找到，抛出异常
     * @param comment_id 评论的id
     * @return 返回找到的评论对象
     * @throws NotFoundException
     */
    @Override
    public VideoComment getVideoComment(Integer comment_id) throws NotFoundException{
        VideoComment videoComment = communityQAMapper.getVideoComment(comment_id);
        if(videoComment == null){
            throw new NotFoundException(404, 404, "没找到这条视频评论");
        }else {
            return videoComment;
        }
    }

    /**
     * 删除某条视频评论，如果该条评论不存在，抛出异常
     * @param videoComment 要删除的视频评论对象
     * @return 删除成功返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer deleteVideoComment(VideoComment videoComment) throws NotFoundException{
        getVideoComment(videoComment.getComment_id());
        communityQAMapper.deleteVideoComment(videoComment);
        return 1;
    }

    /**
     * 查看用户对该视频的评价状态
     * @param video_id 视频的id
     * @param user_id 用户的id
     * @return 返回评价对象
     * @throws NotFoundException 没有评价过 抛出异常
     */
    @Override
    public VideoEvaluate videoEvaluateStatus(Integer video_id, Integer user_id) throws NotFoundException{
        VideoEvaluate videoEvaluate = communityQAMapper.videoEvaluateStatus(video_id, user_id);
        if(videoEvaluate == null){
            throw new NotFoundException(404,404,"用户没有评价过着视频");
        }else {
            return videoEvaluate;
        }
    }

    /**
     * 找到某个视频评价
     * @param evaluate_id 评价id
     * @return 返回找到的视频评价
     * @throws NotFoundException
     */
    @Override
    public VideoEvaluate getVideoEvaluate(Integer evaluate_id) throws NotFoundException{
        VideoEvaluate videoEvaluate = communityQAMapper.getVideoEvaluate(evaluate_id);
        if(videoEvaluate == null){
            throw new NotFoundException(404, 404, "没有找到这个视频评价");
        }else {
            return videoEvaluate;
        }
    }

    /**
     * 评价视频，如果没有评价过，则添加评价并且建立映射，否则直接重新评价
     * @param videoEvaluate 视频评价对象
     * @param video_id 视频的id
     * @return 返回评价id
     * @throws NotFoundException
     */
    @Transactional
    @Override
    public Integer evaluateVideo(VideoEvaluate videoEvaluate, Integer video_id) throws NotFoundException {
        getVideo(video_id);
        Integer evaluate_id = videoEvaluate.getEvaluate_id();
        if(evaluate_id == null){
            communityQAMapper.addEvaluationToVideo(videoEvaluate);
            evaluate_id = videoEvaluate.getEvaluate_id();
            communityQAMapper.createMapBetweenEvaluationAndVideo(evaluate_id, video_id);
        }else{
            getVideoEvaluate(evaluate_id);
            communityQAMapper.reEvaluateVideo(videoEvaluate);
        }
        return evaluate_id;
    }

    /**
     * 拿到某个视频的关注数
     * @param video_id 视频的id
     * @return 返回视频的关注数
     */
    @Override
    public Integer countVideoFollower(Integer video_id) {
        return communityQAMapper.countVideoFollwers(video_id);
    }

    /**
     * 拿到某个视频等等评论数
     * @param video_id 视频的id
     * @return 返回视频的评论数
     */
    @Override
    public Integer countVideoComments(Integer video_id) {
        return communityQAMapper.countVideoComments(video_id);
    }

    /**
     * 拿到某个视屏的赞同数
     * @param video_id 视频iid
     * @return 返回视频的赞同数
     */
    @Override
    public Integer countVideoAgreement(Integer video_id) {
        return communityQAMapper.countVideoAgreement(video_id);
    }

    /**
     * 拿到某个视频的不赞同数
     * @param video_id 视频的id
     * @return 返回视频的反对
     */
    @Override
    public Integer countVideoDisAgreement(Integer video_id) {
        return communityQAMapper.countVideoDisagreement(video_id);
    }
}
