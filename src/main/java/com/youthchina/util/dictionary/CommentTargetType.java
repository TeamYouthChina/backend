package com.youthchina.util.dictionary;

public class CommentTargetType {

    public static final int ESSAY = 1;
    public static final int BRIEFREVIEW = 2;
    public static final int ANSWER = 4;

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
