package com.youthchina.dto;

/**
 * Created by hongshengzhang on 2/17/19.
 */
public class RichTextDTO {
    private String braftEditorRaw;
    private String previewText;
    private String[] resourceList;

    public RichTextDTO(String braftEditorRaw, String previewText, String[] resourceList) {
        this.braftEditorRaw = braftEditorRaw;
        this.previewText = previewText;
        this.resourceList = resourceList;
    }

    public RichTextDTO() {

    }

    public String getBraftEditorRaw() {
        return braftEditorRaw;
    }

    public void setBraftEditorRaw(String braftEditorRaw) {
        this.braftEditorRaw = braftEditorRaw;
    }

    public String getPreviewText() {
        return previewText;
    }

    public void setPreviewText(String previewText) {
        this.previewText = previewText;
    }

    public String[] getResourceList() {
        return  resourceList;
    }

    public void setResourceList(String[] resourceList) {
        this.resourceList = resourceList;
    }
}
