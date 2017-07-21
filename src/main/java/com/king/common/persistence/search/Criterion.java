package com.king.common.persistence.search;

import com.king.common.exception.ParameterException;

/**
 * Created by YJH
 * on 2017/7/14 16:43.
 * 注释:
 */
public class Criterion {

    private String propertyName;            //属性名
    private CriterionEnum criterionEnum;    //条件类型
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
     * 转换sql
     *
     * @return sql
     */
    public String toSqlStr() {
        StringBuilder sb = new StringBuilder();
        switch (this.criterionEnum) {
            case LIKE_LEFT:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" AND ").append(this.getPropertyName()).append(" LIKE '%").append(value).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case LIKE_RIGHT:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" AND ").append(this.getPropertyName()).append(" LIKE '").append(value).append("%'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case LIKE_ALL:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" AND ").append(this.getPropertyName()).append(" LIKE '%").append(value).append("%'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case EQ:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" AND ").append(this.getPropertyName()).append(" = '").append(value).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case GT:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" AND ").append(this.getPropertyName()).append(" > '").append(value).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case LT:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" AND ").append(this.getPropertyName()).append(" < '").append(value).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case GT_EQ:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" AND ").append(this.getPropertyName()).append(" >= '").append(value).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case LT_EQ:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" AND ").append(this.getPropertyName()).append(" <= '").append(value).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case NOT_EQ:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" AND ").append(this.getPropertyName()).append(" <> '").append(value).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case IN:
                try {
                    sb.append(" AND ").append(this.getPropertyName()).append(" IN (");
                    for (int i = 0; i < this.getValues().length; i++) {
                        String value = String.valueOf(this.getValues()[i]);
                        if (i != this.getValues().length - 1) {
                            sb.append("'").append(value).append("',");
                        } else {
                            sb.append("'").append(value).append("'");
                        }
                    }
                    sb.append(")");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case IS_NULL:
                sb.append(" AND ISNULL(").append(this.getPropertyName()).append(")");
                break;
            case IS_NOT_NULL:
                sb.append(" AND ").append(this.getPropertyName()).append(" IS NOT NULL");
                break;
            case BETWEEN:
                try {
                    String value_1 = String.valueOf(this.getValues()[0]);
                    String value_2 = String.valueOf(this.getValues()[1]);
                    sb.append(" AND ").append(this.getPropertyName()).append(" BETWEEN '").append(value_1).append("' AND '").append(value_2).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case OR:
                if (this.getValues().length > 0) {
                    sb.append(" AND (");
                    try {
                        for (Object obj : this.getValues()) {
                            Criterion criterion = (Criterion) obj;
                            sb.append(criterion.toSqlStrOr());
                        }
                    } catch (Exception e) {
                        throw new ParameterException(this.getPropertyName() + "参数错误.");
                    }
                    sb.append(")");
                } else {
                    sb.append(" AND 1=1");
                }
                return sb.toString().replaceFirst(" OR", "");
        }
        return sb.toString();
    }


    /**
     * 转换sql 条件OR
     *
     * @return sql
     */
    private String toSqlStrOr() {
        StringBuilder sb = new StringBuilder();
        switch (this.criterionEnum) {
            case LIKE_LEFT:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" OR ").append(this.getPropertyName()).append(" LIKE '%").append(value).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case LIKE_RIGHT:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" OR ").append(this.getPropertyName()).append(" LIKE '").append(value).append("%'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case LIKE_ALL:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" OR ").append(this.getPropertyName()).append(" LIKE '%").append(value).append("%'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case EQ:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" OR ").append(this.getPropertyName()).append(" = '").append(value).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case GT:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" OR ").append(this.getPropertyName()).append(" > '").append(value).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case LT:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" OR ").append(this.getPropertyName()).append(" < '").append(value).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case GT_EQ:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" OR ").append(this.getPropertyName()).append(" >= '").append(value).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case LT_EQ:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" OR ").append(this.getPropertyName()).append(" <= '").append(value).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case NOT_EQ:
                try {
                    String value = String.valueOf(this.getValues()[0]);
                    sb.append(" OR ").append(this.getPropertyName()).append(" <> '").append(value).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case IN:
                try {
                    sb.append(" OR ").append(this.getPropertyName()).append(" IN (");
                    for (int i = 0; i < this.getValues().length; i++) {
                        String value = String.valueOf(this.getValues()[i]);
                        if (i != this.getValues().length - 1) {
                            sb.append("'").append(value).append("',");
                        } else {
                            sb.append("'").append(value).append("'");
                        }
                    }
                    sb.append(")");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case IS_NULL:
                sb.append(" OR ISNULL(").append(this.getPropertyName()).append(")");
                break;
            case IS_NOT_NULL:
                sb.append(" OR ").append(this.getPropertyName()).append(" IS NOT NULL");
                break;
            case BETWEEN:
                try {
                    String value_1 = String.valueOf(this.getValues()[0]);
                    String value_2 = String.valueOf(this.getValues()[1]);
                    sb.append(" OR ").append(this.getPropertyName()).append(" BETWEEN '").append(value_1).append("' AND '").append(value_2).append("'");
                } catch (Exception e) {
                    throw new ParameterException(this.getPropertyName() + "参数错误.");
                }
                break;
            case OR:
                if (this.getValues().length > 0) {
                    sb.append(" OR (");
                    try {
                        for (Object obj : this.getValues()) {
                            Criterion criterion = (Criterion) obj;
                            sb.append(criterion.toSqlStrOr());
                        }
                    } catch (Exception e) {
                        throw new ParameterException(this.getPropertyName() + "参数错误.");
                    }
                    sb.append(")");
                } else {
                    sb.append(" AND 1=1");
                }
                break;
        }
        return sb.toString();
    }
}
