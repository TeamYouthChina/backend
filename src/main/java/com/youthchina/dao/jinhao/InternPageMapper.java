package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * create by jinhaohu on 11/12/18
 */
@Mapper
public interface InternPageMapper {

    Job getJob(Integer job_id);

    HR getHR(Integer hr_id);

    Company getCompany(Integer company_id);

    JobCollect isJobCollected(@Param("user_id") Integer user_id, @Param("job_id") Integer job_id);

    CompanyCollect isCompanyCollected(@Param("user_id") Integer user_id, @Param("company_id") Integer company_id);

    Integer collectJob(JobCollect jobCollect);

    Integer createMapBetweenJobCollectAndUser(@Param("collect_id") Integer collect_id, @Param("user_id") Integer user_id);

    Integer collectCompany(CompanyCollect companyCollect);

    Integer createMapBetweenCompanyCollectAndUser(@Param("collect_id") Integer collect_id, @Param("user_id") Integer user_id);

    Integer cancelCollectJob(Integer collect_id);

    Integer deleteMapBetweenJobCollectAndUser(Integer collect_id);

    Integer cancelCollectCompany(Integer collect_id);

    Integer deleteMapBetweenCompanyCollectAndUser(Integer collect_id);
}
