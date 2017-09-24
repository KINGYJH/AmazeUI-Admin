package com.king.common.queue.base;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 任务队列
 *
 * @author by yjh
 * @DateTime 2017/9/24 15:44
 */
public class TaskQueue {

    private AtomicInteger mAtomicInteger = new AtomicInteger();

    private BlockingQueue<ITask> mTaskQueue;
    // 多个队列执行器
    private TaskExecutor[] mTaskExecutors;

    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

//    //初始化执行器个数
//    public TaskQueue() {
//        mTaskQueue = new PriorityBlockingQueue<>();
//        mTaskExecutors = new TaskExecutor[size];
//    }

    public void init() {
        mTaskQueue = new PriorityBlockingQueue<>();
        mTaskExecutors = new TaskExecutor[size];

        start();
    }

    /**
     * 开始
     */
    public void start() {
        stop();
        for (int i = 0; i < mTaskExecutors.length; i++) {
            mTaskExecutors[i] = new TaskExecutor(mTaskQueue);
            mTaskExecutors[i].start();
        }
    }

    /**
     * 结束
     */
    public void stop() {
        if (mTaskExecutors != null)
            for (TaskExecutor taskExecutor : mTaskExecutors) {
                if (taskExecutor != null) taskExecutor.quit();
            }
    }

    /**
     * 添加任务
     *
     * @param task 任务
     * @return 队列个数
     */
    public <T extends ITask> int add(T task) {
        if (!mTaskQueue.contains(task)) {
            //incrementAndGet()方法会每次递增1，其实它相当于：
            //mAtomicInteger.addAndGet(1);
            task.setSequence(mAtomicInteger.incrementAndGet());
            mTaskQueue.add(task);
        }
        // 返回队列个数
        return mTaskQueue.size();
    }
}
