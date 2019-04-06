package com.youthchina.service.Qinghong;

import com.youthchina.domain.Qinghong.Candidate;
import com.youthchina.domain.Qinghong.Invitation;

import java.util.List;

public interface EmployerPageService {

    Candidate findCandidate(Integer id);

    List<Candidate> getByLabelOne(String talent_label_one);

    List<Candidate> getByLabelTwo(String talent_label_two);

    List<Candidate> getByLabelThree(String talent_label_three);

    List<Candidate> getByLabel(String talent_label);

    List<Candidate> getByIntentionOne(String job_intention_one);

    List<Candidate> getByIntentionTwo(String job_intention_two);

    List<Candidate> getByIntentionThree(String job_intention_three);

    List<Candidate> getByIntention(String job_intention);

    List<Candidate> getByTime(String duty_time);

    List<Candidate> getByIndustryOne(String desire_industry_one);

    List<Candidate> getByIndustryTwo(String desire_industry_two);

    List<Candidate> getByIndustryThree(String desire_industry_three);

    List<Candidate> getByIndustry(String desire_industry);

    List<Candidate> getByLocationOne(String desire_location_one);

    List<Candidate> getByLocationTwo(String desire_location_two);

    List<Candidate> getByLocationThree(String desire_location_three);

    List<Candidate> getByLocation(String desire_location);

    Integer insertCandidate(Candidate candidate);

    Integer updateCandidate(Candidate candidate);

    Integer deleteCandidate(Integer id);

    List<Candidate> getAllCandidate();

    Integer getAllCandidateCount();

    Invitation getInvitation(Integer id);


}
