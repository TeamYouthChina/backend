package com.youthchina.service.tianjian;

import com.youthchina.dao.jinhao.InfluenceMapper;
import com.youthchina.dao.tianjian.PersonInfluenceMapper;
import com.youthchina.domain.Qinghong.EducationInfo;
import com.youthchina.domain.Qinghong.Student;
import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.domain.tianjian.ComEssayReply;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.domain.tianjian.ComReplyEvaluate;
import com.youthchina.domain.tianjian.PersonInfluence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
* 个人资料完善+身份认证材料 Personal information perfection + Identity validation 10%
* 学校排名  Rank of University 20%
* 工作实习公司 Job and company of Internship 20%
*
* 好友数量 friends 10%
* 好友质量 Average PersonInfluence of friends 10%
* 互动关系 like answer or remark to each other 10%
*
* 点赞数 The answers or articles are liked by others 10%
* 公司&职位评价 Evaluation of company or job 10%
* */
@Service
public class CaculatePersonInfluencePoint {
    @Autowired
    PersonInfluenceMapper personInfluenceMapper;

    private PersonInfluence personInfluence;
    private Float pers_profile = 0.0f;
    private Float pers_ident_verify = 0.0f;
    private Float pers_university = 0.0f;
    private Float pers_work = 0.0f;
    private Float pers_friend_count = 0.0f;
    private Float pers_friend_quality = 0.0f;
    private Float pers_interaction = 0.0f;
    private Float pers_like_count = 0.0f;
    private Float pers_posi_evaluate = 0.0f;

    private Float pers_profile_rate;
    private Float pers_ident_verify_rate;
    private Float pers_university_rate;
    private Float pers_work_rate;
    private Float pers_friend_count_rate;
    private Float pers_friend_quality_rate;
    private Float pers_interaction_rate;
    private Float pers_like_count_rate;
    private Float pers_posi_evaluate_rate;

    @Autowired
    InfluenceMapper influenceMapper;

    public CaculatePersonInfluencePoint( @Value("${pers_profile_rate}")String pers_profile_rate,
                                         @Value("${pers_ident_verify_rate}")String pers_ident_verify_rate,
                                         @Value("${pers_university_rate}")String pers_university_rate,
                                         @Value("${pers_work_rate}")String pers_work_rate,
                                         @Value("${pers_friend_count_rate}")String pers_friend_count_rate,
                                         @Value("${pers_friend_quality_rate}")String pers_friend_quality_rate,
                                         @Value("${pers_interaction_rate}")String pers_interaction_rate,
                                         @Value("${pers_like_count_rate}")String pers_like_count_rate,
                                         @Value("${pers_posi_evaluate_rate}")String pers_posi_evaluate_rate) {
        this.pers_profile_rate = Float.parseFloat(pers_profile_rate);
        this.pers_ident_verify_rate = Float.parseFloat(pers_ident_verify_rate);
        this.pers_university_rate = Float.parseFloat(pers_university_rate);
        this.pers_work_rate = Float.parseFloat(pers_work_rate);
        this.pers_friend_count_rate = Float.parseFloat(pers_friend_count_rate);
        this.pers_friend_quality_rate = Float.parseFloat(pers_friend_quality_rate);
        this.pers_interaction_rate = Float.parseFloat(pers_interaction_rate);
        this.pers_like_count_rate = Float.parseFloat(pers_like_count_rate);
        this.pers_posi_evaluate_rate = Float.parseFloat(pers_posi_evaluate_rate);

        personInfluence = new PersonInfluence();

    }

    /*
     * 计算个人资料完善度得分
     * IsInJob CurrentCompanyName Activities Certificates AdvantageLabels SubInfo Works Projects EducationInfo IntroductionVideo
     * */
    public static Integer caculatePersonInfluencePointPersonalProfile(Student student){
        Integer pers_profile_in = new Integer(0);
        if(student!=null){
            if(student.getIsInJob()!=null){
                pers_profile_in++;
            }
            if (student.getCurrentCompanyName()!=null||student.getCurrentCompanyName()!=""){
                pers_profile_in++;
            }
            if(student.getActivities()!=null){
                pers_profile_in++;
            }
            if(student.getCertificates()!=null){
                pers_profile_in++;
            }
            if(student.getAdvantageLabels()!=null){
                pers_profile_in++;
            }
            if(student.getSubInfo()!=null){
                pers_profile_in++;
            }
            if(student.getWorks()!=null){
                pers_profile_in++;
            }
            if(student.getProjects()!=null){
                pers_profile_in++;
            }
            if(student.getEducationInfos()!=null){
                pers_profile_in++;
            }
            if(student.getIntroductionVideo()!=null){
                pers_profile_in++;
            }
        }else{
            pers_profile_in = 0;
        }

        return pers_profile_in * 10;
    }

    /*
    * 计算认证程度得分
    * */
    public static Integer caculatePersonInfluencePointIdentifyValidation(Student student){
        Integer pers_ident_verify_in = new Integer(0);
        if(student!=null){
            if(student.getPhonenumber()!=null){
                pers_ident_verify_in += 50;
            }
            if(student.getEmail()!=null){
                pers_ident_verify_in += 50;
            }
        }
            return pers_ident_verify_in;
    }

    /*
    * 计算大学排名得分
    * */
    public static Integer caculatePersonInfluencePointUniversity(Integer universityRank){
        Integer pers_university_in = new Integer(0);
        if(universityRank!=null){
            if(universityRank<=20&&universityRank>0){
                pers_university_in = 100;
            }else if(universityRank<=100&&universityRank>20){
                pers_university_in = 95;
            }else if(universityRank<=200&&universityRank>100){
                pers_university_in = 90;
            }else if(universityRank<=300&&universityRank>200){
                pers_university_in = 85;
            }else if(universityRank<=400&&universityRank>300){
                pers_university_in = 80;
            }else if(universityRank<=500&&universityRank>400){
                pers_university_in = 75;
            }else if(universityRank<=600&&universityRank>500){
                pers_university_in = 70;
            }else if(universityRank<=700&&universityRank>600){
                pers_university_in = 65;
            }else if(universityRank<=800&&universityRank>700){
                pers_university_in = 60;
            }else if(universityRank<=1000&&universityRank>800){
                pers_university_in = 55;
            }else if(universityRank<=1250&&universityRank>1000){
                pers_university_in = 50;
            }else {
                pers_university_in = 40;
            }
        }

        return pers_university_in;
    }

    /*
    * 计算工作公司最高的排名
    * */
    public static Integer caculatePersonInfluencePointWork(Integer comanyRank){
        Integer pers_work_in = new Integer(0);
        if(comanyRank!=null){
            if(comanyRank<=10&&comanyRank>0){
                pers_work_in = 100;
            }else if(comanyRank<=20&&comanyRank>10){
                pers_work_in = 95;
            }else if(comanyRank<=50&&comanyRank>20){
                pers_work_in = 90;
            }else if(comanyRank<=100&&comanyRank>50){
                pers_work_in = 85;
            }else if(comanyRank<=200&&comanyRank>100){
                pers_work_in = 80;
            }else if(comanyRank<=300&&comanyRank>200){
                pers_work_in = 75;
            }else if(comanyRank<=400&&comanyRank>300){
                pers_work_in = 70;
            }else if(comanyRank<=500&&comanyRank>400){
                pers_work_in = 65;
            }else{
                pers_work_in = 50;
            }
        }

        return pers_work_in;
    }

    /*
    * 计算好友数量得分
    * */
    public static Integer caculatePersonInfluenceFriendCount(List<ComFriendRelation> friendRelationList){
        Integer pers_friend_count_in = new Integer(0);
        if(friendRelationList!=null){
            if(friendRelationList!=null){
                if(friendRelationList.size()<100){
                    pers_friend_count_in = friendRelationList.size();
                }else{
                    pers_friend_count_in = 100;
                }
            }
        }
        return pers_friend_count_in;
    }

    /*
    * 计算所有好友影响力得分
    * */
    public static Float caculatePersonInfluenceFriendQuality(List<Float> TotalFriendInfluenceList){
            Float pers_friend_quality_in = new Float(0);
            if(TotalFriendInfluenceList != null && TotalFriendInfluenceList.size() != 0){
                Iterator it = TotalFriendInfluenceList.iterator();
                while(it.hasNext()){
                    Float i = (Float) it.next();
                    pers_friend_quality_in += i;
                }
                pers_friend_quality_in = pers_friend_quality_in / TotalFriendInfluenceList.size();
            }

        return pers_friend_quality_in;
    }

    /*
    * 计算好友互动得分
    * */
    public static Integer caculatePersonInfluenceInteraction(List<AnswerEvaluate> AnswerEvaluateList,
                                                      List<CommentEvaluate> commentEvaluateList,
                                                      List<DiscussEvaluate> discussEvaluatesList,
                                                      List<VideoEvaluate> videoEvaluateList,
                                                      List<ComReplyEvaluate> comReplyEvaluateList,
                                                      List<QuestionAnswer> questionAnswersList,
                                                      List<AnswerComment> answerCommentList,
                                                      List<VideoComment> videoCommentList,
                                                      List<ComEssayReply> comEssayReplyList){
        Integer pers_interaction_in = new Integer(0);

        if(AnswerEvaluateList!=null){
            pers_interaction_in += AnswerEvaluateList.size();
        }
        if(commentEvaluateList!=null){
            pers_interaction_in += commentEvaluateList.size();
        }
        if(discussEvaluatesList!=null){
            pers_interaction_in += discussEvaluatesList.size();
        }
        if(videoEvaluateList!=null){
            pers_interaction_in += videoEvaluateList.size();
        }
        if(comReplyEvaluateList!=null){
            pers_interaction_in += comReplyEvaluateList.size();
        }
        if(questionAnswersList!=null){
            pers_interaction_in += questionAnswersList.size();
        }
        if(answerCommentList!=null){
            pers_interaction_in += answerCommentList.size();
        }
        if(videoCommentList!=null){
            pers_interaction_in += videoCommentList.size();
        }
        if(comEssayReplyList!=null){
            pers_interaction_in += comEssayReplyList.size();
        }

        return pers_interaction_in;
    }

    /*
    * 计算被点赞数得分
    * */
    public static Integer caculatePersonInfluenceLikeCount(List<AnswerEvaluate> answerEvaluateList,
                                                      List<CommentEvaluate> commentEvaluateList,
                                                      List<DiscussEvaluate> discussEvaluatesList,
                                                      List<VideoEvaluate> videoEvaluateList,
                                                      List<ComReplyEvaluate> comReplyEvaluateList){
        Integer pers_like_count_in = new Integer(0);
        if(answerEvaluateList!=null){
            pers_like_count_in += answerEvaluateList.size();
        }
        if(commentEvaluateList!=null){
            pers_like_count_in += commentEvaluateList.size();
        }
        if(discussEvaluatesList!=null){
            pers_like_count_in += discussEvaluatesList.size();
        }
        if(videoEvaluateList!=null){
            pers_like_count_in += videoEvaluateList.size();
        }
        if(comReplyEvaluateList!=null){
            pers_like_count_in += comReplyEvaluateList.size();
        }

        if(pers_like_count_in<100){
            return pers_like_count_in;
        }else{
            return 100;
        }
    }

    /*
    * 计算对公司进行评价得分
    * */
    public static Integer caculatePersonInfluencePosiEvaluate(){
        Integer pers_posi_evaluate_in = new Integer(100);
        return pers_posi_evaluate_in;
    }

    /*
    * 计算个人影响力总分
    * */
    public PersonInfluence caculatePersonInfluencePoint(Integer user_id){
        Influence influence = influenceMapper.getInfluenceByUserId(user_id);
        List<Influence> influenceInteraction = new ArrayList<Influence>();
        pers_profile = caculatePersonInfluencePointPersonalProfile(influence.getStudent()) * pers_profile_rate;
        pers_ident_verify = caculatePersonInfluencePointIdentifyValidation(influence.getStudent()) * pers_ident_verify_rate;
        List<EducationInfo> educationInfos = influence.getStudent().getEducationInfos();
        Integer universityRank = influenceMapper.getBestEducation(educationInfos);
        pers_university = caculatePersonInfluencePointUniversity(universityRank) * pers_university_rate;

        Integer companyRank = influenceMapper.getBestWork(influence.getStudent().getWorks());
        pers_work= caculatePersonInfluencePointWork(companyRank) * pers_work_rate;
        pers_friend_count = caculatePersonInfluenceFriendCount(influence.getComFriendRelations()) * pers_friend_count_rate;

        List<Float> friendInfluenceTotal = new ArrayList<Float>();
        List<ComFriendRelation> friendRelationList = influence.getComFriendRelations();
        Iterator it = friendRelationList.iterator();
        while(it.hasNext()){
            ComFriendRelation comFriendRelation = (ComFriendRelation) it.next();
            Integer friendId = comFriendRelation.getUser_id();
            Float friendtotal = influenceMapper.getFriendInfluencePoints(friendId);
            if(friendtotal != null){
                friendInfluenceTotal.add(friendtotal);
            }
            Influence influence1 = influenceMapper.getInteraction(user_id,friendId);
            if(influence1 != null){
                influenceInteraction.add(influence1);
            }
        }

        pers_friend_quality = caculatePersonInfluenceFriendQuality(friendInfluenceTotal) * pers_friend_quality_rate;

        Integer interaction = new Integer(0);
        Iterator itInteraction = influenceInteraction.iterator();
        while(itInteraction.hasNext()){
            Influence influenceinteraction = (Influence) itInteraction.next();
            interaction += caculatePersonInfluenceInteraction(influenceinteraction.getAnswerEvaluates(),
                    influenceinteraction.getCommentEvaluates(),
                    influenceinteraction.getDiscussEvaluates(),
                    influenceinteraction.getVideoEvaluates(),
                    influenceinteraction.getComReplyEvaluates(),
                    influenceinteraction.getQuestionAnswers(),
                    influenceinteraction.getAnswerComments(),
                    influenceinteraction.getVideoComments(),
                    influenceinteraction.getComEssayReplies());
        }
        if(interaction * 3 <= 100){
            pers_interaction = interaction * pers_interaction_rate;
        }else{
            pers_interaction = 100 * pers_interaction_rate;
        }

        pers_like_count = caculatePersonInfluenceLikeCount(influence.getAnswerEvaluates(),
                                                           influence.getCommentEvaluates(),
                                                           influence.getDiscussEvaluates(),
                                                           influence.getVideoEvaluates(),
                                                           influence.getComReplyEvaluates())*pers_like_count_rate;
        pers_posi_evaluate = caculatePersonInfluencePosiEvaluate()*pers_posi_evaluate_rate;

        personInfluence.setPers_profile(pers_profile);
        personInfluence.setPers_ident_verify(pers_ident_verify);
        personInfluence.setPers_university(pers_university);
        personInfluence.setPers_work(pers_work);
        personInfluence.setPers_friend_count(pers_friend_count);
        personInfluence.setPers_friend_quality(pers_friend_quality);
        personInfluence.setPers_interaction(pers_interaction);
        personInfluence.setPers_like_count(pers_like_count);
        personInfluence.setPers_posi_evaluate(pers_posi_evaluate);
        Float averageTotal = (pers_profile + pers_ident_verify + pers_university + pers_work + pers_friend_count +
                pers_friend_quality + pers_interaction + pers_like_count + pers_posi_evaluate);
        personInfluence.setPers_total(averageTotal);

        return personInfluence;
    }
}
