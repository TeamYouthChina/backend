package com.youthchina.util.dictionary;

public class RichTextRelaType {
    public static final int QUESTION = 10;
    public static final int ESSAY = 20;
    public static final int BRIEFREVIEW = 30;
    public static final int DISCUSS = 60;
    public static final int ANSWER = 70;

    public static int getTypeId(String type) {
        switch (type) {
            case "article":
                return ESSAY;
            case "question":
                return QUESTION;
            case "editorial":
                return BRIEFREVIEW;
            case "discuss":
                return DISCUSS;
            case "answer":
                return ANSWER;
            default:
                return 0;
        }
    }
}
