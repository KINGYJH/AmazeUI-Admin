package com.king.common.type;

/**
 * 结果
 *
 * @author by yjh
 * @DateTime 2017/9/24 17:16
 */
public enum ResultType {

    SUCCESS("成功", 0),
    FAIL("失败", 1);

    ResultType(String name, int value) {
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
