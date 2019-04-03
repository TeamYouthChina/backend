package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.*;
import com.youthchina.dto.RequestDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2018/12/30.
 */
public class ApplicantRequestDTO implements RequestDTO {
    private String name;
    private String avatarUrl;
    private Boolean isInJob;
    private Integer currentCompanyId;
    private List<EducationRequestDTO> educations;
    private ContactDTO contacts;
    private List<String> skills;
    private List<WorkRequestDTO> experiences;
    private List<ProjectRequestDTO> projects;
    private List<ExtracurricularRequestDTO> extracurriculars;
    private List<CertificateRequestDTO> certifications;//todo: fixme

    public ApplicantRequestDTO() {
    }

    public ApplicantRequestDTO(Student student) {
        this.name = student.getUsername();
        this.avatarUrl = student.getAvatarUrl();
        this.isInJob = student.getIsInJob();
        this.educations = new ArrayList<>(student.getEducationInfos().size());
        for (EducationInfo educationInfo : student.getEducationInfos()) {
            this.educations.add(new EducationRequestDTO(educationInfo));
        }
        //contact的设置
        this.contacts = new ContactDTO(student.getEmail(), student.getPhonenumber());
        this.experiences = new ArrayList<>(student.getWorks().size());
        for (Work work : student.getWorks()) {
            this.experiences.add(new WorkRequestDTO(work));
        }
        this.projects = new ArrayList<>(student.getProjects().size());
        for (Project project : student.getProjects()) {
            this.projects.add(new ProjectRequestDTO(project));
        }
        this.extracurriculars = new ArrayList<>(student.getActivities().size());
        for (Activity activity : student.getActivities()) {
            this.extracurriculars.add(new ExtracurricularRequestDTO(activity));
        }
        List<CertificateRequestDTO> certificates = new ArrayList<>(student.getCertificates().size());
        for (Certificate certificate : student.getCertificates()) {
            certificates.add(new CertificateRequestDTO(certificate));
        }
        this.setCertifications(certificates);
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

    public List<EducationRequestDTO> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationRequestDTO> educations) {
        this.educations = educations;
    }


    public List<WorkRequestDTO> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<WorkRequestDTO> experiences) {
        this.experiences = experiences;
    }

    public List<ProjectRequestDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectRequestDTO> projects) {
        this.projects = projects;
    }

    public List<ExtracurricularRequestDTO> getExtracurriculars() {
        return extracurriculars;
    }

    public void setExtracurriculars(List<ExtracurricularRequestDTO> extracurriculars) {
        this.extracurriculars = extracurriculars;
    }

    public List<CertificateRequestDTO> getCertifications() {
        return certifications;
    }

    public void setCertifications(List<CertificateRequestDTO> certifications) {
        this.certifications = certifications;
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




