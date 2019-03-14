package com.youthchina.dto.applicant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-28
 **/
public class ResumeRequestDTO {
    List<String> json;

    public List<String> getJson() {
        return json;
    }

    public void setResume(String resume) {
        this.json = new ArrayList<>();
        this.json.add(resume);
    }
}
