package com.hdp.juc.blokingqueue.arrayblockingqueue;

import java.util.concurrent.BlockingQueue;

/**
 * @author HDP
 * @ClassName: Producer
 * @Description:
 * @date 2022/11/17 14:57
 */
public class Producer implements Runnable {
    protected BlockingQueue queue = null;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            for (int i = 0; i < 3; i++) {
                queue.put(i);
                System.out.println("produce");
                Thread.sleep(10000);
            }
        } catch (
                InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
