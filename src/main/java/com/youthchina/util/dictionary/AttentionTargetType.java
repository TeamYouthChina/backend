package com.youthchina.util.dictionary;

/**
 * Created by zhongyangwu on 4/15/19.
 */
public class AttentionTargetType {
    public static final int JOB = -1;
    public static final int COMPANY = -2;
    public static final int QUESTION = 10;
    public static final int ESSAY = 20;
    public static final int BRIEFREVIEW = 30;
    public static final int VIDEO = 40;
    public static final int COMMENT = 50;
    public static final int DISCUSS = 60;
    public static final int ANSWER = 70;



    public static int getTypeId(String type) {
        switch (type) {
            case "job":
                return JOB;
            case "company":
                return COMPANY;
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