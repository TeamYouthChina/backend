package com.youthchina.dto;

import com.youthchina.domain.Qinghong.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2018/12/30.
 */
public class ApplicantDTO {
    private String name;
    private String avatarUrl;
    private Boolean isInJob;
    private Integer currentCompanyId;
    private List<EducationDTO> educations;
    private ContactDTO contacts;
    private List<String> skills;
    private List<WorkDTO> experiences;
    private List<ProjectDTO> projects;
    private List<ExtracurricularDTO> extracurriculars;
    private List<CertificateDTO> certificates;//todo: fixme

    public ApplicantDTO() {
    }

    public ApplicantDTO(Student student) {
        this.name = student.getUsername();
        this.avatarUrl = student.getAvatarUrl();
        this.isInJob = student.getIsInJob();
        this.educations = new ArrayList<>(student.getEducationInfos().size());
        for (EducationInfo educationInfo : student.getEducationInfos()) {
            this.educations.add(new EducationDTO(educationInfo));
        }
        //contact的设置
        this.contacts = new ContactDTO(student.getEmail(), student.getPhonenumber());
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
        List<CertificateDTO> certificates = new ArrayList<>(student.getCertificates().size());
        for (Certificate certificate : student.getCertificates()) {
            certificates.add(new CertificateDTO(certificate));
        }
        this.setCertificates(certificates);
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

    public Boolean getIsInJob() {
        return isInJob;
    }

    public void setIsInJob(Boolean inJob) {
        isInJob = inJob;
    }

    public List<EducationDTO> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationDTO> educations) {
        this.educations = educations;
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


    public Integer getCurrentCompanyId() {
        return currentCompanyId;
    }

    public void setCurrentCompanyId(Integer currentCompanyId) {
        this.currentCompanyId = currentCompanyId;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public ContactDTO getContacts() {
        return contacts;
    }

    public void setContacts(ContactDTO contacts) {
        this.contacts = contacts;
    }
}




