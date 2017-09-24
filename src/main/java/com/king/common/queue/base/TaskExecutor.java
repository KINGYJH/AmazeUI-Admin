package com.king.common.queue.base;

import java.util.concurrent.BlockingQueue;

/**
 * 任务执行器
 *
 * @author by yjh
 * @DateTime 2017/9/24 15:40
 */
public class TaskExecutor extends Thread {

    // 任务队列。 队列内容为空的时候，它会一直处于阻塞状态
    private BlockingQueue<ITask> taskQueue;

    // 状态
    private boolean isRunning = true;

    public TaskExecutor(BlockingQueue<ITask> taskQueue) {
        this.taskQueue = taskQueue;
    }

    // 下班。
    public void quit() {
        isRunning = false;
        interrupt();
    }

    @Override
    public void run() {
        while (isRunning) {
            ITask iTask;
            try {
                iTask = taskQueue.take(); // 执行下一个任务
            } catch (InterruptedException e) {
                if (!isRunning) {
                    interrupt();
                    break;
                }
                continue;
            }

            // 执行任务
            iTask.run();
        }

    }
}
