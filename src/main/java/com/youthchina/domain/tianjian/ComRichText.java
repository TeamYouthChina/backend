package com.youthchina.domain.tianjian;

import com.youthchina.dto.util.RichTextDTORequest;
import com.youthchina.dto.util.RichTextDTOResponse;

public class ComRichText {
private int textId;
    private int compileType;
    private String jsonContent;
    private String textContent;

    public ComRichText(){}

    public ComRichText(RichTextDTORequest richTextDTORequest){
        this.jsonContent = richTextDTORequest.getBraftEditorRaw();
        this.textContent = richTextDTORequest.getPreviewText();
    }

    public ComRichText(RichTextDTOResponse richTextDTOResponse){
        this.jsonContent = richTextDTOResponse.getBraftEditorRaw();
        this.textContent = richTextDTOResponse.getPreviewText();
    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public int getCompileType() {
        return compileType;
    }

    public void setCompileType(int compileType) {
        this.compileType = compileType;
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
