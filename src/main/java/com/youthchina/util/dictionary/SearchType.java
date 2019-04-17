package com.youthchina.util.dictionary;

import jdk.internal.joptsimple.internal.Strings;

/**
 * Created by zhongyangwu on 4/17/19.
 */
public class SearchType {
    public static final String ARTICLE = "article";
    public static final String EDITORIAL = "editorial";
    public static final String ANSWER = "answer";
    public static final String QUESTION = "question";
    public static final String USER = "user";
    public static final String JOB = "job";
    public static final String COMPANY = "company";
    public static final String COMMENT = "comment";

    public static final String ALL = "all";


    /**
     *
     * @return a string contains all possible type
     */
    public static String getNameString() {
        return Strings.join(new String[]{ARTICLE, EDITORIAL, ANSWER, QUESTION, USER, JOB, ALL, COMMENT, COMPANY}, ", ");
    }
}
