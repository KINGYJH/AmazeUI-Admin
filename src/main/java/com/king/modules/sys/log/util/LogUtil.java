package com.king.modules.sys.log.util;

import com.alibaba.fastjson.JSONArray;
import com.king.common.queue.base.TaskQueue;
import com.king.common.queue.log.WriteLogTask;
import com.king.common.type.OperationType;
import com.king.common.type.ResultType;
import com.king.common.utils.SpringContextHolder;
import com.king.modules.sys.log.entity.SysLog;
import com.king.modules.sys.log.service.ISysLogService;
import com.king.modules.sys.user.entity.User;
import com.king.modules.sys.user.util.UserUtil;

import java.util.Date;

/**
 * @author by yjh
 * @DateTime 2017/9/24 16:41
 */
public class LogUtil {

    private static TaskQueue taskQueue = SpringContextHolder.getBean(TaskQueue.class);

    public static void write(Object data, OperationType operationType, String operationModular) {
        write(data, operationType, "", ResultType.SUCCESS, operationModular);

    }

    /**
     * 写入日志
     *
     * @param data             数据
     * @param operationType    操作类型
     * @param describe         描述
     * @param result           操作结果
     * @param operationModular 操作模块
     */
    public static void write(Object data, OperationType operationType, String describe, ResultType result, String operationModular) {
        String strData = JSONArray.toJSONString(data);
        User user = UserUtil.getUser();

        SysLog sysLog = new SysLog();
        sysLog.setCreateDate(new Date());
        sysLog.setCreateUserId(user.getId());
        sysLog.setCreateUserName(user.getAcctName());
        sysLog.setOperationParameter(strData);
        sysLog.setOperationType(operationType);
        sysLog.setDescribe(describe);
        sysLog.setResult(result);
        sysLog.setOperationModular(operationModular);

        taskQueue.add(new WriteLogTask(sysLog));
    }

}
