package com.hdp.juc.blokingqueue.synchronousqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @author HDP
 * @ClassName: SynchronousQueueConsumer
 * @Description:
 * @date 2022/11/24 11:13
 */
public class SynchronousQueueConsumer implements Runnable {
    BlockingQueue<Integer> queue;

    public SynchronousQueueConsumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Integer take = queue.take();
                System.out.println(Thread.currentThread().getName() + "take()" + take);
                Thread.sleep(5000);
            } catch (
                    InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
