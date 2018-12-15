package com.youthchina.exception.zhongyang;

import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.StatusDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by zhongyangwu on 11/18/18.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BaseException extends Throwable implements ResponseDTO {
    public int statusCode;
    public StatusDTO status;

    public BaseException() {
        super();
        this.statusCode = 500;
        this.status = new StatusDTO(5000, "Unknown error");
    }

    public BaseException(int code, int statusCode, String message) {
        super();
        this.status = new StatusDTO(code, message);
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "BaseException{" +
                "statusCode=" + statusCode +
                ", status=" + status +
                '}';
    }

    @Override
    public StatusDTO getStatus() {
        return status;
    }

    @Override
    public void setStatus(StatusDTO status) {
        this.status = status;
    }
}
