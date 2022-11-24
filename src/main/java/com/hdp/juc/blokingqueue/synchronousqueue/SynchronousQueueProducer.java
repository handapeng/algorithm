package com.hdp.juc.blokingqueue.synchronousqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @author HDP
 * @ClassName: SynchronousQueueProducer
 * @Description:
 * @date 2022/11/24 11:13
 */
public class SynchronousQueueProducer implements Runnable {
    BlockingQueue<Integer> queue;

    public SynchronousQueueProducer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            System.out.println(Thread.currentThread().getName() + "put:" + ++i);
            try {
                queue.put(i);
                Thread.sleep(1000);
            } catch (
                    InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
