package com.king.common.upload;

/**
 * Created by YJH
 * on 2017/8/10 14:27.
 * 注释:
 */
public class UploadResult {

    private Object[] files;

    public UploadResult() {
    }

    public UploadResult(Object[] files) {
        this.files = files;
    }

    public Object[] getFiles() {
        return files;
    }

    public void setFiles(Object[] files) {
        this.files = files;
    }
}
