package com.youthchina.exception.zhongyang.exception;

/**
 * Created by zhongyangwu on 3/19/19.
 */
public enum InternalStatusCode {

    NOT_LOGIN(4010, "user have not logged in "),
    ACCESS_DENY(4030, "user do not have access to this resource"),
    EXPIRED(4031, "Resource has expired"),
    SQL_ERROR(5001, "database exception"),
    NOT_FOUND(4040, "cannot found resource"),
    SERVICE_UNAVAILABLE(5030, "service unavailable for now");


    private final int statusCode;
    private final String message;

    InternalStatusCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int value() {
        return this.statusCode;
    }

    public String getMessage() {
        return message;

    }
}
