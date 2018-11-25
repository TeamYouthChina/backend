package com.youthchina.service.qingyang;

import com.youthchina.domain.qingyang.Job_qingyang;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.NotBelongException;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    public static Job_qingyang job;
    //给前端的默认值
    public static void initialization(){
        job.setJobId(1);
        job.setCompanyId(1);
        job.setJobName("前端");
        job.setCompanyName("YouthChina");
        job.setJobStartTime(new Date(20181212));
        job.setJobEndTime(new Date(20190101));
        job.setJobDescription("吃苦耐劳");
        job.setJobTime(1); // 全职1
        job.setJobLocation("北京");
        job.setCvReceiMail("test@test.test");
        job.setJobActive(1); // 1，2，3,4,5
    }
    @Override
    public void delete(User user, Integer jobId) throws NotFoundException {

    }

    @Override
    public Job_qingyang getByHr(User user, Integer jobId) throws NotBelongException {
        return null;
    }

    @Override
    public Job_qingyang get(String id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Job_qingyang> get(List<String> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(String id) throws NotFoundException {

    }

    @Override
    public Job_qingyang update(Job_qingyang job_qingyang) throws NotFoundException {
        return null;
    }

    @Override
    public Job_qingyang add(Job_qingyang entity) {
        return null;
    }
}



