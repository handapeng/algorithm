package com.hdp.juc.blokingqueue.transferqueue;

import java.util.concurrent.TransferQueue;

/**
 * @author HDP
 * @ClassName: LinkedTransferQueueConsumer
 * @Description:
 * @date 2022/11/24 10:43
 */
public class LinkedTransferQueueConsumer implements Runnable {
    private final TransferQueue<String> queue;

    public LinkedTransferQueueConsumer(TransferQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            System.out.println("consumer"+Thread.currentThread().getName()+queue.take());
        } catch (
                InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
