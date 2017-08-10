package com.king.common.upload;

/**
 * Created by YJH
 * on 2017/8/10 14:27.
 * 注释:
 */
public class UploadFailure {

    private String name;
    private String error;

    public UploadFailure() {
    }

    public UploadFailure(String name, String error) {
        this.name = name;
        this.error = error;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
