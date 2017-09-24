package com.king.common.queue.base;

import com.king.common.type.Priority;

/**
 * 任务接口
 *
 * @author by yjh
 * @DateTime 2017/9/24 15:39
 */
public interface ITask extends Comparable<ITask> {

    void run();

    void setPriority(Priority priority);

    Priority getPriority();

    void setSequence(int sequence);

    int getSequence();
}
