package com.king.common.persistence.search;

/**
 * Created by YJH
 * on 2017/7/14 16:30.
 * 注释: sql 查询条件枚举
 */
public enum CriterionEnum {

    LIKE_LEFT("'%_P'", 0),
    LIKE_RIGHT("'_p%'", 1),
    LIKE_ALL("'%_p%'", 2),
    EQ("==", 3),
    GT(">", 4),
    LT("<", 5),
    GT_EQ(">=", 6),
    LT_EQ("<=", 7),
    NOT_EQ("<>", 8),
    IN("IN", 9),
    IS_NULL("ISNULL(_p)", 10),
    IS_NOT_NULL("IS NOT NULL", 11),
    BETWEEN("_p BETWEEN _v and _v", 12),
    OR("OR", 13);

    private CriterionEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    private String name;

    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
