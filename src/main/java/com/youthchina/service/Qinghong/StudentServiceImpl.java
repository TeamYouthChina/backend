package com.youthchina.service.Qinghong;

import com.youthchina.dao.Qinghong.ApplicantMapper;
import com.youthchina.dao.Qinghong.StudentMapper;
import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.qingyang.Job_qingyang;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @program: youthchina
 * @description: 学生实体Service层实现
 * @author: Qinghong Wang
 * @create: 2018-11-29 17:11
 **/
public class StudentServiceImpl implements StudentService{
    @Autowired
    ApplicantMapper applicantMapper;
    /**
    * @Description: 通过user_id获得学生的所有信息，如何该id为空，则抛出异常
    * @Param: [id] use_id
    * @return: com.youthchina.domain.Qinghong.Student
    * @Author: Qinghong Wang
    * @Date: 2018/12/19
    */
    @Override
    public Student get(Integer id) throws NotFoundException {
        Student student=applicantMapper.getStudentInfo(id);
        if (student==null){
            throw new NotFoundException(404,404,"不能找到该user_id下的学生信息");
        }
        else return student;

    }

    @Override
    public List<Student> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Student update(Student student) throws NotFoundException {
        return null;
    }

    @Override
    public Student add(Student entity) {
        return null;
    }
    /**
    * @Description: 通过user_id找到所有该id下所有的教育信息
    * @Param: [id]
    * @return: java.util.List<com.youthchina.domain.Qinghong.EducationInfo>
    * @Author: Qinghong Wang
    * @Date: 2018/12/19
    */
    public List<EducationInfo> getEducations(Integer id) throws NotFoundException{
        List<EducationInfo> educationInfos=applicantMapper.getEducations(id);
        if (educationInfos==null){
            throw new NotFoundException(404,404,"不能找到该user_id下的教育信息");
        }
        else return educationInfos;
    }
    /**
    * @Description: 通过user_id找到该id下所有的工作经历
    * @Param: [id]
    * @return: java.util.List<com.youthchina.domain.Qinghong.Work>
    * @Author: Qinghong Wang
    * @Date: 2018/12/19
    */
    public List<Work> getWorks(Integer id) throws NotFoundException{
        List<Work> works=applicantMapper.getWorks(id);
        if (works==null){
            throw new NotFoundException(404,404,"不能找到该user_id下的工作信息");
        }
        else return works;

    }
    /**
    * @Description: 通过user_id找到该id下的所有课外活动经历
    * @Param: [id]
    * @return: java.util.List<com.youthchina.domain.Qinghong.Activity>
    * @Author: Qinghong Wang
    * @Date: 2018/12/19
    */
    public List<Activity> getActivities(Integer id) throws NotFoundException{
        List<Activity> activities=applicantMapper.getActivities(id);
        if (activities==null){
            throw new NotFoundException(404,404,"不能找到该user_id下的课外信息");
        }
        else return activities;

    }

    /**
    * @Description: 通过user_id找到该id下所有的项目经历
    * @Param: [id]
    * @return: java.util.List<com.youthchina.domain.Qinghong.Project>
    * @Author: Qinghong Wang
    * @Date: 2018/12/19
    */
    public List<Project> getProjects(Integer id) throws NotFoundException{
        List<Project> projects=applicantMapper.getProjects(id);
        if (projects==null){
            throw new NotFoundException(404,404,"不能找到该user_id下的课外信息");
        }
        else return projects;
    }
    /**
    * @Description: 通过job_id和stu_id来将申请的职位信息加入申请表中
    * @Param: [job_id, stu_id]
    * @return: com.youthchina.domain.Qinghong.JobApply
    * @Author: Qinghong Wang
    * @Date: 2018/12/19
    */
    public JobApply jobApply(Integer job_id,Integer stu_id) throws NotFoundException{
        Job_qingyang job=applicantMapper.getJob(job_id);
        if(job==null){
            throw new NotFoundException(404,404,"不能找到该job_id下的职位信息");
        }
        else {
            Date time=job.getJobEndTime();
            if(time.before(new Date())){
                throw new NotFoundException(404,404,"不能申请该职位因为申请时间已过");
            }
            else{
                JobApply jobApply=new JobApply();
                jobApply.setJob_id(job.getJobId());
                jobApply.setStu_id(stu_id);
                jobApply.setJob_cv_send(1);
                applicantMapper.addApply(jobApply);
                return jobApply;
            }
            }
        }
    /** 
    * @Description: 通过user_id找到该id下所有申请职位的信息
    * @Param: [user_id] 
    * @return: java.util.List<com.youthchina.domain.Qinghong.JobApply> 
    * @Author: Qinghong Wang 
    * @Date: 2018/12/19 
    */
    public List<JobApply> getJobApplies(Integer user_id) throws NotFoundException{
        List<JobApply> jobApplies=applicantMapper.getJobApplies(user_id);
        if(jobApplies==null){
            throw new NotFoundException(404,404,"该user_id下没有职位申请记录");
        }
        else return jobApplies;
    }

}
