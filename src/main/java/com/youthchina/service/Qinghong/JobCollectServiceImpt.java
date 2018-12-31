package com.youthchina.service.Qinghong;

import com.youthchina.dao.Qinghong.StudentMapper;
import com.youthchina.dao.qingyang.JobHrMapper;
import com.youthchina.domain.Qinghong.JobCollect;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: youthchina
 * @description: 职位收藏的Service层具体实现
 * @author: Qinghong Wang
 * @create: 2018-11-29 17:29
 **/
@Service
public class JobCollectServiceImpt implements JobCollectService {
    private StudentMapper studentMapper;
    private JobHrMapper jobHrMapper;

    @Autowired
    public JobCollectServiceImpt(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @Override
    public Job get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Job> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Job update(Job Job) throws NotFoundException {
        return null;
    }

    @Override
    public Job add(Job entity) {
        return null;
    }

    /**
     * @Description: 通过学生id获得该id下所有收藏的信息
     * @Param: [stu_id]
     * @return: java.util.List<com.youthchina.domain.qingyang.Job>
     * @Author: Qinghong Wang
     * @Date: 2018/11/29
     */
    public List<Job> getAllJobCollect(Integer stu_id) {
        List<JobCollect> jobCollects = studentMapper.getAllJobCollect(stu_id);
        List<Integer> key = new ArrayList<>();
        for (JobCollect item : jobCollects) {
            key.add(item.getJob_id());
        }
        List<Job> result = jobHrMapper.selectJobByJobIdList(key);
        return result;
    }

    /**
     * @Description: 通过job_id获得该职业的所有信息，包括职业信息，职业对应的公司信息，发布职位的HR信息
     * @Param: [job_id]
     * @return: com.youthchina.domain.qingyang.Job
     * @Author: Qinghong Wang
     * @Date: 2018/11/29
     */
    public Job getByJobId(Integer job_id) {
        return studentMapper.getByJobId(job_id);
    }

    public JobCollect addOneJobCollect(Integer stu_id, Integer job_id) {
        JobCollect jobCollect = new JobCollect();
        if (studentMapper.getOneJobCollect(stu_id, job_id) == null) {

            jobCollect.setJob_id(job_id);
            jobCollect.setStu_id(stu_id);
            jobCollect.setJob_coll_time(new Date());
            studentMapper.addOneJobCollect(jobCollect);
        }
        return jobCollect;
    }

}
