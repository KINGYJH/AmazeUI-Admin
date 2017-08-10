package com.king.common.upload;

/**
 * Created by YJH
 * on 2017/8/10 14:16.
 * 注释:
 */
public class FileUploadConfig {

    private String typeName;    //文件用途
    private String dicName;     //上传目录
    private long maxSize;    //文件最大大小
    private String suffixName;  //支持文件后缀名(,分隔)
    private String temp;        //临时文件夹

    public FileUploadConfig() {
    }

    public FileUploadConfig(String typeName, String dicName, long maxSize, String suffixName, String temp) {
        this.typeName = typeName;
        this.dicName = dicName;
        this.maxSize = maxSize;
        this.suffixName = suffixName;
        this.temp = temp;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
}
