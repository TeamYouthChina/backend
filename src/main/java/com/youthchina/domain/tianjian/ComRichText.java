package com.youthchina.domain.tianjian;

public class ComRichText {
    private int rela_type;
    private int rela_id;
    private int compile_type;
    private String json_content;
    private String text_content;

    public ComRichText(){}

    public int getRela_type() {
        return rela_type;
    }

    public void setRela_type(int rela_type) {
        this.rela_type = rela_type;
    }

    public int getRela_id() {
        return rela_id;
    }

    public void setRela_id(int rela_id) {
        this.rela_id = rela_id;
    }

    public int getCompile_type() {
        return compile_type;
    }

    public void setCompile_type(int compile_type) {
        this.compile_type = compile_type;
    }

    public String getJson_content() {
        return json_content;
    }

    public void setJson_content(String json_content) {
        this.json_content = json_content;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }
}
