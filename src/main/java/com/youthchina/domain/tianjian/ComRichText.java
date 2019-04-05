package com.youthchina.domain.tianjian;

import com.youthchina.dto.util.RichTextRequestDTO;
import com.youthchina.dto.util.RichTextResponseDTO;

public class ComRichText {
private int textId;
    private int compileType;
    private String jsonContent;
    private String textContent;

    public ComRichText(){}

    public ComRichText(RichTextRequestDTO richTextRequestDTO){
        this.jsonContent = richTextRequestDTO.getBraftEditorRaw();
        this.textContent = richTextRequestDTO.getPreviewText();
        this.compileType = richTextRequestDTO.getCompiletype();
    }

    public ComRichText(RichTextResponseDTO richTextResponseDTO){
        this.jsonContent = richTextResponseDTO.getBraftEditorRaw();
        this.textContent = richTextResponseDTO.getPreviewText();
        this.compileType = richTextResponseDTO.getCompiletype();
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
