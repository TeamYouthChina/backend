package com.youthchina.dao.qingyang;

import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.Degree;
import com.youthchina.domain.qingyang.Industry;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.domain.qingyang.Logo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;


@Mapper
@Component
public interface JobMapper {
    /**
     * insert Job
     */
    Integer insertJob(Job job);

    /**
     * update Job
     */
    Integer updateJob(Job job);

    /**
     * delete Job
     */
    Integer deleteJob(Integer id);

    /**
     * select Job information by Job_ID
     */
    Job selectJobByJobId(Integer id);

    /**
     * select Job information by Job_ID List
     */
    List<Job> selectJobByJobIdList(List<Integer> ids);

    List<Job> getJobByMore(@Param("jobId") Integer jobId, @Param("jobName") String jobName, @Param("comId") Integer comId, @Param("comName") String comName,
                           @Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("type") Integer type, @Param("salaryFloor") Integer salaryFloor, @Param("salaryCap") Integer salaryCap,
                           @Param("active") Integer active, @Param("location") String location, @Param("jobReqList") List<Degree> jobReqList,
                           @Param("industryList") List<Industry> industryList);

    /*create table JOB_INFO
(
	JOB_ID int auto_increment comment '职位ID'
		primary key,
	JOB_NAME varchar(200) not null comment '职位名称',
	JOB_PROF_CODE varchar(20) not null comment '职位类别编号',
	JOB_START_TIME date not null comment '职位起始时间',
	JOB_END_TIME date not null comment '职位截止时间',
	JOB_TYPE int not null comment '职位性质',
	JOB_DESCRIPTION varchar(200) not null comment '职位描述',
	JOB_DUTY varchar(200) null comment '职责描述',
	JOB_HIGHLIGHT varchar(200) null comment '职位亮点',
	JOB_SALARY_FLOOR int null comment '职位薪资下限',
	JOB_SALARY_CAP int null comment '职位薪资上限',
	JOB_LINK varchar(500) null comment '职位链接',
	CV_RECEI_MAIL varchar(200) not null comment '简历接收邮箱',
	CV_NAME_RULE varchar(200) null comment '简历命名规则',
	JOB_ACTIVE int not null comment '职位状态',
	COMPANY_ID int not null comment '企业ID',
	IS_DELETE int default 0 null comment '是否删除',
	IS_DELETE_TIME timestamp null comment '删除时间',
	constraint JOB_COMPANY_ID
		foreign key (COMPANY_ID) references COMPANY_INFO (company_id),

)
comment '职位基本信息表';
*/

    Integer insertJobIndustry(List<Industry> industries);

    Integer insertJobDegree(@Param("jobId") Integer jobId, @Param("degreeList")List<Degree> degreeList);

    Integer insertJobLocation(@Param("jobId") Integer jobId, @Param("locationList") List<Location> locationList);

    Integer insertJobLogo(@Param("jobId") Integer jobId, @Param("logoList") List<Logo> logoList);

    Integer deleteJobLocation(Integer jobId);

    Integer deleteJobLogo(Integer jobId);

    Integer deleteJobIndustry(Integer jobId);

    Integer deleteJobDegree(Integer jobId);

    Integer deleteJobByComId(Integer comId);
}

