package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.Company;
import com.youthchina.domain.jinhao.Job;
import com.youthchina.domain.jinhao.StuCollect;
import org.apache.ibatis.annotations.*;

/**
 * create by jinhaohu on 11/12/18
 */
@Mapper
public interface InternPageMapper {

    @Select("select * from JOB_INFO where job_id = #{job_id}")
    Job getJob(@Param("job_id") String job_id);

    @Select("select * from COMPANY_INFO where company_id = #{company_id}")
    Company getCompany(@Param("company_id") String company_id);

    @Select("select * from STU_COLLECT where stu_id = #{stuCollect.stu_id} and job_id = #{stuCollect.job_id}")
    StuCollect isJobCollected(@Param("stuCollect") StuCollect stuCollect);

    @Select("select * from STU_COLLECT where stu_id = #{stuCollect.stu_id} and company_id = #{stuCollect.company_id}")
    StuCollect isCompanyCollected(@Param("stuCollect") StuCollect stuCollect);

    @Insert("insert into STU_COLLECT(stu_id, job_id) values(#{stuCollect.stu_id}, #{stuCollect.job_id})")
    Integer collectJob(@Param("stuCollect") StuCollect stuCollect);

    @Insert("insert into STU_COLLECT(stu_id, company_id) values(#{stuCollect.stu_id}, #{stuCollect.company_id})")
    Integer collectCompany(@Param("stuCollect") StuCollect stuCollect);

    @Delete("delete from STU_COLLECT where stu_id = #{stuCollect.stu_id} and job_id = #{stuCollect.job_id}")
    Integer cancelCollectJob(@Param("stuCollect") StuCollect stuCollect);

    @Delete("delete from STU_COLLECT where stu_id = #{stuCollect.stu_id} and company_id = #{stuCollect.company_id}")
    Integer cancelCollectCompany(@Param("stuCollect") StuCollect stuCollect);
}
