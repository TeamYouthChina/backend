package com.youthchina.service.application;

import com.youthchina.dao.qingyang.JobMapper;
import com.youthchina.dao.qingyang.LocationMapper;
import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.*;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.exception.NotBelongException;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
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
     * 通过职位Id 获取职位详情
     *
     * @param id JobId
     * @return Job Detail
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Job get(Integer id) throws NotFoundException {
        Job job = jobMapper.selectJobByJobId(id);
        if(job == null) throw new NotFoundException(4040,404,"No such Job");
        //获取职位的时候返回所属公司信息
        job.setCompany(companyCURDServiceImpl.get(job.getCompany().getCompanyId()));
        //设置该职位Location
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

                Integer regionId = locationList.get(i).getRegionId();
                if(regionId < 100000){
                    regionId += 900000;
                }
                locationList.set(i, locationServiceImpl.getLocation(regionId));
            }
        }

        //Set Company Location
        Location comLocation = job.getCompany().getLocation();
        if (comLocation != null) {
            job.getCompany().setLocation(locationServiceImpl.getLocation(comLocation.getRegionId()));
        }
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
        jobMapper.deleteJobIndustry(job.getJobId());
        jobMapper.deleteJobDegree(job.getJobId());
        jobMapper.deleteJobLogo(job.getJobId());
        this.addRelatedInfo(job);
        return this.get(job.getJobId());
    }

    /**
     * 添加职位
     *
     * @param entity target
     * @return 返回添加后的职位详情
     */
    @Override
    @Transactional
    public Job add(Job entity) throws NotFoundException {
        Integer result = jobMapper.insertJob(entity);
        this.addRelatedInfo(entity);
        return this.get(entity.getJobId());
    }

    /**
     * 在添加&删除的时候, 添加关联表相关信息
     * @param job
     * @throws NotFoundException
     */
    private void addRelatedInfo(Job job) throws NotFoundException{
        List<Industry> industryList = job.getIndustries();
        if(industryList != null && industryList.size() > 0){
            jobMapper.insertJobIndustry(industryList);
        }
        List<Degree> degreeList = job.getJobReqList();
        if(degreeList != null && degreeList.size() > 0 ){
            jobMapper.insertJobDegree(job.getId(), degreeList);
        }
        List<Location> locationList = job.getJobLocationList();
        if(locationList != null && locationList.size() > 0){
            jobMapper.insertJobLocation(job.getId(), locationList);
        }
        List<Logo> logoList = job.getLogoList();
        if( logoList != null && logoList.size() > 0 ){
            jobMapper.insertJobLogo(job.getId(), logoList);
        }
    }

    /**暂时不用
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

    /**
     * 获取该User发布的职位
     * @param userId
     * @return
     * @throws NotFoundException
     */
    @Override
    public List<Job> getJobByUserId(Integer userId) throws NotFoundException {
        List<Job> jobList = jobMapper.getJobByUserId(userId);
        for(Job job : jobList){
            try {
                job.setCompany(companyCURDServiceImpl.get(job.getCompany().getCompanyId()));
            } catch (NotFoundException e){
                throw new NotFoundException(4040, 404, "Company of job : " + job.getJobName() + " not found");
            }
        }
        return jobList;
    }

    /**
     * 该stuId 是否收藏了这个职位
     * @param jobId
     * @param stuId
     * @return
     */
    public Boolean isCollected(Integer jobId, Integer stuId){
        Integer result = jobMapper.isCollect(jobId,stuId);
        return  result != null;
    }

    /**
     * 通过职位Id, 获取职位, 并判读是否被该User收藏
     * @param jobId
     * @param userId
     * @return
     * @throws NotFoundException
     */
    @Override
    public Job getJobWithCollected(Integer jobId, Integer userId) throws NotFoundException {
       Job job = this.get(jobId);
       job.setCollected(isCollected(jobId, userId));
        Company company = job.getCompany();
        company.setCollected(companyCURDServiceImpl.isCollected(company.getId(), userId));
        return job;
    }

    /**
     * 通过职位Id的List, 获取职位, 并判断是否被该User收藏
     * @param idList
     * @param userId
     * @return
     * @throws NotFoundException
     */
    public List<Job> getJobListWithCollected(List<Integer> idList, Integer userId) throws NotFoundException {
        List<Job> jobList = new ArrayList<Job>();
        for (Integer jobId: idList) {
            try {
                jobList.add(this.getJobWithCollected(jobId, userId));
            } catch (NotFoundException ignored) {

            }
        }
        return jobList;
    }

}



