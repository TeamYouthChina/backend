package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.PersonInfluenceMapper;
import com.youthchina.domain.tianjian.PersonInfluencePoint;
import org.springframework.beans.factory.annotation.Autowired;

/*
* 个人资料完善+身份认证材料 Personal information perfection + Identity validation 10%
* 学校排名  Rank of University 20%
* 工作实习公司 Job and company of Internship 20%
*
* 好友数量 friends 10%
* 好友质量 Average PersonInfluencePoint of friends 10%
* 互动关系 like answer or remark to each other 10%
*
* 点赞数 The answers or articles are liked by others 10%
* 公司&职位评价 Evaluation of company or job 10%
* */
public class CaculatePersonInfluencePoint {
    @Autowired
    PersonInfluenceMapper personInfluenceMapper;


    private PersonInfluencePoint personInfluencePoint;

    public CaculatePersonInfluencePoint(){
        personInfluencePoint = new PersonInfluencePoint();
    }
    public int caculatePersonInfluencePoint(Integer user_id){
        return 0;
    }
}
