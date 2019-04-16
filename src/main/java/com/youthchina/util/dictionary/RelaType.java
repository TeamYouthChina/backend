package com.youthchina.util.dictionary;

public class RelaType {
    public static final int COMPANY=100;
    public static final int JOB=200;
    public static final int PERSON=300;

    public static int getTypeId(String type) {
        switch (type) {
            case "job":
                return JOB;
            case "company":
                return COMPANY;
            case "PERSON":
                return PERSON;
            default:
                return 0;
        }
    }
}
