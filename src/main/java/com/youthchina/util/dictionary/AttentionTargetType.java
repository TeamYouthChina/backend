package com.youthchina.util.dictionary;

/**
 * Created by zhongyangwu on 4/15/19.
 */
public class AttentionTargetType {
    public static final int JOB = -1;
    public static final int COMPANY = -2;
    public static final int ESSAY = 2;

    public static int getTypeId(String type) {
        switch (type) {
            case "job":
                return JOB;
            case "company":
                return COMPANY;
            case "article":
                return ESSAY;
            default:
                return 0;
        }
    }
}
