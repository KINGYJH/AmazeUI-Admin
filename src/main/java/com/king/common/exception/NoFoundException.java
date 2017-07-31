package com.king.common.exception;

/**
 * Created by YJH
 * on 2017/7/27 9:27.
 * 注释:
 */
public class NoFoundException extends RuntimeException {

    public NoFoundException() {
    }

    public NoFoundException(String message) {
        super(message);
    }

    public NoFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoFoundException(Throwable cause) {
        super(cause);
    }

}
