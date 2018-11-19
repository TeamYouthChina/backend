package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.JobHrMapper;
import com.youthchina.domain.qingyang.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class JobHrServiceImpl implements JobHrService {

    @Autowired
    JobHrMapper jobHrMapper;
    /**添加Job*/
    @Override
    public Map<String,Object> insertJob(Job job) {
        //定义消息返回值
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);
        Integer result = jobHrMapper.insertJob(job);
        if (1!=result){
            resultMap.put("result", false);
        }
        return resultMap;
    }

    /**更新Job信息*/
    @Override
    public Map<String, Object> updateJob(Job job) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);
        Integer result = jobHrMapper.updateJob(job);
        if (1!=result){
            resultMap.put("result", false);
        }
        return resultMap;
    }

    /**删除Job信息*/
    @Override
    public Map<String, Object> deleteJob(String job_id) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);
        Integer result = jobHrMapper.deleteJob(job_id);
        if (1!=result){
            resultMap.put("result", false);
        }
        return resultMap;
    }

    /** 通过JobId查询Job信息*/
    @Override
    public Job getJobByJobId(String job_id) {
        return jobHrMapper.selectJobByJobId(job_id);
    }


    /** 通过CompanyId查询Job信息*/
    @Override
    public List<Job> getJobByComId(String company_id){
        return jobHrMapper.selectJobByComId(company_id);
    }
}

