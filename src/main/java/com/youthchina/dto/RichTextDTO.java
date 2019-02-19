package com.youthchina.dto;

public class RichTextDTO {

    private String braftEditorRaw;
    private String previewText;
    private String resourceList;

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

    public void setResourceList(String resourceList){
        this.resourceList = resourceList;
    }

    public String getResourceList(){ return resourceList; }
}
