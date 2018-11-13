package com.youthchina.controller.jinhao;

import com.youthchina.domain.jinhao.CompanyAndJob;
import com.youthchina.domain.jinhao.StuCollect;
import com.youthchina.service.jinhao.InternPageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * create by jinhaohu on 11/12/18
 */
@RestController
@RequestMapping("/internpage/**")
public class InternPageController {
    @Resource
    InternPageService internPageService;

    @RequestMapping("/getcompanyandjob")
    public CompanyAndJob getCompanyAndJob(String company_id, String job_id){
        return internPageService.getCompanyAndJob(company_id, job_id);
    }

    @RequestMapping("/isjobcollected")
    public boolean isJobCollected(String job_id, String stu_id){
        StuCollect stuCollect = new StuCollect();
        stuCollect.setStu_id(stu_id);
        stuCollect.setJob_id(job_id);
        return internPageService.isJobCollected(stuCollect);
    }

    @RequestMapping("/iscompanycollected")
    public boolean isCompanyCollected(String company_id, String stu_id){
        StuCollect stuCollect = new StuCollect();
        stuCollect.setStu_id(stu_id);
        stuCollect.setJob_id(company_id);
        return internPageService.isCompanyCollected(stuCollect);
    }

    @RequestMapping("/collectjob")
    public Integer collectJob(String job_id, String stu_id){
        StuCollect stuCollect = new StuCollect();
        stuCollect.setStu_id(stu_id);
        stuCollect.setJob_id(job_id);
        return internPageService.collectJob(stuCollect);
    }

    @RequestMapping("/collectcompany")
    public Integer collectCompany(String company_id, String stu_id){
        StuCollect stuCollect = new StuCollect();
        stuCollect.setStu_id(stu_id);
        stuCollect.setJob_id(company_id);
        return internPageService.collectCompany(stuCollect);
    }

    @RequestMapping("/cancelcollectcompany")
    public Integer cancelCollectCompany(String company_id, String stu_id){
        StuCollect stuCollect = new StuCollect();
        stuCollect.setStu_id(stu_id);
        stuCollect.setJob_id(company_id);
        return internPageService.cancelCollectCompany(stuCollect);
    }

    @RequestMapping("/cancelcollectjob")
    public Integer cancelCollectJob(String job_id, String stu_id){
        StuCollect stuCollect = new StuCollect();
        stuCollect.setStu_id(stu_id);
        stuCollect.setJob_id(job_id);
        return internPageService.cancelCollectJob(stuCollect);
    }
}
