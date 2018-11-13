package com.youthchina.service.jinhao;

import com.youthchina.domain.jinhao.Company;
import com.youthchina.domain.jinhao.CompanyAndJob;
import com.youthchina.domain.jinhao.Job;
import com.youthchina.domain.jinhao.StuCollect;

/**
 * create by jinhaohu on 11/12/18
 */
public interface InternPageService {

    CompanyAndJob getCompanyAndJob(String company_id, String job_id);

    Job getJob(String job_id);

    Company getCompany(String company_id);

    boolean isJobCollected(StuCollect job);

    boolean isCompanyCollected(StuCollect company);

    Integer collectCompany(StuCollect job);

    Integer collectJob(StuCollect company);

    Integer cancelCollectCompany(StuCollect company);

    Integer cancelCollectJob(StuCollect job);
}
