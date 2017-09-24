package com.king.modules.sys.log.entity;

import com.king.common.persistence.BaseEntity;
import com.king.common.type.OperationType;
import com.king.common.type.ResultType;

import javax.persistence.*;

/**
 * @author by yjh
 * @DateTime 2017/9/24 16:14
 */
@Entity
@Table(name = "sys_log")
public class SysLog extends BaseEntity<SysLog> {

    private String operationModular;        //操作模块
    private String operationParameter;      //操作参数
    private OperationType operationType;    //操作类型
    private String describe;                //描述
    private ResultType result;              //操作结果


    @Column(name = "operation_modular")
    public String getOperationModular() {
        return operationModular;
    }

    public void setOperationModular(String operationModular) {
        this.operationModular = operationModular;
    }

    @Column(name = "operation_parameter", length = 2000)
    public String getOperationParameter() {
        return operationParameter;
    }

    public void setOperationParameter(String operationParameter) {
        this.operationParameter = operationParameter;
    }

    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @Column(name = "describes", length = 2000)
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Column(name = "result")
    @Enumerated(EnumType.STRING)
    public ResultType getResult() {
        return result;
    }

    public void setResult(ResultType result) {
        this.result = result;
    }
}
