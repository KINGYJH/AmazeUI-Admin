package com.admin;

import com.king.common.type.Priority;
import com.king.common.queue.base.TaskQueue;
import com.king.common.queue.message.MessageTask;

/**
 * @author by yjh
 * @DateTime 2017/9/24 15:54
 */
public class TaskTest {

    public static void main(String[] args) {
        // 开一个窗口，这样会让优先级更加明显。
        TaskQueue taskQueue = new TaskQueue();
        taskQueue.start(); //  // 某机构开始工作。

        // 为了显示出优先级效果，我们预添加3个在前面堵着，让后面的优先级效果更明显。
        taskQueue.add(new MessageTask(110));
        taskQueue.add(new MessageTask(112));
        taskQueue.add(new MessageTask(122));

        for (int i = 0; i < 10; i++) { // 从第0个人开始。
            MessageTask task = new MessageTask(i);
            if (1 == i) {
                task.setPriority(Priority.LOW); // 让第2个进入的人最后办事。
            } else if (8 == i) {
                task.setPriority(Priority.HIGH); // 让第9个进入的人第二个办事。
            } else if (9 == i) {
                task.setPriority(Priority.IMMEDIATELY); // 让第10个进入的人第一个办事。
            }
            // ... 其它进入的人，按照进入顺序办事。
            taskQueue.add(task);
        }
    }

}
