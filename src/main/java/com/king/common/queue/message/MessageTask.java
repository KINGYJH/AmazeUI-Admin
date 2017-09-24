package com.king.common.queue.message;

import com.king.common.queue.base.BasicTask;

/**
 * 消息任务
 *
 * @author by yjh
 * @DateTime 2017/9/24 15:53
 */
public class MessageTask extends BasicTask {
    private int id;

    public MessageTask(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        // 为了尽量模拟窗口办事的速度，我们这里停顿两秒。
        try {
            Thread.sleep(200);
        } catch (InterruptedException ignored) {
        }

        System.out.println("我的id是：" + id);
    }
}
