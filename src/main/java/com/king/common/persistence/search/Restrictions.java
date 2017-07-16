package com.king.common.persistence.search;

/**
 * @author by yjh
 * @DateTime 2017/7/15 21:28
 * 引用 org.hibernate.criterion.Restrictions;
 */
public class Restrictions {

    public static Criterion like(String propertyName, Object value) {
        return like(propertyName, value, MatchMode.ALL);
    }

    /**
     * 模糊查询 like
     *
     * @param propertyName 字段名
     * @param value        值
     * @param matchMode    模糊查询方式
     * @return Criterion
     */
    public static Criterion like(String propertyName, Object value, MatchMode matchMode) {
        return new Criterion(propertyName, matchMode.toMatchCriterionEnum(), new Object[]{value});
    }

    /**
     * 等于 =
     *
     * @param propertyName 字段名
     * @param value        值
     * @return Criterion
     */
    public static Criterion eq(String propertyName, Object value) {
        return new Criterion(propertyName, CriterionEnum.EQ, new Object[]{value});
    }

    /**
     * 大于 >
     *
     * @param propertyName 字段名
     * @param value        值
     * @return Criterion
     */
    public static Criterion gt(String propertyName, Object value) {
        return new Criterion(propertyName, CriterionEnum.GT, new Object[]{value});
    }

    /**
     * 小于 <
     *
     * @param propertyName 字段名
     * @param value        值
     * @return Criterion
     */
    public static Criterion lt(String propertyName, Object value) {
        return new Criterion(propertyName, CriterionEnum.LT, new Object[]{value});
    }

    /**
     * 大于等于 >=
     *
     * @param propertyName 字段名
     * @param value        值
     * @return Criterion
     */
    public static Criterion ge(String propertyName, Object value) {
        return new Criterion(propertyName, CriterionEnum.GT_EQ, new Object[]{value});
    }

    /**
     * 小于等于 <=
     *
     * @param propertyName 字段名
     * @param value        值
     * @return Criterion
     */
    public static Criterion le(String propertyName, Object value) {
        return new Criterion(propertyName, CriterionEnum.LT_EQ, new Object[]{value});
    }

    /**
     * 不等于 <>
     *
     * @param propertyName 字段名
     * @param value        值
     * @return Criterion
     */
    public static Criterion ne(String propertyName, Object value) {
        return new Criterion(propertyName, CriterionEnum.NOT_EQ, new Object[]{value});
    }

    /**
     * 包含 in
     *
     * @param propertyName 字段名
     * @param value        值
     * @return Criterion
     */
    public static Criterion in(String propertyName, Object[] value) {
        return new Criterion(propertyName, CriterionEnum.IN, value);
    }

    /**
     * 为null isNull
     *
     * @param propertyName 字段名
     * @return Criterion
     */
    public static Criterion isNull(String propertyName) {
        return new Criterion(propertyName, CriterionEnum.IS_NULL, null);
    }

    /**
     * 不为null is not null
     *
     * @param propertyName 字段名
     * @return Criterion
     */
    public static Criterion isNotNull(String propertyName) {
        return new Criterion(propertyName, CriterionEnum.IS_NOT_NULL, null);
    }

    /**
     * between ? and ?
     *
     * @param propertyName 字段名
     * @param lo           低值
     * @param hi           高值
     * @return Criterion
     */
    public static Criterion between(String propertyName, Object lo, Object hi) {
        return new Criterion(propertyName, CriterionEnum.IS_NOT_NULL, new Object[]{lo, hi});
    }

    /**
     * or
     *
     * @param predicates 条件
     * @return Criterion
     */
    public static Criterion or(Criterion... predicates) {
        return new Criterion(null, CriterionEnum.OR, predicates);
    }
}
