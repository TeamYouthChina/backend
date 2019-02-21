package com.youthchina.dto;

import java.util.List;

public class RichTextDTO {

    private String braftEditorRaw;
    private String previewText;
    private List<String> resourceList;

    public void RichTextDTO(){}

    public void setBraftEditorRaw(String braftEditorRaw){ this.braftEditorRaw = braftEditorRaw; }

    public String getBraftEditorRaw(){
        return braftEditorRaw;
    }

    public void setPreviewText(String previewText){
        this.previewText = previewText;
    }

    public String getPreviewText(){
        return previewText;
    }

    public void setResourceList(List resourceList){
        this.resourceList = resourceList;
    }

    public List getResourceList(){ return resourceList; }
}
