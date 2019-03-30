package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.JobMapper;
import com.youthchina.dao.qingyang.LocationMapper;
import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.Degree;
import com.youthchina.domain.qingyang.Industry;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.NotBelongException;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobMapper jobMapper;

    @Autowired
    LocationMapper locationMapper;

    final
    LocationServiceImpl locationServiceImpl;

    final
    CompanyCURDService companyCURDServiceImpl;

    @Autowired
    public JobServiceImpl(LocationServiceImpl locationServiceImpl, CompanyCURDServiceImpl companyCURDServiceImpl) {
        this.locationServiceImpl = locationServiceImpl;
        this.companyCURDServiceImpl = companyCURDServiceImpl;
    }

    /**
     * 删除职位 TODO: 通过HrId 确认其有删除权限
     *
     * @param user  Hr
     * @param jobId 职位Id
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public void delete(User user, Integer jobId) throws NotFoundException {
        jobMapper.deleteJob(jobId);
    }

    /**
     * 职位详情 TODO: 通过HrId 确认其有权限
     *
     * @param user  Hr
     * @param jobId 职位Id
     * @return
     * @throws NotBelongException
     */
    @Override
    @Transactional
    public Job getByHr(User user, Integer jobId) throws NotBelongException {
        Job result = jobMapper.selectJobByJobId(jobId);
        setJobLocation(result);
        return result;
    }

    /**
     * 职位详情
     *
     * @param id JobId
     * @return Job Detail
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Job get(Integer id) throws NotFoundException {
        Job job = jobMapper.selectJobByJobId(id);
        job.setCompany(companyCURDServiceImpl.get(job.getCompany().getCompanyId()));
        setJobLocation(job);
        return job;
    }

    /**
     * 查询Job的Location
     *
     * @param job
     */
    public void setJobLocation(Job job) {
        List<Location> locationList = job.getJobLocationList();
        if (locationList != null) {
            for (int i = 0; i < locationList.size(); i++) {

                Integer regionNum = locationList.get(i).getRegionNum();
                locationList.set(i, locationServiceImpl.getLocation(regionNum));
            }
        }

        //Set Company Location
        Location comLocation = job.getCompany().getLocation();
        if (comLocation != null) {
            job.getCompany().setLocation(locationServiceImpl.getLocation(comLocation.getRegionId()));
        }
    }

    /**
     * 多职位详情
     *
     * @param id list of id
     * @return
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public List<Job> get(List<Integer> id) throws NotFoundException {
        List<Job> jobList = jobMapper.selectJobByJobIdList(id);
        for (Job job : jobList) {
            setJobLocation(job);
            job.setCompany(companyCURDServiceImpl.get(job.getCompany().getCompanyId()));
        }
        return jobList;
    }

    /**
     * 按 JobId 删除职位
     *
     * @param id id
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        jobMapper.deleteJob(id);
    }

    /**
     * 更新职位详情
     *
     * @param job
     * @return 返回更新后的职位详情
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Job update(Job job) throws NotFoundException {
        jobMapper.updateJob(job);
        jobMapper.deleteJobLocation(job.getJobId());
        jobMapper.insertJobLocation(job.getId(), job.getJobLocationList());
        jobMapper.deleteJobIndustry(job.getJobId());
        jobMapper.insertJobIndustry(job.getIndustries());
        jobMapper.deleteJobDegree(job.getJobId());
        if(job.getJobReqList() != null && job.getJobReqList().size() > 0 ){
            jobMapper.insertJobDegree(job.getId(), job.getJobReqList());
        }
        jobMapper.deleteJobLogo(job.getJobId());
        if(job.getLogoList() != null && job.getLogoList().size() > 0 ){
            jobMapper.insertJobLogo(job.getId(), job.getLogoList());
        }
        Job result = this.get(job.getId());
        return result;
    }

    /**
     * 添加职位
     *
     * @param entity target
     * @return 返回添加后的职位详情
     */
    @Override
    @Transactional
    public Job add(Job entity) {
        Integer result = jobMapper.insertJob(entity);
        jobMapper.insertJobIndustry(entity.getIndustries());
        if(entity.getJobReqList() != null && entity.getJobReqList().size() > 0 ){
            jobMapper.insertJobDegree(entity.getId(), entity.getJobReqList());
        }
        jobMapper.insertJobLocation(entity.getId(), entity.getJobLocationList());
        if(entity.getLogoList() != null && entity.getLogoList().size() > 0 ){
            jobMapper.insertJobLogo(entity.getId(), entity.getLogoList());
        }
        Job jobResult = jobMapper.selectJobByJobId(entity.getJobId());
        setJobLocation(jobResult);
        return jobResult;
    }

    /**
     * Job Advanced Search 高级搜索, 返回职位详情List
     *
     * @param jobId        职位ID
     * @param jobName      职位名称, 模糊搜索
     * @param comId        公司ID
     * @param comName      公司名称
     * @param startTime    职位起始时间
     * @param endTime      职位截止时间
     * @param type         职位性质 1-实习，2-兼职，3-全职
     * @param salaryFloor  薪资下限(包含)
     * @param salaryCap    薪资上限(包含)
     * @param active       职位状态 1，2，3,4,5
     * @param location     职位地点List
     * @param jobReqList   职位学位要求List
     * @param industryList 职位行业List
     * @return
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public List<Job> getJobByMore(Integer jobId, String jobName, Integer comId, String comName,
                                  Date startTime, Date endTime, Integer type, Integer salaryFloor, Integer salaryCap,
                                  Integer active, String location, List<Degree> jobReqList,
                                  List<Industry> industryList) throws NotFoundException {
        //TODO: Location Param
        List<Job> results = jobMapper.getJobByMore(jobId, jobName, comId, comName, startTime, endTime, type,
                salaryFloor, salaryCap, active, null, jobReqList, industryList);
        for (Job job : results) {
            setJobLocation(job);
            job.setCompany(companyCURDServiceImpl.get(job.getCompany().getCompanyId()));
        }
        return results;
    }

}



