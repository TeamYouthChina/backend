package com.youthchina.service.jinhao;

import com.youthchina.domain.jinhao.*;

/**
 * create by jinhaohu on 11/12/18
 */
public interface InternPageService {

    InternPageInfo getCompanyAndJob(Integer company_id, Integer job_id, Integer user_id);

    Job getJob(Integer job_id);

    Company getCompany(Integer company_id);

    HR getHR(Integer hr_id);

    boolean isJobCollected(Integer user_id, Integer job_id);

    boolean isCompanyCollected(Integer user_id, Integer company_id);

    Integer collectCompany(CompanyCollect companyCollect, Integer user_id);

    Integer collectJob(JobCollect jobCollect, Integer user_id);

    Integer cancelCollectCompany(Integer collect_id);

    Integer cancelCollectJob(Integer collect_id);
}
