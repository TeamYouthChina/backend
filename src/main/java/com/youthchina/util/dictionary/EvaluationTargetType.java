package com.youthchina.util.dictionary;

/**
 * Created by zhongyangwu on 4/14/19.
 */
public class EvaluationTargetType {
    public static final int QUESTION = 1;
    public static final int ESSAY = 2;
    public static final int BRIEFREVIEW = 3;
    public static final int VIDEO = 4;
    public static final int COMMENT = 5;
    public static final int DISCUSS = 6;
    public static final int ANSWER = 7;

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
