package com.king.common.persistence.search;

/**
 * Created by YJH
 * on 2017/7/14 16:43.
 * 注释:
 */
public class Criterion {

    private String propertyName;            //属性名
    private CriterionEnum criterionEnum;    //条件累心
    private Object[] values;                //值

    public Criterion() {

    }

    public Criterion(String propertyName, CriterionEnum criterionEnum, Object[] values) {
        this.propertyName = propertyName;
        this.criterionEnum = criterionEnum;
        this.values = values;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public CriterionEnum getCriterionEnum() {
        return criterionEnum;
    }

    public void setCriterionEnum(CriterionEnum criterionEnum) {
        this.criterionEnum = criterionEnum;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    /**
     * 装换sql
     *
     * @return
     */
    public String toSqlStr() {
        return "";
    }
}
