package com.king.common.type;

/**
 * YES OR NO
 *
 * @author by yjh
 * @DateTime 2017/9/24 16:00
 */
public enum YesOrNoStatus {

    ALL("全部", 0),
    YES("是", 1),
    NO("否", 2);

    YesOrNoStatus(String name, int value) {
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
