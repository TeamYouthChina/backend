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

    private Question getQuestion(Integer ques_id) throws NotFoundException{
        Question question = communityQAMapper.getQuestion(ques_id);
        if(question == null){
            throw new NotFoundException(404,404,"没有找到这个问题");//todo
        }
        return question;
    }

    @Override
    @Transactional
    public Question add(Question entity) {
        Integer rela_type = entity.getRela_type();
        Integer rela_id = entity.getRela_id();
        communityQAMapper.addQuestion(entity);
        communityQAMapper.createMapBetweenQuestionAndUser(entity.getQues_id(), entity.getQues_user().getId(),
                rela_type, rela_id);
        return communityQAMapper.getQuestionById(entity.getQues_id());
    }

    @Override
    @Transactional
    public Question get(Integer id) throws NotFoundException {
        Question question = communityQAMapper.getQuestionById(id);
        if(question == null){
            throw new NotFoundException(404,404,"没有找到这个问题");//todo
        }
        return question;
    }

    @Override
    @Transactional
    public List<Question> get(List<Integer> id) throws NotFoundException {
        List<Question> questions = new LinkedList<>();
        for(Integer one : id){
            questions.add(get(one));
        }
        return questions;
    }

    @Override
    @Transactional
    public Question update(Question question) throws NotFoundException {
        getQuestion(question.getQues_id());
        communityQAMapper.editQuestion(question);
        return question;
    }

    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        getQuestion(id);
        communityQAMapper.deleteQuestion(id);
        communityQAMapper.deleteAllAnswerInvitationMap(id);
        communityQAMapper.deleteAllAnswerInvitation(id);
        communityQAMapper.deleteAllAnswerEvaluation(id);
        communityQAMapper.deleteAllAttention(id);
        communityQAMapper.deleteAllAnswers(id);
        communityQAMapper.deleteAllComments(id);
        communityQAMapper.deleteAllCommentEvaluation(id);
    }

    @Override
    @Transactional
    public QuestionAttention attentionQuestion(Integer ques_id, Integer user_id) throws NotFoundException{
        getQuestion(ques_id);
        QuestionAttention questionAttention = communityQAMapper.isQuestionAttention(ques_id, user_id);
        if(questionAttention == null){
            QuestionAttention questionAttention1 = new QuestionAttention();
            questionAttention1.setUser_id(user_id);
            communityQAMapper.addAttentionToQuestion(questionAttention1);
            communityQAMapper.createMapBetweenAttentionAndQuestion(ques_id,questionAttention1.getAtten_id());
        }else{
            communityQAMapper.reAddAttentionToQuestion(questionAttention.getAtten_id());
        }
        return questionAttention;
    }

//    private QuestionAttention getAttention(Integer atten_id) throws NotFoundException{
//        QuestionAttention questionAttention= communityQAMapper.getAttention(atten_id);
//        if(questionAttention == null){
//            throw new NotFoundException(404, 404, "该关注不存在");
//        }else{
//            return questionAttention;
//        }
//    }

    @Override
    @Transactional
    public void cancelAttentionQuestion(Integer ques_id, Integer user_id) throws NotFoundException{
        QuestionAttention questionAttention = communityQAMapper.isQuestionAttention(ques_id, user_id);
        if(questionAttention == null){
            throw new NotFoundException(404, 404, "没有这个关注，无法取消");//todo
        }
        communityQAMapper.cancelAttention(questionAttention.getAtten_id());
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
            throw new NotFoundException(404,404,"该用户没有关注的问题");//todo
        }else {
            return questions;
        }
    }

    @Override
    @Transactional
    public Integer invitUsersToAnswer(Integer invit_user_id, Integer ques_id, List<Integer> invited_user_ids)
            throws NotFoundException{
        for(Integer invited_user_id : invited_user_ids){
            invitUserToAnswer(invit_user_id, ques_id, invited_user_id);
        }
        return 1;
    }

    @Override
    @Transactional
    public Integer invitUserToAnswer(Integer invit_user_id, Integer ques_id,
                                   Integer invited_user_id) throws NotFoundException{
        getQuestion(ques_id);
        AnswerInvitation answerInvitation = new AnswerInvitation();
        answerInvitation.setInvit_user_id(invit_user_id);
        answerInvitation.setInvit_ques_id(ques_id);
        communityQAMapper.addInvitation(answerInvitation);
        communityQAMapper.createMapBetweenInvitationAndQuestion(answerInvitation.getInvit_id(),
                invited_user_id);
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
        if(questions.size() == 0){
            throw new NotFoundException(404, 404, "用户还没有提出过问题");//todo
        }else {
            return questions;
        }
    }

    /**
     * search questions by its title or relative company or job name
     * @param searchContent title or company name
     * @return list of questions
     * @throws NotFoundException if the result of search is null, throw exception
     */
    @Override
    @Transactional
    public List<Question> searchQuestionByTitleOrCompanyName(String searchContent) throws NotFoundException{
        List<Integer> question_ids = getQuestionIdByTitleOrCompanyName(searchContent);
        if(question_ids.size() == 0) throw new NotFoundException(404,404,"没有搜索到相关的问题");//todo
        List<Question> questions = new LinkedList<>();
        for(Integer ques_id : question_ids){
            questions.add(communityQAMapper.getQuestionById(ques_id));
        }
        return questions;
    }

    /**
     * get question id  by its title or relative company or job name
     * @param searchContent title or company or job name
     * @return list of question ids
     */
    private List<Integer> getQuestionIdByTitleOrCompanyName(String searchContent) {
        return communityQAMapper.getQuestionIdByTitleOrCompanyName(searchContent);
    }

    /**
     * search videos by its title or relative company name
     * @param searchContent title or company or name
     * @return list of videos
     * @throws NotFoundException if the result of search is null, throw exception
     */
    @Override
    @Transactional
    public List<Video> searchVideoByTitleOrCompanyName(String searchContent) throws NotFoundException{
        List<Integer> video_ids = getVideoIdByTitleOrCompanyName(searchContent);
        if(video_ids.size() == 0) throw new NotFoundException(404,404,"没有搜索到相关的视频");//todo
        List<Video> videos = new LinkedList<>();
        for(Integer video_id : video_ids){
            videos.add(communityQAMapper.getVideoById(video_id));
        }
        return videos;
    }

    /**
     * get video id by its title or relative company name
     * @param searchContent title or company name
     * @return list of video ids
     */
    private List<Integer> getVideoIdByTitleOrCompanyName(String searchContent) {
        return communityQAMapper.getVideoIdByTitleOrCompanyName(searchContent);
    }

    /**
     * Judge if an answer is belong to a question
     * @param answer_id id of answer
     * @param ques_id id of question
     * @return true or false
     */
    @Override
    public boolean isAnswerBelongToQuestion(Integer answer_id, Integer ques_id) {
        return communityQAMapper.isAnswerBelongToQuestion(answer_id, ques_id);
    }



    /**
     * 添加回答，建立回答和问题的映射
     * @param questionAnswer 要添加的回答的对象
     * @param ques_id 要回答的问题的id
     * @return 添加成功返回1
     */
    @Override
    @Transactional
    public QuestionAnswer addAnswer(QuestionAnswer questionAnswer, Integer ques_id, Integer answer_level) throws NotFoundException {
        getQuestion(ques_id);
        communityQAMapper.addAnswerToQuestion(questionAnswer);
        communityQAMapper.createMapBetweenQuestionAndAnswer(ques_id, questionAnswer.getAnswer_id(), answer_level);
        return questionAnswer;
    }

    /**
     * 得到某个回答，如果该回答不存在，抛出异常
     * @param answer_id 回答的id
     * @return 返回回答
     * @throws NotFoundException
     */
    @Override
    public QuestionAnswer getAnswer(Integer answer_id) throws NotFoundException{
        QuestionAnswer questionAnswer =  communityQAMapper.getAnswerById(answer_id);
        if(questionAnswer == null){
            throw new NotFoundException(404, 404, "没有找到该回答");//todo
        }else {
            return questionAnswer;
        }
    }
    private QuestionAnswer simplyGetAnswer(Integer answer_id) throws NotFoundException{
        QuestionAnswer questionAnswer = communityQAMapper.getAnswer(answer_id);
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
    public QuestionAnswer editAnswer(QuestionAnswer questionAnswer) throws NotFoundException{
        simplyGetAnswer(questionAnswer.getAnswer_id());
        communityQAMapper.editAnswer(questionAnswer);
        return communityQAMapper.getAnswerById(questionAnswer.getAnswer_id());
    }


    @Override
    @Transactional
    public void deleteAnswer(Integer answer_id) throws NotFoundException{
        simplyGetAnswer(answer_id);
        communityQAMapper.deleteAnswer(answer_id);
        communityQAMapper.deleteAllAnswerEvaluationByAnswerId(answer_id);
        communityQAMapper.deleteAllCommentsByAnswerId(answer_id);
        communityQAMapper.deleteAllCommentEvaluationByAnswerId(answer_id);
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
            throw new NotFoundException(404,404,"用户没有回答过问题");//todo
        }else {
            return questionAnswers;
        }
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


//    /**
//     * 拿到某个回答评价，如果没有，抛出异常
//     * @param evaluate_id 评价的id
//     * @return 返回得到的回答评价
//     * @throws NotFoundException
//     */
//    @Override
//    public Evaluate getAnswerEvaluate(Integer evaluate_id) throws NotFoundException{
//        Evaluate evaluate = communityQAMapper.getAnswerEvaluate(evaluate_id);
//        if(evaluate == null){
//            throw new NotFoundException(404, 404, "没有找到该答案评价");
//        }else{
//            return evaluate;
//        }
//    }

    @Override
    @Transactional
    public void evaluateAnswer(Integer answer_id, Integer user_id) throws NotFoundException{
        simplyGetAnswer(answer_id);
        Evaluate evaluate = communityQAMapper.evaluateStatus(user_id,answer_id);
        if(evaluate != null){
            communityQAMapper.reEvaluateAnswer(evaluate.getEvaluate_id());
        }else {
            Evaluate evaluate1 = new Evaluate();
            evaluate1.setUser_id(user_id);
            communityQAMapper.addEvaluateToAnswer(evaluate1);
            communityQAMapper.createMapBetweenAnswerAndEvaluate(evaluate1.getEvaluate_id(), answer_id);
        }
    }

    @Override
    public void deleteEvaluateAnswer(Integer answer_id, Integer user_id) throws NotFoundException {
        simplyGetAnswer(answer_id);
        Evaluate evaluate = communityQAMapper.evaluateStatus(user_id,answer_id);
        if(evaluate == null){
            throw new NotFoundException(404,404,"没有点赞过这个回答，不能取消");//todo
        }else {
            communityQAMapper.deleteEvaluateAnswer(evaluate.getEvaluate_id());
        }
    }

//    /**
//     * 得到用户赞同过的回答,没有的话抛出异常
//     * @param user_id 用户的id
//     * @return 返回用户赞同过的回答的list
//     * @throws NotFoundException
//     */
//    @Override
//    public List<QuestionAnswer> listMyAgreeAnswer(Integer user_id) throws NotFoundException {
//        List<QuestionAnswer> questionAnswers = communityQAMapper.listMyAgreeAnswer(user_id);
//        if(questionAnswers == null){
//            throw new NotFoundException(404,404,"该用户没有赞同的回答");
//        }else {
//            return questionAnswers;
//        }
//    }

    /**
     * 列出这个回答的所有评论
     * @param answer_id 回答的id
     * @return 返回包含评论对象的list
     * @throws NotFoundException
     */
    @Override
    public List<Comment> getAllAnswerComments(Integer answer_id) throws NotFoundException{
        simplyGetAnswer(answer_id);
        List<Comment> comments = communityQAMapper.listAllAnswerComment(answer_id);
        if(comments == null){
            throw new NotFoundException(404, 404, "找不到这个回答的评论");//todo
        }else {
            return comments;
        }
    }

    /**
     * 拿到某个评论
     * @param comment_id 评论的id
     * @return 返回评论对象
     * @throws NotFoundException
     */
    @Override
    public Comment getComment(Integer comment_id) throws NotFoundException{
        Comment comment = communityQAMapper.getComment(comment_id);
        if(comment == null){
            throw new NotFoundException(404,404,"找不到该评论");//todo
        }else {
            return comment;
        }
    }

    /**
     * 评论回答，如果回答不存在，抛出异常
     * @param answer_id 回答的id
     * @param comment 要添加的评论的对象
     * @return 评论成功返回1
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Integer addCommentToAnswer(Integer answer_id, Comment comment, Integer comment_level)
            throws NotFoundException{
        simplyGetAnswer(answer_id);
        communityQAMapper.addCommentToAnswer(comment);
        communityQAMapper.createMapBetweenAnswerAndComment(answer_id, comment.getComment_id(),comment_level);
        return 1;
    }


    @Override
    @Transactional
    public void deleteComment(Integer comment_id) throws  NotFoundException{
        getComment(comment_id);
        communityQAMapper.deleteComment(1);
        communityQAMapper.deleteAllCommentEvaluationByCommentId(1);
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




    @Override
    @Transactional
    public void evaluateComment(Integer comment_id, Integer user_id) throws NotFoundException{
        getComment(comment_id);
        CommentEvaluate commentEvaluate = communityQAMapper.commentEvaluateStatus(user_id, comment_id);
        if(commentEvaluate == null){
            CommentEvaluate commentEvaluate1 = new CommentEvaluate();
            commentEvaluate1.setUser_id(user_id);
            communityQAMapper.addEvaluateToComment(commentEvaluate1);
            communityQAMapper.createMapBetweenCommentAndEvaluate(commentEvaluate1.getEvaluate_id(), comment_id);
        }else {
            communityQAMapper.reEvaluateComment(commentEvaluate.getEvaluate_id());
        }
    }

    @Override
    public void deleteEvaluateComment(Integer comment_id, Integer user_id) throws NotFoundException {
        getComment(comment_id);
        CommentEvaluate commentEvaluate = communityQAMapper.commentEvaluateStatus(user_id, comment_id);
        if(commentEvaluate == null){
            throw new NotFoundException(404,404,"没用点赞过这个评论，无法取消！");//todo
        }else{
            communityQAMapper.deleteEvaluateComment(commentEvaluate.getEvaluate_id());
        }
    }

    //    /**
//     * 列出该评论的所有讨论
//     * @param comment_id 评论的id
//     * @return 返回包含所有讨论的list
//     * @throws NotFoundException
//     */
//    @Override
//    public List<Discuss> listAllCommentDiscuss(Integer comment_id) throws NotFoundException{
//        getComment(comment_id);
//        List<Discuss> discusses = communityQAMapper.listAllCommentDiscuss(comment_id);
//        if(discusses == null){
//            throw new NotFoundException(404,404,"没找到该问题的讨论");
//        }else {
//            return discusses;
//        }
//    }
//
//    /**
//     * 得到某个讨论
//     * @param discuss_id 讨论的id
//     * @return 返回该讨论对象
//     * @throws NotFoundException
//     */
//    @Override
//    public Discuss getDiscuss(Integer discuss_id) throws NotFoundException{
//        Discuss discuss = communityQAMapper.getDiscuss(discuss_id);
//        if(discuss == null){
//            throw new NotFoundException(404,404,"没有找到这个讨论");
//        }else {
//            return discuss;
//        }
//    }
//
//    /**
//     * 添加讨论，如果要讨论的评论不存在，则抛出异常
//     * @param comment_id 要讨论的评论的id
//     * @param discuss 要添加的讨论的对象
//     * @return 添加成功返回1
//     * @throws NotFoundException
//     */
//    @Override
//    @Transactional
//    public Integer addDiscuss(Integer comment_id, Discuss discuss, Integer discuss_level) throws NotFoundException{
//        getComment(comment_id);
//        communityQAMapper.addDiscuss(discuss);
//        communityQAMapper.createMapBetweenDiscussAndComment(discuss.getDiscuss_id(), comment_id,discuss_level);
//        return 1;
//    }
//
//    /**
//     * 列出该讨论的点赞数
//     * @param discuss_id 讨论的id
//     * @return 返回点赞数
//     */
//    @Override
//    public Integer countAgreementOfDiscuss(Integer discuss_id) {
//        return communityQAMapper.countDiscussAgreement(discuss_id);
//    }
//
//
//    @Override
//    @Transactional
//    public void deleteDiscuss(Integer discuss_id) throws NotFoundException{
//        getDiscuss(discuss_id);
//        communityQAMapper.deleteDiscuss(discuss_id);
//        communityQAMapper.deleteAllDiscussEvaluateByDiscussId(discuss_id);
//    }
//
//    /**
//     * 检查用户对该讨论的评价状态
//     * @param user_id 用户的id
//     * @param discuss_id 讨论的id
//     * @return 返回评价对象
//     * @throws NotFoundException 没有评价过。抛出异常
//     */
//    @Override
//    public DiscussEvaluate discussEvaluateStatus(Integer user_id, Integer discuss_id) throws NotFoundException {
//        DiscussEvaluate discussEvaluate = communityQAMapper.discussEvaluateStatus(user_id, discuss_id);
//        if(discussEvaluate == null){
//            throw new NotFoundException(404,404,"没有评价过");
//        }else {
//            return discussEvaluate;
//        }
//    }
//
//
//    /**
//     * 评价这个讨论，如果没有评价过，则添加评价并且建立映射；如果评价过，则更新字段
//     * @param discuss_id 讨论的id
//     * @param discussEvaluate 评价的对象
//     * @return 返回评价id
//     * @throws NotFoundException
//     */
//    @Override
//    @Transactional
//    public Integer evaluateDiscuss(Integer discuss_id, DiscussEvaluate discussEvaluate) throws NotFoundException{
//        getDiscuss(discuss_id);
//        Integer evaluate_id = discussEvaluate.getEvaluate_id();
//        if(evaluate_id == null){
//            communityQAMapper.addEvaluateToDiscuss(discussEvaluate);
//            evaluate_id = discussEvaluate.getEvaluate_id();
//            communityQAMapper.createMapBetweenDiscussAndEvaluate(evaluate_id, discuss_id);
//        }else {
//            communityQAMapper.reEvaluateDiscuss(discussEvaluate);
//        }
//        return evaluate_id;
//    }

//    /**
//     * 列出用户收到的邀请
//     * @param user_id 用户的id
//     * @return 返回收到的邀请的list
//     * @throws NotFoundException 如果没有，抛出异常
//     */
//    @Override
//    public List<AnswerInvitation> listInvitationGot(Integer user_id) throws NotFoundException {
//        List<AnswerInvitation> answerInvitations = communityQAMapper.listInvitationGot(user_id);
//        if(answerInvitations == null){
//            throw new NotFoundException(404,404,"还没有邀请");
//        }else {
//            return answerInvitations;
//        }
//    }

//
//    /**
//     * 列出用户邀请过的来回答这个问题的其他用户的id
//     * @param user_id 用户id
//     * @param ques_id 问题id
//     * @return 返回已经被邀请过的用户的id
//     * @throws NotFoundException 如果没有邀请过任何用户，抛出异常
//     */
//    @Override
//    public List<Integer> listUsersInvitedByMeToQuestion(Integer user_id, Integer ques_id) throws NotFoundException{
//        List<Integer> usersInvited = communityQAMapper.listUsersInvitedByMeToQuestion(user_id, ques_id);
//        if(usersInvited == null){
//            throw new NotFoundException(404,404,"你还没有邀请过用户回答这问题");
//        }else {
//            return usersInvited;
//        }
//    }
//
//
//    /**
//     * 得到某个邀请
//     * @param invit_id 邀请的id
//     * @return 如果没找到，则抛出异常，如果找到了，返回找到的邀请
//     * @throws NotFoundException
//     */
//    @Override
//    public AnswerInvitation getInvitation(Integer invit_id) throws NotFoundException{
//        AnswerInvitation answerInvitation = communityQAMapper.getInvitation(invit_id);
//        if(answerInvitation == null){
//            throw new NotFoundException(404,404,"没有找到该邀请");
//        }else {
//            return answerInvitation;
//        }
//    }
//
//    /**
//     * 接受或者拒绝某个邀请
//     * @param answerInvitation 邀请的对象
//     * @return 更改成功返回1, 没找到邀请，抛出异常
//     * @throws NotFoundException
//     */
//    @Override
//    public Integer acceptOrRefuseInvitation(AnswerInvitation answerInvitation) throws NotFoundException{
//        getInvitation(answerInvitation.getInvit_id());
//        communityQAMapper.updateStatusOfInvitation(answerInvitation);
//        return 1;
//    }



    /**
     * 拿到前十个视频
     * @return 返回前十个视频的list
     * @throws NotFoundException 没有找到抛出异常
     */
    @Override
    public List<Video> listFirstTenVideos() throws NotFoundException{
        List<Video> videos= communityQAMapper.listFirstTenVideos();
        if(videos == null){
            throw new NotFoundException(404,404,"没有任何视频");//todo
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
            throw new NotFoundException(404,404,"该用户没有发过视频");//todo
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
    public Video addVideo(Video video, Integer user_id, Integer rela_type, Integer rela_id) {
        communityQAMapper.addVideo(video);
        communityQAMapper.createMapBetweenVideoAndUser(video.getVideo_id(), user_id, rela_type, rela_id);
        return video;
    }

    private void simplyGetVideo(Integer video_id) throws NotFoundException{
        Video video = communityQAMapper.getVideo(video_id);
        if(video == null){
            throw new NotFoundException(404,404,"没有找到这个视频");//todo
        }
    }

    /**
     * 拿到某个视频，如果视频不存在，则抛出异常
     * @param video_id 视频的id
     * @return 返回得到的视频对象
     * @throws NotFoundException
     */
    @Override
    public Video getVideo(Integer video_id) throws NotFoundException {
        Video video = communityQAMapper.getVideoById(video_id);
        if(video == null){
            throw new NotFoundException(404, 404, "没有找到该视频");//todo
        }else {
            return video;
        }
    }


    @Override
    @Transactional
    public void deleteVideo(Integer video_id) throws NotFoundException{
        simplyGetVideo(video_id);
        communityQAMapper.deleteVideo(video_id);
        communityQAMapper.deleteAllVideoAttention(video_id);
        communityQAMapper.deleteAllVideoEvaluate(video_id);
        communityQAMapper.deleteAllVideoComment(video_id);
    }

    @Override
    @Transactional
    public VideoAttention attentionVideo(Integer user_id, Integer video_id) throws NotFoundException{
        simplyGetVideo(video_id);
        VideoAttention videoAttention = communityQAMapper.videoAttentionStatus(video_id,user_id);
        if(videoAttention == null){
            VideoAttention videoAttention1 = new VideoAttention();
            videoAttention1.setUser_id(user_id);
            communityQAMapper.addAttentionToVideo(videoAttention1);
            communityQAMapper.createMapBetweenAttentionAndVideo(videoAttention1.getAtten_id(), video_id);
        }else{
            communityQAMapper.reAddAttentionToVideo(videoAttention.getAtten_id());
        }
        return videoAttention;
    }

    @Override
    @Transactional
    public void cancelAttenVideo(Integer user_id, Integer video_id) throws NotFoundException {
        VideoAttention videoAttention = communityQAMapper.videoAttentionStatus(video_id, user_id);
        if(videoAttention == null){
            throw new NotFoundException(404,404,"没有关注过这个视频，无法取消");//todo
        }else {
            communityQAMapper.cancelAttentionVideo(videoAttention.getAtten_id());
        }
    }


    @Override
    @Transactional
    public VideoComment commentVideo(VideoComment videoComment, Integer video_id, Integer comment_level) throws NotFoundException{
        simplyGetVideo(video_id);
        communityQAMapper.addCommentToVideo(videoComment);
        communityQAMapper.createMapBetweenCommentAndVideo(videoComment.getComment_id(), video_id, comment_level);
        return videoComment;
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
            throw new NotFoundException(404, 404, "没找到这条视频评论");//todo
        }else {
            return videoComment;
        }
    }


    @Override
    @Transactional
    public void deleteVideoComment(Integer comment_id) throws NotFoundException{
        getVideoComment(comment_id);
        communityQAMapper.deleteVideoComment(comment_id);
    }


    @Transactional
    @Override
    public VideoEvaluate evaluateVideo(Integer user_id, Integer video_id) throws NotFoundException {
        simplyGetVideo(video_id);
        VideoEvaluate videoEvaluate = communityQAMapper.videoEvaluateStatus(video_id,user_id);
        if(videoEvaluate == null){
            VideoEvaluate videoEvaluate1 = new VideoEvaluate();
            communityQAMapper.addEvaluationToVideo(videoEvaluate1);
            communityQAMapper.createMapBetweenEvaluationAndVideo(videoEvaluate1.getEvaluate_id(), video_id);
        }else{
            communityQAMapper.reEvaluateVideo(videoEvaluate.getEvaluate_id());
        }
        return videoEvaluate;
    }

    @Override
    @Transactional
    public void cancelEvaluateVideo(Integer user_id, Integer video_id) throws NotFoundException {
        VideoEvaluate videoEvaluate = communityQAMapper.videoEvaluateStatus(video_id,user_id);
        if(videoEvaluate == null){
            throw new NotFoundException(404,404,"没有点赞过这个视频，无法取消点赞");//todo
        }else {
            communityQAMapper.cancelEvaluateVideo(videoEvaluate.getEvaluate_id());
        }
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
