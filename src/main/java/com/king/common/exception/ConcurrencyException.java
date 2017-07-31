package com.king.common.exception;

/**
 * Created by YJH
 * on 2017/7/28 14:09.
 * 注释: 并发异常
 */
public class ConcurrencyException extends RuntimeException {
    public ConcurrencyException() {
    }

    public ConcurrencyException(String message) {
        super(message);
    }

    public ConcurrencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConcurrencyException(Throwable cause) {
        super(cause);
    }

}
