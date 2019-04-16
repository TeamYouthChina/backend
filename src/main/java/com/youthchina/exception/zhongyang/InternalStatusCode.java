package com.youthchina.exception.zhongyang;

/**
 * Created by zhongyangwu on 3/19/19.
 */
public enum InternalStatusCode {

    NOT_LOGIN(4010, "user have not logged in "),
    ACCESS_DENY(4030, "user do not have access to this resource");



    private final int statusCode;
    private final String message;

    InternalStatusCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int value(){
        return this.statusCode;
    }
}