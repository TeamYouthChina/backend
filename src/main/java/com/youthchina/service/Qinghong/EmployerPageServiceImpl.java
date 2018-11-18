package com.youthchina.service.Qinghong;

import com.youthchina.dao.Qinghong.EmployerPageMapper;
import com.youthchina.domain.Qinghong.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerPageServiceImpl implements EmployerPageService {

    @Autowired
    private EmployerPageMapper employerPageMapper;

    @Override
    public Candidate findCandidate(Integer id) {
        return employerPageMapper.findCandidate(id);
    }

    @Override
    public Candidate getByLabelOne(String talent_label_one) {
        return employerPageMapper.findByLabelOne(talent_label_one);
    }

    @Override
    public Candidate getByLabelTwo(String talent_label_two) {
        return employerPageMapper.findByLabelTwo(talent_label_two);
    }

    @Override
    public Candidate getByLabelThree(String talent_label_three) {
        return employerPageMapper.findByLabelThree(talent_label_three);
    }

    @Override
    public Candidate getByIntentionOne(String job_intention_one) {
        return employerPageMapper.findByIntentionOne(job_intention_one);
    }

    @Override
    public Candidate getByIntentionTwo(String job_intention_two) {
        return employerPageMapper.findByIntentionTwo(job_intention_two);
    }

    @Override
    public Candidate getByIntentionThree(String job_intention_three) {
        return employerPageMapper.findByIntentionThree(job_intention_three);
    }

    @Override
    public Candidate getByTime(String duty_time) {
        return employerPageMapper.findByTime(duty_time);
    }

    @Override
    public Candidate getByIndustryOne(String desire_industry_one) {
        return employerPageMapper.findByIndustryOne(desire_industry_one);
    }

    @Override
    public Candidate getByIndustryTwo(String desire_industry_two) {
        return employerPageMapper.findByIndustryTwo(desire_industry_two);
    }

    @Override
    public Candidate getByIndustryThree(String desire_industry_three) {
        return employerPageMapper.findByIndustryThree(desire_industry_three);
    }

    @Override
    public Candidate getByLocationOne(String desire_location_one) {
        return employerPageMapper.findByLocationOne(desire_location_one);
    }

    @Override
    public Candidate getByLocationTwo(String desire_location_two) {
        return employerPageMapper.findByLocationTwo(desire_location_two);
    }

    @Override
    public Candidate getByLocationThree(String desire_location_three) {
        return employerPageMapper.findByLocationThree(desire_location_three);
    }
}
