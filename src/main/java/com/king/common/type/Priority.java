package com.king.common.type;

/**
 * 队列优先级别
 *
 * @author by yjh
 * @DateTime 2017/9/24 16:00
 */
public enum Priority {

    LOW("最低", 0),
    DEFAULT("默认级别", 1),
    HIGH("高于默认级别", 2),
    IMMEDIATELY("立刻执行", 3);

    Priority(String name, int value) {
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
