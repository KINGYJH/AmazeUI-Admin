package com.king.common.exception;

/**
 * Created by YJH
 * on 2017/8/1 14:00.
 * 注释: 已存在异常
 */
public class ExistException extends RuntimeException {

    public ExistException() {
    }

    public ExistException(String message) {
        super(message);
    }

    public ExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExistException(Throwable cause) {
        super(cause);
    }

}
