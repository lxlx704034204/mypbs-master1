package com.pbs.ams.common.exception;

import com.pbs.ams.common.constant.StatusCode;

/**
 * Created by whb on 2017/7/3.
 * Exception统一在Service层处理
 */
public class AmsException extends Exception{

    private StatusCode statusCode;

    public AmsException(StatusCode statusCode) {
        super(statusCode.getMessage());
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
