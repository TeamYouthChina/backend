package com.youthchina.dto;

import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.qingyang.Company;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: youthchina
 * @description: 返回申请者DTO
 * @author: Qinghong Wang
 * @create: 2019-02-23 14:36
 **/
public class ApplicantResponseDTO {
    private Integer id;
    private String name;
    private String avatarUrl;
    private String isInJob;
    private CompanyDTO company;
    private List<String> labels;
    private List<EducationDTO> educations;
    private List<String> emails;
    private List<String> phonenumbers;
    private List<WorkDTO> experiences;
    private List<ProjectDTO> projects;
    private List<ExtracurricularDTO> extracurriculars;
    private List<CertificateDTO> certificates;//todo: fixme

    public ApplicantResponseDTO(Student student) {
        this.id = student.getId();
        this.name = student.getUsername();
        this.avatarUrl=student.getAvatarUrl();
        this.isInJob=student.getIsInJob();
        this.labels=new ArrayList<>();
        for(LabelInfo labelInfo:student.getLabelInfos()){
            String label_chn=labelInfo.getLabel_chn();
            this.labels.add(label_chn);
        }
        this.educations = new ArrayList<>(student.getEducationInfos().size());
        for (EducationInfo educationInfo : student.getEducationInfos()) {
            this.educations.add(new EducationDTO(educationInfo));
        }
        this.emails = new ArrayList<>();
        this.emails.add(student.getEmail());
        this.phonenumbers = new ArrayList<>();
        this.phonenumbers.add(student.getPhonenumber());
        this.experiences = new ArrayList<>(student.getWorks().size());
        for (Work work : student.getWorks()) {
            this.experiences.add(new WorkDTO(work));
        }
        this.projects = new ArrayList<>(student.getProjects().size());
        for (Project project : student.getProjects()) {
            this.projects.add(new ProjectDTO(project));
        }
        this.extracurriculars = new ArrayList<>(student.getActivities().size());
        for (Activity activity : student.getActivities()) {
            this.extracurriculars.add(new ExtracurricularDTO(activity));
        }
        this.certificates = new ArrayList<>(student.getCertificates().size());
        for(Certificate certificate: student.getCertificates()){
            this.certificates.add(new CertificateDTO(certificate));
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getIsInJob() {
        return isInJob;
    }

    public void setIsInJob(String isInJob) {
        this.isInJob = isInJob;
    }

    public CompanyDTO getCompany() {
        return company;
    }

    public void setCompany(CompanyDTO company) {
        this.company = company;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<EducationDTO> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationDTO> educations) {
        this.educations = educations;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhonenumbers() {
        return phonenumbers;
    }

    public void setPhonenumbers(List<String> phonenumbers) {
        this.phonenumbers = phonenumbers;
    }

    public List<WorkDTO> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<WorkDTO> experiences) {
        this.experiences = experiences;
    }

    public List<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }

    public List<ExtracurricularDTO> getExtracurriculars() {
        return extracurriculars;
    }

    public void setExtracurriculars(List<ExtracurricularDTO> extracurriculars) {
        this.extracurriculars = extracurriculars;
    }

    public List<CertificateDTO> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<CertificateDTO> certificates) {
        this.certificates = certificates;
    }
}
