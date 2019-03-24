package com.youthchina.domain.tianjian;

public class ComRichText {
    private int relaType;
    private int relaId;
    private int compileType;
    private String jsonContent;
    private String textContent;

    public ComRichText(){}

    public int getRelaType() {
        return relaType;
    }

    public void setRelaType(int relaType) {
        this.relaType = relaType;
    }

    public int getRelaId() {
        return relaId;
    }

    public void setRelaId(int relaId) {
        this.relaId = relaId;
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
