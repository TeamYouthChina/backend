package com.youthchina.util.dictionary;

public class CommentTargetType {

    public static final int ESSAY = 20;
    public static final int BRIEFREVIEW = 30;
    public static final int ANSWER = 70;

    public static int getTypeId(String type) {
        switch (type) {
            case "article":
                return ESSAY;
            case "editorial":
                return BRIEFREVIEW;
            case "answer":
                return ANSWER;
            default:
                return 0;
        }
    }
}
