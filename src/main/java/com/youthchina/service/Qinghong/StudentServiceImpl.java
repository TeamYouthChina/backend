package com.youthchina.service.Qinghong;

import com.youthchina.dao.Qinghong.ApplicantMapper;
import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.dao.qingyang.JobMapper;
import com.youthchina.dao.qingyang.ResumeJsonMapper;
import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Degree;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.dto.EducationDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.qingyang.JobService;
import com.youthchina.service.qingyang.JobServiceImpl;
import com.youthchina.service.qingyang.LocationService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @program: youthchina
 * @description: 学生实体Service层实现
 * @author: Qinghong Wang
 * @create: 2018-11-29 17:11
 **/
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private ApplicantMapper applicantMapper;

    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private LocationService locationService;

    @Autowired
    private JobServiceImpl jobService;

    @Autowired
    private ResumeJsonMapper resumeJsonMapper;


    /**
     * @Description: 通过user_id获得学生的所有信息，如何该id为空，则抛出异常
     * @Param: [id] use_id
     * @return: com.youthchina.domain.Qinghong.Student
     * @Author: Qinghong Wang
     * @Date: 2018/12/19
     */
    @Override
    public Student get(Integer id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(id);
        if (userInfo == null) {
            throw new NotFoundException(404, 404, "cannot find user with id "+id);//todo
        } else {
            Student student = applicantMapper.getStudentInfo(id);
            for(EducationInfo educationInfo:student.getEducationInfos()){
                Location location=locationService.getLocation(educationInfo.getLocation().getRegion_num());
                educationInfo.setLocation(location);
            }
            for (Work work:student.getWorks()){
                Location location=locationService.getLocation(work.getLocation().getRegion_num());
                work.setLocation(location);
            }
            return student;
        }
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
        if(applicantMapper.getStudentInfo(entity.getId())!=null){
            Student student=applicantMapper.getStudentInfo(entity.getId());
            for(EducationInfo educationInfo:student.getEducationInfos()){
                Location location=locationService.getLocation(educationInfo.getLocation().getRegion_num());
                educationInfo.setLocation(location);
            }
            for (Work work:student.getWorks()){
                Location location=locationService.getLocation(work.getLocation().getRegion_num());
                work.setLocation(location);
            }
            return student;
        }
        applicantMapper.updateUserInfo(entity);
        applicantMapper.insertStuInfo(entity);
        BaseInfo baseInfo=applicantMapper.getBaseInfo(entity.getId());
        System.out.print(baseInfo);
        Integer stu_id=baseInfo.getStu_id();
        for(LabelInfo labelInfo:entity.getLabelInfos()){
            AdvantageLabel label=new AdvantageLabel();
            label.setStu_id(stu_id);
            label.setLabel_code(labelInfo.getLabel_code());
            applicantMapper.insertStuLabel(label);
        }
        for(EducationInfo educationInfo:entity.getEducationInfos()){
            educationInfo.setStu_id(stu_id);
            Location location=locationService.getLocation(educationInfo.getLocation().getRegion_num());
            educationInfo.setLocation(location);
            Integer integer=applicantMapper.insertEduInfo(educationInfo);
        }
        for(Project project:entity.getProjects()){
            project.setStu_id(stu_id);
            Integer integer=applicantMapper.insertStuProject(project);
            System.out.print(integer);
        }
        for(Work work:entity.getWorks()){
            work.setStu_id(stu_id);
            Location location=locationService.getLocation(work.getLocation().getRegion_num());
            work.setLocation(location);
            applicantMapper.insertStuWork(work);
        }
        for (Activity activity:entity.getActivities()){
            activity.setStu_id(stu_id);
            applicantMapper.insertStuActivity(activity);
        }
        for(Certificate certificate:entity.getCertificates()){
            certificate.setStu_id(stu_id);
            applicantMapper.insertStuCertificate(certificate);
        }
        Student student=applicantMapper.getStudentInfo(entity.getId());
        //分离service并不能实现location
        for(EducationInfo educationInfo:student.getEducationInfos()){
            Location location=locationService.getLocation(educationInfo.getLocation().getRegion_num());
            educationInfo.setLocation(location);
        }
        for (Work work:student.getWorks()){
            Location location=locationService.getLocation(work.getLocation().getRegion_num());
            work.setLocation(location);
        }


        return student;
    }

    /**
     * @Description: 通过user_id找到该用户的所有基本注册信息
     * @Param: [id]
     * @return: com.youthchina.domain.Qinghong.UserInfo
     * @Author: Qinghong Wang
     * @Date: 2018/12/20
     */
    public UserInfo getContacts(Integer id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(id);
        if (userInfo == null) {
            throw new NotFoundException(404, 404, "cannot find user with id "+id);//todo
        } else return userInfo;
    }

    /**
     * @Description: 通过user_id找到所有该id下所有的教育信息
     * @Param: [id]
     * @return: java.util.List<com.youthchina.domain.Qinghong.EducationInfo>
     * @Author: Qinghong Wang
     * @Date: 2018/12/19
     */
    public List<EducationInfo> getEducations(Integer id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(id);
        if (userInfo == null) {
            throw new NotFoundException(404, 404, "cannot find user with id "+id);//todo
        } else {
            List<EducationInfo> educationInfos = applicantMapper.getEducations(id);
            return educationInfos;
        }
    }

    /**
     * @Description: 通过user_id找到该id下所有的工作经历
     * @Param: [id]
     * @return: java.util.List<com.youthchina.domain.Qinghong.Work>
     * @Author: Qinghong Wang
     * @Date: 2018/12/19
     */
    public List<Work> getWorks(Integer id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(id);
        if (userInfo == null) {
            throw new NotFoundException(404, 404, "cannot find user with id "+id);//todo
        } else {
            List<Work> works = applicantMapper.getWorks(id);
            return works;
        }

    }

    @Override
    public Student addStudent(Student student) {
        return null;
    }

    /**
     * @Description: 通过user_id找到该id下的所有课外活动经历
     * @Param: [id]
     * @return: java.util.List<com.youthchina.domain.Qinghong.Activity>
     * @Author: Qinghong Wang
     * @Date: 2018/12/19
     */
    public List<Activity> getActivities(Integer id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(id);
        if (userInfo == null) {
            throw new NotFoundException(404, 404, "cannot find user with id "+id);//todo
        } else {
            List<Activity> activities = applicantMapper.getActivities(id);
            return activities;
        }

    }

    /**
     * @Description: 通过user_id找到该id下所有的认证信息
     * @Param: [id]
     * @return: java.util.List<com.youthchina.domain.Qinghong.Certificate>
     * @Author: Qinghong Wang
     * @Date: 2018/12/22
     */
    public List<Certificate> getCertificates(Integer id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(id);
        if (userInfo == null) {
            throw new NotFoundException(404, 404, "cannot find user with id "+id);//todo
        } else {
            List<Certificate> certificates = applicantMapper.getCertificates(id);
            return certificates;
        }
    }

    /**
     * @Description: 通过user_id找到该id下所有的项目经历
     * @Param: [id]
     * @return: java.util.List<com.youthchina.domain.Qinghong.Project>
     * @Author: Qinghong Wang
     * @Date: 2018/12/19
     */
    public List<Project> getProjects(Integer id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(id);
        if (userInfo == null) {
            throw new NotFoundException(404, 404, "cannot find user with id "+id);//todo
        } else {
            List<Project> projects = applicantMapper.getProjects(id);
            return projects;
        }

    }


    /**
     * @Description: 通过job_id和user_id来将申请的职位信息加入申请表中
     * @Param: [job_id, user_id]
     * @return: com.youthchina.domain.Qinghong.JobApply
     * @Author: Qinghong Wang
     * @Date: 2018/12/19
     */
    public JobApply jobApply(Integer job_id,Integer user_id) throws NotFoundException {
        BaseInfo baseInfo=applicantMapper.getBaseInfo(user_id);
        Integer stu_id=baseInfo.getStu_id();
        Job job = jobMapper.selectJobByJobId(job_id);
        if (job == null) {
            throw new NotFoundException(4042, 404, "cannot find job with id "+job_id);
        } else {
            Date time = job.getJobEndTime();
            if (time.before(new Date())) {
                throw new NotFoundException(4032, 403, "cannot apply for job because it has passed deadline");
            } else {
                JobApply jobApply2=applicantMapper.getOneJobApply(job_id,stu_id);
                if(jobApply2!=null){
                    Job job1=jobApply2.getJob();
                    jobService.setJobLocation(job1);
                    return jobApply2;
                }else {
                    JobApply jobApply = new JobApply();
                    jobApply.setStu_id(applicantMapper.getBaseInfo(user_id).getStu_id());
                    jobApply.setJob_id(job_id);
                    //这里应该设计简历是否发送的判断
                    jobApply.setJob_cv_send(1);
                    jobApply.setJob_apply_status("已申请");
                    Integer integer = applicantMapper.addApply(jobApply);
                    JobApply jobApply1 = applicantMapper.getOneJobApply(job_id,stu_id);
                    Job job1=jobApply1.getJob();
                    jobService.setJobLocation(job1);
                    jobApply1.setJob(job1);
                    return jobApply1;
                }
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
    public List<JobApply> getJobApplies(Integer user_id) throws NotFoundException {
        UserInfo userInfo = applicantMapper.getUserInfo(user_id);
        if (userInfo == null) {
            throw new NotFoundException(4041, 404, "cannot find user with id " + user_id);
        } else {
            BaseInfo baseInfo = applicantMapper.getBaseInfo(user_id);
            List<JobApply> jobApplies = applicantMapper.getJobApplies(baseInfo.getStu_id());
            for (JobApply jobApply : jobApplies) {
                Job job=jobApply.getJob();
                jobService.setJobLocation(job);
                jobApply.setJob(job);


            }
                return jobApplies;
            }
        }


    /**
    * @Description: 通过user_id找到该id下所有的职位收藏信息
    * @Param: [user_id]
    * @return: java.util.List<com.youthchina.domain.Qinghong.JobCollect>
    * @Author: Qinghong Wang
    * @Date: 2019/1/9
    */
    public List<JobCollect> getJobCollect(Integer user_id) throws NotFoundException{
        UserInfo userInfo=applicantMapper.getUserInfo(user_id);
        if(userInfo==null){
            throw new NotFoundException(404,404,"cannot find user with id "+user_id);//todo
        }else {
            List<JobCollect> jobCollects=applicantMapper.getJobCollects(user_id);
            for(JobCollect jobCollect:jobCollects){
                Job job=jobCollect.getJob();
                jobService.setJobLocation(job);
                jobCollect.setJob(job);


            }
            return jobCollects;
        }
    }

    /**
    * @Description: 通过user_id找到该id下所有的公司收藏信息
    * @Param: [user_id]
    * @return: java.util.List<com.youthchina.domain.Qinghong.CompCollect>
    * @Author: Qinghong Wang
    * @Date: 2019/1/9
    */
    public List<CompCollect> getCompCollect(Integer user_id) throws NotFoundException{
        UserInfo userInfo=applicantMapper.getUserInfo(user_id);
        if(userInfo==null){
            throw new NotFoundException(404,404,"cannot find user with id "+user_id);//todo
        }else {
            List<CompCollect> compCollects=applicantMapper.getCompCollects(user_id);
            for(CompCollect compCollect:compCollects){
                Location location=locationService.getLocation(compCollect.getCompany().getLocation().getRegion_num());
                compCollect.getCompany().setLocation(location);
            }
            return compCollects;
        }
    }

    /**
    * @Description: 通过job_id user_id添加一个职位收藏
    * @Param: [job_id, user_id]
    * @return: java.lang.Integer
    * @Author: Qinghong Wang
    * @Date: 2019/1/9
    */
    public Integer addJobCollection(Integer job_id,Integer user_id) throws NotFoundException{
        UserInfo userInfo=applicantMapper.getUserInfo(user_id);
        if(userInfo==null){
            throw new  NotFoundException(404,404,"cannot find user with id "+user_id);//todo
        }else{
            JobCollect jobCollect=applicantMapper.getOneJobCollect(job_id);
            if(jobCollect!=null){
                throw new NotFoundException(404,404,"不能收藏该职位，因为已经收藏");//todo
            }else{
                Job job=jobMapper.selectJobByJobId(job_id);
                if(job==null){
                    throw new NotFoundException(400,404,"cannot collect this job,maybe the job has already delete");//todo
                }else {
                    JobCollect jobCollect1=new JobCollect();
                    jobCollect1.setStu_id(applicantMapper.getStudentInfo(user_id).getStu_id());
                    jobCollect1.setJob_id(job_id);
                    Integer integer=applicantMapper.addJobCollect(jobCollect1);
                    return integer;

                }
            }
        }

    }

    /**
     * @Description: 通过collect_id删除收藏的信息，通过假删除实现
     * @Param: [id]
     * @return: java.lang.Integer
     * @Author: Qinghong Wang
     * @Date: 2018/12/21
     */


    public Integer deleteCollect(Integer id) throws NotFoundException {
        Integer num1 = applicantMapper.deleteJobCollect(id);
        Integer num2 = applicantMapper.deleteCompCollect(id);
        if (num1 == 0 && num2 == 0) {
            throw new NotFoundException(404, 404, "没有删除任何一条收藏信息");//todo
        } else return num1 + num2;
    }
    /**
    * @Description: 通过collect_id删除职位收藏
    * @Param: [id]
    * @return: java.lang.Integer
    * @Author: Qinghong Wang
    * @Date: 2019/2/16
    */

    public Integer deleteJobCollect(Integer collect_id) throws NotFoundException{
        Integer num=applicantMapper.deleteJobCollect(collect_id);
        return num;
    }
    
    /**
    * @Description: 通过collect_id删除公司收藏
    * @Param: [id]
    * @return: java.lang.Integer
    * @Author: Qinghong Wang
    * @Date: 2019/2/16
    */

    public Integer deleteCompCollect(Integer collect_id) throws NotFoundException {
        Integer num=applicantMapper.deleteCompCollect(collect_id);
        return num;
    }

    /** 
    * @Description: 通过company_id和user_id添加公司收藏信息
    * @Param: [company_id, user_id] 
    * @return: java.lang.Integer 
    * @Author: Qinghong Wang 
    * @Date: 2019/2/16 
    */

    @Override
    public Integer addCompCollect(Integer company_id, Integer user_id) throws NotFoundException {
        UserInfo userInfo=applicantMapper.getUserInfo(user_id);
        if(userInfo==null){
            throw new  NotFoundException(404,404,"cannot find user with id "+user_id);//todo
        }else {
            Company company=companyMapper.selectCompany(company_id);
            if(company==null){
                throw new NotFoundException(400,400,"cannot collect this company,maybe the company has already deleted");//todo
            }else{
                CompCollect compCollect=new CompCollect();
                compCollect.setCompany_id(company_id);
                compCollect.setStu_id(applicantMapper.getStudentInfo(user_id).getStu_id());
                Integer integer=applicantMapper.addCompCollect(compCollect);
                return integer;
            }
        }

    }

    @Override
    public List<EducationInfo> insertEducation(EducationInfo educationInfo, Integer user_id) throws NotFoundException {
        BaseInfo baseInfo=applicantMapper.getBaseInfo(user_id);
        if(baseInfo==null){
            throw new NotFoundException(404,404,"cannot find user with id "+user_id);
        }else{
            Integer stu_id=baseInfo.getStu_id();
            educationInfo.setStu_id(stu_id);
            Integer integer=applicantMapper.insertEduInfo(educationInfo);
            List<EducationInfo> educationInfos=applicantMapper.getStudentInfo(user_id).getEducationInfos();
            for(EducationInfo educationInfo1:educationInfos){
                Location location=locationService.getLocation(educationInfo1.getLocation().getRegion_num());
                educationInfo1.setLocation(location);
            }
            return educationInfos;

        }
    }

    @Override
    public List<Work> insertWork(Work work, Integer user_id) throws NotFoundException {
        BaseInfo baseInfo=applicantMapper.getBaseInfo(user_id);
        if(baseInfo==null){
            throw new NotFoundException(404,404,"cannot find user with id "+user_id);
        }else{
            Integer stu_id=baseInfo.getStu_id();
            work.setStu_id(stu_id);
            Integer integer=applicantMapper.insertStuWork(work);
            List<Work> works=applicantMapper.getStudentInfo(user_id).getWorks();
            for(Work work1:works){
                Location location=locationService.getLocation(work1.getLocation().getRegion_num());
                work1.setLocation(location);
            }

            return works;

        }

    }

    @Override
    public List<Project> insertProject(Project project, Integer user_id) throws NotFoundException {
        BaseInfo baseInfo=applicantMapper.getBaseInfo(user_id);
        if(baseInfo==null){
            throw new NotFoundException(404,404,"cannot find user with id "+user_id);
        }else{
            Integer stu_id=baseInfo.getStu_id();
            project.setStu_id(stu_id);
            Integer integer=applicantMapper.insertStuProject(project);
            List<Project> projects=applicantMapper.getProjects(user_id);
            return projects;

        }
    }

    @Override
    public List<Activity> insertActivity(Activity activity, Integer user_id) throws NotFoundException {
        BaseInfo baseInfo=applicantMapper.getBaseInfo(user_id);
        if(baseInfo==null){
            throw new NotFoundException(404,404,"cannot find user with id "+user_id);
        }else{
            Integer stu_id=baseInfo.getStu_id();
            activity.setStu_id(stu_id);
            Integer integer=applicantMapper.insertStuActivity(activity);
            List<Activity> activities=applicantMapper.getActivities(user_id);
            return activities;

        }

    }

    @Override
    public List<Certificate> insertCertificate(Certificate certificate, Integer user_id) throws NotFoundException {
        BaseInfo baseInfo=applicantMapper.getBaseInfo(user_id);
        if(baseInfo==null){
            throw new NotFoundException(404,404,"cannot find user with id "+user_id);
        }else{
            Integer stu_id=baseInfo.getStu_id();
            certificate.setStu_id(stu_id);
            Integer integer=applicantMapper.insertStuCertificate(certificate);
            List<Certificate> certificates=applicantMapper.getCertificates(user_id);
            return certificates;

        }

    }


    @Override
    public Integer deleteEducation(Integer id) throws NotFoundException {
        Integer integer=applicantMapper.deleteEduInfo(id);
        return integer;
    }

    @Override
    public Integer deleteWork(Integer id) throws NotFoundException {
        Integer integer=applicantMapper.deleteWork(id);
        return integer;
    }

    @Override
    public Integer deleteProject(Integer id) throws NotFoundException {
        Integer integer=applicantMapper.deleteProject(id);
        return integer;
    }

    @Override
    public Integer deleteActivity(Integer id) throws NotFoundException {
        Integer integer=applicantMapper.deleteActivity(id);
        return integer;
    }

    @Override
    public Integer deleteCertificate(Integer id) throws NotFoundException {
        Integer integer=applicantMapper.deleteCertificate(id);
        return integer;
    }

    @Override
    public List<EducationInfo> insertEducations(List<EducationInfo> educationInfos, Integer user_id) throws NotFoundException {
        BaseInfo baseInfo=applicantMapper.getBaseInfo(user_id);
        if(baseInfo==null){
            throw new NotFoundException(404,404,"cannot find user with id "+user_id);
        }else{
            Integer EduNum=applicantMapper.deleteAllEduInfo(baseInfo.getStu_id());
            System.out.print(EduNum);
            for(EducationInfo educationInfo:educationInfos){
                educationInfo.setStu_id(baseInfo.getStu_id());
                applicantMapper.insertEduInfo(educationInfo);
            }
            List<EducationInfo> educationInfoList=applicantMapper.getStudentInfo(user_id).getEducationInfos();
            for(EducationInfo educationInfo:educationInfoList){
                Location location=locationService.getLocation(educationInfo.getLocation().getRegion_num());
                educationInfo.setLocation(location);

            }
            return educationInfoList;
        }

    }

    @Override
    public List<Work> insertWorks(List<Work> works, Integer user_id) throws NotFoundException {
        BaseInfo baseInfo=applicantMapper.getBaseInfo(user_id);
        if(baseInfo==null){
            throw new NotFoundException(404,404,"cannot find user with id "+user_id);
        }else{
            Integer num=applicantMapper.deleteAllWork(baseInfo.getStu_id());
            for(Work work:works){
                work.setStu_id(baseInfo.getStu_id());
                applicantMapper.insertStuWork(work);
            }
            List<Work> works1=applicantMapper.getStudentInfo(user_id).getWorks();
            for(Work work:works1){
                Location location=locationService.getLocation(work.getLocation().getRegion_num());
                work.setLocation(location);

            }
            return works1;

        }


    }

    @Override
    public List<Project> insertProjects(List<Project> projects, Integer user_id) throws NotFoundException {
        BaseInfo baseInfo=applicantMapper.getBaseInfo(user_id);
        if(baseInfo==null){
            throw new NotFoundException(404,404,"cannot find user with id "+user_id);
        }else{
            Integer num=applicantMapper.deleteAllProject(baseInfo.getStu_id());
            for(Project project:projects){
                project.setStu_id(baseInfo.getStu_id());
                applicantMapper.insertStuProject(project);
            }
            List<Project> projects1=applicantMapper.getStudentInfo(user_id).getProjects();
            return projects1;
        }
    }

    @Override
    public List<Activity> insertActivities(List<Activity> activities, Integer user_id) throws NotFoundException {
        BaseInfo baseInfo=applicantMapper.getBaseInfo(user_id);
        if(baseInfo==null){
            throw new NotFoundException(404,404,"cannot find user with id "+user_id);
        }else{
            Integer num=applicantMapper.deleteAllActivity(baseInfo.getStu_id());
            for(Activity activity:activities){
                activity.setStu_id(baseInfo.getStu_id());
                applicantMapper.insertStuActivity(activity);
            }
            List<Activity> activities1=applicantMapper.getStudentInfo(user_id).getActivities();
            return activities1;
        }
    }

    @Override
    public List<Certificate> insertCertificates(List<Certificate> certificates, Integer user_id) throws NotFoundException {
        BaseInfo baseInfo=applicantMapper.getBaseInfo(user_id);
        if(baseInfo==null){
            throw new NotFoundException(404,404,"cannot find user with id "+user_id);
        }else{
            Integer num=applicantMapper.deleteAllCertificate(baseInfo.getStu_id());
            for (Certificate certificate:certificates){
                certificate.setStu_id(baseInfo.getStu_id());
                applicantMapper.insertStuCertificate(certificate);

            }
            List<Certificate> certificates1=applicantMapper.getStudentInfo(user_id).getCertificates();
            return certificates1;
        }
    }

    @Override
    public List<LabelInfo> insertLabels(List<String> label_codes, Integer user_id) throws NotFoundException {
        BaseInfo baseInfo=applicantMapper.getBaseInfo(user_id);
        if(baseInfo==null){
            throw new NotFoundException(404,404,"cannot find user with id "+user_id);
        }else{
            Integer num=applicantMapper.deleteAllSkills(baseInfo.getStu_id());
            for(String label_code:label_codes){
                AdvantageLabel advantageLabel=new AdvantageLabel();
                advantageLabel.setLabel_code(label_code);
                advantageLabel.setStu_id(baseInfo.getStu_id());
                applicantMapper.insertAdvantageSkills(advantageLabel);
            }
            List<LabelInfo> labelInfos=applicantMapper.getStudentInfo(user_id).getLabelInfos();
            return labelInfos;
        }
    }

    @Override
    public List<LabelInfo> getAllSkills() throws NotFoundException {
        List<LabelInfo> labelInfos=applicantMapper.getAllSkills();
        return labelInfos;
    }

    @Override
    public ResumeJson getResumeJson(Integer resume_id) throws NotFoundException {
        return resumeJsonMapper.selectResumeJson(resume_id);
    }

    @Override
    public ResumeJson insertResumeJson(ResumeJson resumeJson) throws NotFoundException {
        Integer id = resumeJsonMapper.insertResumeJson(resumeJson);
        return resumeJsonMapper.selectResumeJson(resumeJson.getResume_id());
    }
}
