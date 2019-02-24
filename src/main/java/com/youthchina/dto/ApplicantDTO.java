package com.youthchina.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.youthchina.domain.Qinghong.*;
import com.youthchina.domain.zhongyang.User;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhong on 2018/12/30.
 */
public class ApplicantDTO {
    private Integer id;
    private String name;
    private String avatarUrl;
    private String isInJob;
    private String currentCompanyName;
    private List<EducationDTO> educations;
    private List<String> skills;
    private List<String> labels;
    private List<String> emails;
    private List<String> phonenumbers;
    private List<WorkDTO> experiences;
    private List<ProjectDTO> projects;
    private List<ExtracurricularDTO> extracurriculars;
    private List<CertificateDTO> certificates;//todo: fixme

    public ApplicantDTO() {
    }

    public ApplicantDTO(Student student) {
        this.id = student.getId();
        this.name = student.getUsername();
        this.avatarUrl=student.getAvatarUrl();
        this.isInJob=student.getIsInJob();
        this.currentCompanyName=student.getCurrentCompanyName();
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
        List<CertificateDTO> certificates = new ArrayList<>(student.getCertificates().size());
        for(Certificate certificate: student.getCertificates()){
            certificates.add(new CertificateDTO(certificate));
        }
        this.setCertificates(certificates);
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

    public List<EducationDTO> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationDTO> educations) {
        this.educations = educations;
    }

    @JsonIgnore
    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    @JsonIgnore
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


    public String getCurrentCompanyName() {
        return currentCompanyName;
    }

    public void setCurrentCompanyName(String currentCompanyName) {
        this.currentCompanyName = currentCompanyName;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    @JsonGetter("contacts")
    public Map<String, List<String>> getContact() {
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("emails", this.emails);
        map.put("phonenumbers", this.phonenumbers);
        return map;
    }

    @JsonSetter("contacts")
    @SuppressWarnings("unchecked")
    public void setContact(Map<String, Object> map) {
        this.emails = (List) map.get("emails");
        this.phonenumbers = (List) map.get("phonenumbers");
    }
}
