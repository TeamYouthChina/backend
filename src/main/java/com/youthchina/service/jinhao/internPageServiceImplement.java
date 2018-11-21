package com.youthchina.service.jinhao;

import com.youthchina.dao.jinhao.InternPageMapper;
import com.youthchina.domain.jinhao.*;
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
     * 把需要的信息整合起来返回给前端
     * @param company_id 企业id
     * @param job_id 职位id
     * @return 返回所需信息组合成的一个对象
     */
    @Override
    @Transactional(rollbackFor = SQLException.class)
    public InternPageInfo getCompanyAndJob(Integer company_id, Integer job_id, Integer user_id) {
        Company company = getCompany(company_id);
        Job job = getJob(job_id);
        Integer hr_id = job.getJob_pub_hr();
        HR hr = getHR(hr_id);
        boolean ifJobCollected = isJobCollected(user_id, job_id);
        boolean ifCompanyCollected = isCompanyCollected(user_id, company_id);
        return new InternPageInfo(company, job, hr, ifJobCollected, ifCompanyCollected);
    }

    /**
     * 拿到职位信息
     * @param job_id 职位id
     * @return 返回一个job对象
     */
    @Override
    public Job getJob(Integer job_id) {
        return internPageMapper.getJob(job_id);
    }

    /**
     * 拿到公司信息
     * @param company_id 公司id
     * @return 返回一个company对象
     */
    @Override
    public Company getCompany(Integer company_id) {
        return internPageMapper.getCompany(company_id);
    }

    /**
     * 拿到HR信息
     * @param hr_id HR id
     * @return 返回一个HR对象
     */
    @Override
    public HR getHR(Integer hr_id) {
        return internPageMapper.getHR(hr_id);
    }

    /**
     * 检查工作是否被收藏
     * @param user_id 用户id
     * @param job_id 工作id
     * @return 返回一个布尔值
     */
    @Override
    public boolean isJobCollected(Integer user_id, Integer job_id) {
        return internPageMapper.isJobCollected(user_id, job_id) == null;
    }

    /**
     * 检查公司是否被收藏
     * @param user_id 用户id
     * @param company_id 公司id
     * @return 返回一个布尔值
     */
    @Override
    public boolean isCompanyCollected(Integer user_id, Integer company_id) {
        return internPageMapper.isCompanyCollected(user_id, company_id) == null;
    }


    /**
     * 收藏工作
     * @param jobCollect 工作收藏的对象，包含工作id和收藏时间
     * @param user_id 用户id
     * @return 如果收藏成功返回1
     */
    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Integer collectJob(JobCollect jobCollect, Integer user_id){
        if(getJob(jobCollect.getJob_id()) == null){
            return 0;
        }else{
            internPageMapper.collectJob(jobCollect);
            return internPageMapper.createMapBetweenJobCollectAndUser(jobCollect.getCollect_id(), user_id);
        }
    }

    /**
     * 收藏公司
     * @param companyCollect 公司收藏的对象,包含公司id和收藏时间
     * @param user_id 用户id
     * @return 如果收藏成功返回1
     */
    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Integer collectCompany(CompanyCollect companyCollect, Integer user_id) {
        if(getCompany(companyCollect.getCompany_id()) == null){
            return 0;
        }else {
            internPageMapper.collectCompany(companyCollect);
            return internPageMapper.createMapBetweenCompanyCollectAndUser(companyCollect.getCollect_id(), user_id);
        }
    }

    /**
     * 取消收藏工作
     * @param collect_id 被取消的收藏的id
     * @return 取消成功返回1
     */
    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Integer cancelCollectJob(Integer collect_id) {
        internPageMapper.cancelCollectJob(collect_id);
        return internPageMapper.deleteMapBetweenJobCollectAndUser(collect_id);
    }

    /**
     * 取消收藏公司
     * @param collect_id 被取消的收藏的id
     * @return 取消成功返回1
     */
    @Override
    @Transactional(rollbackFor = SQLException.class)
    public Integer cancelCollectCompany(Integer collect_id) {
        internPageMapper.cancelCollectCompany(collect_id);
        return internPageMapper.deleteMapBetweenCompanyCollectAndUser(collect_id);
    }
}
