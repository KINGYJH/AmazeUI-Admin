package com.king.common.type;

/**
 * 操作类型
 *
 * @author by yjh
 * @DateTime 2017/9/24 16:18
 */
public enum OperationType {

    DEL("删除", 0),
    SAVE("新增", 1),
    UPDATE("修改", 2),
    SEARCH("查询", 3),
    OTHER("其他", 4);

    OperationType(String name, int value) {
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
