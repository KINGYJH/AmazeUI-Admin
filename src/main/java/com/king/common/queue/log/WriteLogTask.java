package com.king.common.queue.log;

import com.king.common.queue.base.BasicTask;
import com.king.common.queue.base.TaskQueue;
import com.king.common.type.Priority;
import com.king.common.utils.SpringContextHolder;
import com.king.modules.sys.log.entity.SysLog;
import com.king.modules.sys.log.service.ISysLogService;
import org.apache.shiro.SecurityUtils;

/**
 * @author by yjh
 * @DateTime 2017/9/24 16:53
 */
public class WriteLogTask extends BasicTask {

    private static TaskQueue taskQueue = SpringContextHolder.getBean(TaskQueue.class);

    private static ISysLogService sysLogService = SpringContextHolder.getBean(ISysLogService.class);

    private SysLog sysLog;

    public WriteLogTask(SysLog sysLog) {
        this.sysLog = sysLog;
    }

    public WriteLogTask(SysLog sysLog, Priority priority) {
        this.sysLog = sysLog;
        this.setPriority(priority);
    }

    @Override
    public void run() {
        try {
            //执行日志入库
            sysLogService.save(sysLog);
        } catch (Exception e) {
            //失败  放入队列重新执行
            exCount++;
            taskQueue.add(this);
        }

    }
}
