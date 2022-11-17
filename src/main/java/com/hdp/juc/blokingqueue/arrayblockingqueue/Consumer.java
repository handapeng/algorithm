package com.hdp.juc.blokingqueue.arrayblockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @author HDP
 * @ClassName: Consumer
 * @Description:
 * @date 2022/11/17 14:57
 */
public class Consumer implements Runnable{
    protected BlockingQueue queue = null;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("consumer"+queue.take());
            System.out.println("consumer"+queue.take());
            System.out.println("consumer"+queue.take());
        } catch (
                InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
