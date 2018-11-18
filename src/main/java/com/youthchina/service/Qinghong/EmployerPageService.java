package com.youthchina.service.Qinghong;

import com.youthchina.domain.Qinghong.Candidate;

import javax.annotation.Resource;

public interface EmployerPageService {

    Candidate findCandidate(Integer id);
    Candidate getByLabelOne(String talent_label_one);
    Candidate getByLabelTwo(String talent_label_two);
    Candidate getByLabelThree(String talent_label_three);
    Candidate getByIntentionOne(String job_intention_one);
    Candidate getByIntentionTwo(String job_intention_two);
    Candidate getByIntentionThree(String job_intention_three);
    Candidate getByTime(String duty_time);
    Candidate getByIndustryOne(String desire_industry_one);
    Candidate getByIndustryTwo(String desire_industry_two);
    Candidate getByIndustryThree(String desire_industry_three);
    Candidate getByLocationOne(String desire_location_one);
    Candidate getByLocationTwo(String desire_location_two);
    Candidate getByLocationThree(String desire_location_three);


}
