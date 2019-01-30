package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.PersonInfluenceMapper;
import com.youthchina.domain.Qinghong.Student;
import com.youthchina.domain.jinhao.communityQA.AnswerEvaluate;
import com.youthchina.domain.jinhao.communityQA.CommentEvaluate;
import com.youthchina.domain.jinhao.communityQA.DiscussEvaluate;
import com.youthchina.domain.jinhao.communityQA.VideoEvaluate;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.domain.tianjian.ComReplyEvaluate;
import com.youthchina.domain.tianjian.PersonInfluence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class CaculatePersonInfluencePoint {
    @Autowired
    PersonInfluenceMapper personInfluenceMapper;

    private PersonInfluence personInfluence;
    private Integer pers_profile = 0;
    private Integer pers_ident_verify = 0;
    private Integer pers_university = 0;
    private Integer pers_work = 0;
    private Integer pers_friend_count = 0;
    private Float pers_friend_quality = 0.0f;
    private Integer pers_interaction = 0;
    private Integer pers_like_count = 0;
    private Integer pers_posi_evaluate = 0;

    private Float pers_profile_rate;
    private Float pers_ident_verify_rate;
    private Float pers_university_rate;
    private Float pers_work_rate;
    private Float pers_friend_count_rate;
    private Float pers_friend_quality_rate;
    private Float pers_interaction_rate;
    private Float pers_like_count_rate;
    private Float pers_posi_evaluate_rate;



    public CaculatePersonInfluencePoint( @Value("${pers_profile_rate}")String pers_profile_rate,
                                         @Value("${pers_ident_verify_rate}")String pers_ident_verify_rate,
                                         @Value("${pers_university_rate}")String pers_university_rate,
                                         @Value("${pers_work_rate}")String pers_work_rate,
                                         @Value("${pers_friend_count_rate}")String pers_friend_count_rate,
                                         @Value("${pers_friend_quality_rate}")String pers_friend_quality_rate,
                                         @Value("${pers_interaction_rate}")String pers_interaction_rate,
                                         @Value("${pers_like_count_rate}")String pers_like_count_rate,
                                         @Value("${pers_posi_evaluate_rate}")String pers_posi_evaluate_rate){
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

    //IsInJob CurrentCompanyName Activities Certificates AdvantageLabels SubInfo Works Projects EducationInfo IntroductionVideo
    public Integer caculatePersonInfluencePointPersonalProfile(Student student){
        if(student.getIsInJob()!=null){
            pers_profile++;
        }
        if (student.getCurrentCompanyName()!=null||student.getCurrentCompanyName()!=""){
            pers_profile++;
        }
        if(student.getActivities()!=null){
            pers_profile++;
        }
        if(student.getCertificates()!=null){
            pers_profile++;
        }
        if(student.getAdvantageLabels()!=null){
            pers_profile++;
        }
        if(student.getSubInfo()!=null){
            pers_profile++;
        }
        if(student.getWorks()!=null){
            pers_profile++;
        }
        if(student.getProjects()!=null){
            pers_profile++;
        }
        if(student.getEducationInfos()!=null){
            pers_profile++;
        }
        if(student.getIntroductionVideo()!=null){
            pers_profile++;
        }
        return pers_profile*10;
    }

    public Integer caculatePersonInfluencePointIdentifyValidation(Student student){
        if(student.getPhonenumber()!=null){
            pers_ident_verify += 50;
        }
        if(student.getEmail()!=null){
            pers_ident_verify += 50;
        }
            return pers_ident_verify;
    }

    public Integer caculatePersonInfluencePointUniversity(Student student){
        return pers_university;
    }
    public Integer caculatePersonInfluencePointWork(Student student){

        return pers_work;
    }

    public Integer caculatePersonInfluenceFriendCount(List<ComFriendRelation> friendRelationList){
        if(friendRelationList.size()<100){
            pers_friend_count = friendRelationList.size();
        }else{
            pers_friend_count = 100;
        }
        return pers_friend_count;
    }

    public Float caculatePersonInfluenceFriendQuality(List<PersonInfluence> friendQualityList){
            Iterator it = friendQualityList.iterator();
            while(it.hasNext()){
                PersonInfluence personInfluence = (PersonInfluence) it.next();
                pers_friend_quality += personInfluence.getPers_total();
            }
            pers_friend_quality = pers_friend_quality/friendQualityList.size();
        return pers_friend_quality;
    }

    public Integer caculatePersonInfluenceInteraction(List<AnswerEvaluate> AnswerEvaluateList,
                                                      List<CommentEvaluate> commentEvaluateList,
                                                      List<DiscussEvaluate> discussEvaluatesList,
                                                      List<VideoEvaluate> videoEvaluateList,
                                                      List<ComReplyEvaluate> comReplyEvaluateList){


        return pers_interaction;
    }

    public Integer caculatePersonInfluenceLikeCount(List<AnswerEvaluate> AnswerEvaluateList,
                                                      List<CommentEvaluate> commentEvaluateList,
                                                      List<DiscussEvaluate> discussEvaluatesList,
                                                      List<VideoEvaluate> videoEvaluateList,
                                                      List<ComReplyEvaluate> comReplyEvaluateList){
        pers_interaction += AnswerEvaluateList.size();
        pers_interaction += commentEvaluateList.size();
        pers_interaction += discussEvaluatesList.size();
        pers_interaction += videoEvaluateList.size();
        pers_interaction += comReplyEvaluateList.size();

        if(pers_interaction<100){
            return pers_interaction;
        }else{
            return 100;
        }
    }

    public Integer caculatePersonInfluencePosiEvaluate(){
       return pers_posi_evaluate;
    }

    public PersonInfluence caculatePersonInfluencePoint(Integer user_id){
        return personInfluence;
    }
}
