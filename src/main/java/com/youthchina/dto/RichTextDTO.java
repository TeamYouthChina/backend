package com.youthchina.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RichTextDTO {

    private String braftEditorRaw;
    private String previewText;
    private List<String> resourceList;

    public void RichTextDTO() {
    }

    public void setBraftEditorRaw(String braftEditorRaw) {
        this.braftEditorRaw = braftEditorRaw;
    }


    @JsonSetter
    public void setBraftEditorRaw(Map<String, Object> braftEditorRaw) {
        try {
            this.braftEditorRaw = new ObjectMapper().writeValueAsString(braftEditorRaw);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @JsonGetter("braftEditorRaw")
    public HashMap<String, Object> getJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return new ObjectMapper().readValue(this.braftEditorRaw, new TypeReference<HashMap<String,Object>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @JsonIgnore
    public String getBraftEditorRaw() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this.braftEditorRaw);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setPreviewText(String previewText) {
        this.previewText = previewText;
    }

    public String getPreviewText() {
        return previewText;
    }

    public void setResourceList(List resourceList) {
        this.resourceList = resourceList;
    }

    public List getResourceList() {
        return resourceList;
    }
}
