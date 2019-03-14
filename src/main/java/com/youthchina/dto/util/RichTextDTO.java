package com.youthchina.dto.util;

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
    private List<String> resourceIdList;

    public RichTextDTO() {

    }

    public RichTextDTO(String json) {
        try {
            RichTextDTO processed = new ObjectMapper().readValue(json, RichTextDTO.class);
            this.braftEditorRaw = processed.braftEditorRaw;
            this.previewText = processed.previewText;
            this.resourceIdList = processed.resourceIdList;
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @JsonGetter("braftEditorRaw")
    public HashMap<String, Object> getJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return new ObjectMapper().readValue(this.braftEditorRaw, new TypeReference<HashMap<String, Object>>() {
            });
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
            System.out.println("12345");
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

    public void setresourceIdList(List resourceIdList) {
        this.resourceIdList = resourceIdList;
    }

    public List getresourceIdList() {
        return resourceIdList;
    }
}
