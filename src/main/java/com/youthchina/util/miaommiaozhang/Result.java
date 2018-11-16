package com.youthchina.util.miaommiaozhang;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author miaomiaozhang
 * @date 2018-11-13
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Result<T extends Object> implements Serializable {

    private static final long serialVersionUID = -3379359587196074790L;

    private Integer code = 0;
    private String message = "";
    private T data;

    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Result<T> setCode(String code) {
        this.code = Integer.parseInt(code);
        return this;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }
}
