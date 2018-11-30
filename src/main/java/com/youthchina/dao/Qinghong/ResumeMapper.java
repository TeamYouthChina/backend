package com.youthchina.dao.Qinghong;

import com.youthchina.domain.Qinghong.Resume;

public interface ResumeMapper {
    Resume selectResumeById(int resume_id);
    int insertBaseInfo(int stu_id);
    int insertEducationInfo(int edu_id);
    int insertWork(int work_id);
    int updateBaseInfo(int stu_id);
    int updateEducationInfo(int edu_id);
    int updateWork(int work_id);
    int deleteBaseInfo(int stu_id);
    int deleteEducationInfo(int edu_id);
    int deleteWork(int work_id);
}
