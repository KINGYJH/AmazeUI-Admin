package com.king.common.exception;

/**
 * Created by YJH
 * on 2017/8/1 16:19.
 * 注释: 数据错误异常
 */
public class DataErrorException extends RuntimeException {
    public DataErrorException() {
    }

    public DataErrorException(String message) {
        super(message);
    }

    public DataErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataErrorException(Throwable cause) {
        super(cause);
    }
}
