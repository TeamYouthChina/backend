package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.JobHrMapper;
import com.youthchina.domain.qingyang.JobLocation;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class JobLocationCURDServiceImpl implements JobLocationCURDService{
    @Resource
    JobHrMapper jobHrMapper;

    @Override
    public JobLocation get(Integer id) throws NotFoundException {
        return jobHrMapper.getJobLocation(id);
    }

    @Override
    public List<JobLocation> get(List<Integer> id) throws NotFoundException {
        return jobHrMapper.getJobLocationList(id);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        jobHrMapper.deleteJobLocation(id);
    }

    @Override
    public JobLocation update(JobLocation jobLocation) throws NotFoundException {
        Integer result = jobHrMapper.updateJobLocation(jobLocation);
        return jobHrMapper.getJobLocation(result);
    }

    @Override
    public JobLocation add(JobLocation entity) {
        Integer result = jobHrMapper.insertJobLocation(entity);
        return jobHrMapper.getJobLocation(result);
    }
}
