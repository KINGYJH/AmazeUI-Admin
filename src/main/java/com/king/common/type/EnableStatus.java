package com.king.common.type;

/**
 * 状态
 *
 * @author by yjh
 * @DateTime 2017/9/24 16:23
 */
public enum EnableStatus {

    ALL("全部", 0),
    ENABLE("启用", 1),
    DISABLE("禁用", 2);

    EnableStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    private String name;

    private int value;

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }

}
