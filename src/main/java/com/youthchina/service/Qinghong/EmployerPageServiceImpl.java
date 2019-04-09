package com.youthchina.service.Qinghong;

/*
import com.youthchina.dao.Qinghong.EmployerPageMapper;
import com.youthchina.dao.Qinghong.InvitationMapper;
import com.youthchina.domain.Qinghong.Candidate;
import com.youthchina.domain.Qinghong.Invitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployerPageServiceImpl implements EmployerPageService {

    @Autowired
    private EmployerPageMapper employerPageMapper;
    @Autowired
    private InvitationMapper invitationMapper;

    @Override
    public Candidate findCandidate(Integer id) {
        return employerPageMapper.findCandidate(id);
    }

    @Override
    public List<Candidate> getByLabelOne(String talent_label_one) {
        return employerPageMapper.findByLabelOne(talent_label_one);
    }

    @Override
    public List<Candidate> getByLabelTwo(String talent_label_two) {
        return employerPageMapper.findByLabelTwo(talent_label_two);
    }

    @Override
    public List<Candidate> getByLabelThree(String talent_label_three) {
        return employerPageMapper.findByLabelThree(talent_label_three);
    }

    public List<Candidate> getByLabel(String talent_label) {
        return employerPageMapper.findByLabel(talent_label);
    }


    @Override
    public List<Candidate> getByIntentionOne(String job_intention_one) {
        return employerPageMapper.findByIntentionOne(job_intention_one);
    }

    @Override
    public List<Candidate> getByIntentionTwo(String job_intention_two) {
        return employerPageMapper.findByIntentionTwo(job_intention_two);
    }


    @Override
    public List<Candidate> getByIntentionThree(String job_intention_three) {
        return employerPageMapper.findByIntentionThree(job_intention_three);
    }

    public List<Candidate> getByIntention(String job_intention) {
        return employerPageMapper.findByIntention(job_intention);

    }

    @Override
    public List<Candidate> getByTime(String duty_time) {
        return employerPageMapper.findByTime(duty_time);
    }

    @Override
    public List<Candidate> getByIndustryOne(String desire_industry_one) {
        return employerPageMapper.findByIndustryOne(desire_industry_one);
    }

    @Override
    public List<Candidate> getByIndustryTwo(String desire_industry_two) {
        return employerPageMapper.findByIndustryTwo(desire_industry_two);
    }

    @Override
    public List<Candidate> getByIndustryThree(String desire_industry_three) {
        return employerPageMapper.findByIndustryThree(desire_industry_three);
    }

    @Override
    public List<Candidate> getByIndustry(String desire_industry) {
        return employerPageMapper.findByIndustry(desire_industry);
    }

    @Override
    public List<Candidate> getByLocationOne(String desire_location_one) {
        return employerPageMapper.findByLocationOne(desire_location_one);
    }

    @Override
    public List<Candidate> getByLocationTwo(String desire_location_two) {
        return employerPageMapper.findByLocationTwo(desire_location_two);
    }

    @Override
    public List<Candidate> getByLocationThree(String desire_location_three) {
        return employerPageMapper.findByLocationThree(desire_location_three);
    }

    @Override
    public List<Candidate> getByLocation(String desire_location) {
        return employerPageMapper.findByLocation(desire_location);
    }

    @Override
    public Integer insertCandidate(Candidate candidate) {
        return employerPageMapper.insertCandidate(candidate);
    }

    @Override
    public Integer updateCandidate(Candidate candidate) {
        return employerPageMapper.updateCandidate(candidate);
    }

    @Override
    public Integer deleteCandidate(Integer id) {
        return employerPageMapper.deleteCandidate(id);
    }

    @Override
    public List<Candidate> getAllCandidate() {
        return employerPageMapper.getAllCandidate();
    }

    @Override
    public Integer getAllCandidateCount() {
        return employerPageMapper.getAllCandidateCount();
    }

    @Override
    public Invitation getInvitation(Integer id) {
        return invitationMapper.findInvitationMapper(id);
    }
}
*/