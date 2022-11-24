package com.hdp.juc.blokingqueue.transferqueue;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @author HDP
 * @ClassName: LinkedTransferQueueDemo
 * @Description:
 * @date 2022/11/24 10:49
 */
public class LinkedTransferQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        TransferQueue<String> queue = new LinkedTransferQueue<>();
        Thread produce = new Thread(new LinkedTransferQueueProducer(queue));
        produce.setDaemon(true);
        produce.start();
        for (int i = 0; i < 10; i++) {
            Thread consumer = new Thread(new LinkedTransferQueueConsumer(queue));
            consumer.setDaemon(true);
            consumer.start();
            Thread.sleep(1000);
        }
    }
}
