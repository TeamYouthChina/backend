package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.InternPageMapper;
import com.youthchina.domain.jinhao.Company;
import com.youthchina.domain.jinhao.CompanyAndJob;
import com.youthchina.domain.jinhao.Job;
import com.youthchina.domain.jinhao.StuCollect;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;

/**
 * create by jinhaohu on 11/12/18
 */
@Service
public class internPageServiceImplement implements InternPageService {
    @Resource
    InternPageMapper internPageMapper;

    /**
     * combine obtained Job object and Company object into a CompanyAndJob object, so that we can see the two select
     * method as a transaction
     * @param company_id id of company
     * @param job_id id of job
     * @return an object of CompanyAndJob, and if any one of the select methods fails, it returns null
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = SQLException.class)
    public CompanyAndJob getCompanyAndJob(String company_id, String job_id) {
        return new CompanyAndJob(getCompany(company_id), getJob(job_id));
    }

    /**
     * get detailed information of the specified job
     * @param job_id id of job
     * @return a Job object
     */
    @Override
    public Job getJob(String job_id) {
        return internPageMapper.getJob(job_id);
    }

    /**
     * get detailed information of the specified company
     * @param company_id id of company
     * @return a Company object
     */
    @Override
    public Company getCompany(String company_id) {
        return internPageMapper.getCompany(company_id);
    }

    /**
     * check if the job is collected
     * @param job a stuCollect object whose stu_id and job_id are specified
     * @return a stuCollect object, and if the job has not been collected, the object equals to null
     */
    @Override
    public boolean isJobCollected(StuCollect job) {
        return internPageMapper.isJobCollected(job) == null;
    }

    /**
     * check if the company is collected
     * @param company a stuCollect object whose stu_id and company_id are specified
     * @return a stuCollect object, and if the job has not been collected, the object equals to null
     */
    @Override
    public boolean isCompanyCollected(StuCollect company) {
        return internPageMapper.isCompanyCollected(company) == null;
    }

    /**
     * collect the job
     * @param job a stuCollect object whose stu_id and job_id are specified
     * @return return 1 if success, or if the jod does not exist or the jod cannot be insert successfully, return 0
     */
    @Override
    public Integer collectJob(StuCollect job){
        if(getJob(job.getJob_id()) == null){
            return 0;
        }else{
            return internPageMapper.collectJob(job);
        }
    }

    /**
     * collect the company
     * @param company a stuCollect object whose stu_id and company_id are specified
     * @return return 1 if success, or 0
     */
    @Override
    public Integer collectCompany(StuCollect company) {
        if(getCompany(company.getCompany_id()) == null){
            return 0;
        }else{
            return internPageMapper.collectCompany(company);
        }
    }

    /**
     * cancel collecting the job
     * @param job a stuCollect object whose stu_id and job_id are specified
     * @return return 1 if success, or 0
     */
    @Override
    public Integer cancelCollectJob(StuCollect job) {
        return internPageMapper.cancelCollectJob(job);
    }

    /**
     * cancel collecting the company
     * @param company stuCollect a stuCollect object whose stu_id and company_id are specified
     * @return return 1 if success, or 0
     */
    @Override
    public Integer cancelCollectCompany(StuCollect company) {
        return internPageMapper.cancelCollectCompany(company);
    }
}
