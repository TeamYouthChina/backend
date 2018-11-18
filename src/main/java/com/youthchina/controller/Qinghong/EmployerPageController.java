package com.youthchina.controller.Qinghong;

import com.youthchina.domain.Qinghong.Candidate;
import com.youthchina.domain.Qinghong.Invitation;
import com.youthchina.service.Qinghong.EmployerPageService;
import org.springframework.beans.factory.CannotLoadBeanClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employerPage")
public class EmployerPageController {

    @Autowired
    private EmployerPageService employerPageService;

    @RequestMapping("/getCandidate")
    public Candidate getById(Integer id){
        return employerPageService.findCandidate(id);
    }
    @RequestMapping("/getBylabel")
    public List<Candidate> getByLabel(String talent_label){
        return employerPageService.getByLabel(talent_label);
    }
    @RequestMapping("/getByIntention")
    public List<Candidate> getByIntention(String job_intention){
        return employerPageService.getByIntention(job_intention);
    }

    @RequestMapping("/getByTime")
    public List<Candidate> getBytime(String duty_time){
        return employerPageService.getByTime(duty_time);
    }
    @RequestMapping("/getByIndustry")
    public List<Candidate> getByIndustry(String desire_industry){
        return employerPageService.getByIndustry(desire_industry);
    }
    @RequestMapping("/getByLocation")
    public List<Candidate> getByLocation(String desire_location){
        return employerPageService.getByLocation(desire_location);
    }
    @RequestMapping("/getAllCandidate")
    public List<Candidate> getAllCandidate(){
        return employerPageService.getAllCandidate();
    }
    @RequestMapping("/getCandidateCount")
    public Integer getAllCandidateCount(){
        return employerPageService.getAllCandidateCount();
    }
    @RequestMapping("/getInvitation")
    public Invitation getInvitation(Integer id){
        return employerPageService.getInvitation(id);
    }







}
