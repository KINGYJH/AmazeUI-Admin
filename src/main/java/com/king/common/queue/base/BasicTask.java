package com.king.common.queue.base;

import com.king.common.type.Priority;

/**
 * @author by yjh
 * @DateTime 2017/9/24 16:02
 */
public abstract class BasicTask implements ITask {

    // 默认优先级。
    private Priority priority = Priority.DEFAULT;
    private int sequence;

    //任务执行次数
    protected int exCount = 0;

    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public int getSequence() {
        return sequence;
    }

    // 做优先级比较。
    @Override
    public int compareTo(ITask another) {
        final Priority me = this.getPriority();
        final Priority it = another.getPriority();
        return me == it ? this.getSequence() - another.getSequence() :
                it.ordinal() - me.ordinal();
    }

}
