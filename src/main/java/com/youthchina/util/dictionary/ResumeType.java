package com.youthchina.util.dictionary;

/**
 * @author Qingyang Zhao
 * @description: ResumeType
 * @date 2019/5/1719:59
 */
public class ResumeType {

    public static final int PDF = 1;
    public static final int RICHTEXT = 2;


    public static int getTypeId(String type) {
        switch (type) {
            case "online":
                return RICHTEXT;
            case "pdf":
                return PDF;
            default:
                return 0;
        }
    }
}
