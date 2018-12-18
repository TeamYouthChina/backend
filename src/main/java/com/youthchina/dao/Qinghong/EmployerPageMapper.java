package com.youthchina.dao.Qinghong;

import com.youthchina.domain.Qinghong.Candidate;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface EmployerPageMapper {
    Candidate findCandidate(Integer id);
    List<Candidate> findByLabelOne(String talent_label_one);
    List<Candidate> findByLabelTwo(String talent_label_two);
    List<Candidate> findByLabelThree(String talent_label_three);
    List<Candidate> findByLabel(String talent_label);
    List<Candidate> findByIntentionOne(String job_intention_one);
    List<Candidate> findByIntentionTwo(String job_intention_two);
    List<Candidate> findByIntentionThree(String job_intention_three);
    List<Candidate> findByIntention(String job_intention);
    List<Candidate> findByTime(String duty_time);
    List<Candidate> findByIndustryOne(String desire_industry_one);
    List<Candidate> findByIndustryTwo(String desire_industry_two);
    List<Candidate> findByIndustryThree(String desire_industry_three);
    List<Candidate> findByIndustry(String desire_industry);
    List<Candidate> findByLocationOne(String desire_location_one);
    List<Candidate> findByLocationTwo(String desire_location_two);
    List<Candidate> findByLocationThree(String desire_location_three);
    List<Candidate> findByLocation(String desire_location);

    Integer updateCandidate(Candidate candidate);
    Integer deleteCandidate(Integer id);
    Integer insertCandidate(Candidate candidate);
    List<Candidate> getAllCandidate();
    Integer getAllCandidateCount();


}

