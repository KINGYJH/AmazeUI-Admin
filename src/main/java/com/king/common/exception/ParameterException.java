package com.king.common.exception;

/**
 * @author by yjh
 * @DateTime 2017/7/15 20:12
 * 参数异常
 */
public class ParameterException extends RuntimeException {

    public ParameterException() {
    }

    public ParameterException(String message) {
        super(message);
    }

    public ParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterException(Throwable cause) {
        super(cause);
    }

}
