package com.youthchina.util.dictionary;

public class IsExistTargetType {
    public static final int QUESTION = 1;
    public static final int ANSWER = 2;
    public static final int ESSAY = 3;
    public static final int BRIEFREVIEW = 4;
    public static final int VIDEO = 5;
    public static final int COMMENT = 6;
    public static final int DISCUSS = 7;
    public static int getTypeId(String type) {
        switch (type) {
            case "article":
                return ESSAY;
            case "question":
                return QUESTION;
            case "editorial":
                return BRIEFREVIEW;
            case "video":
                return VIDEO;
            case "comment":
                return COMMENT;
            case "discuss":
                return DISCUSS;
            case "answer":
                return ANSWER;
            default:
                return 0;
        }
    }
}
