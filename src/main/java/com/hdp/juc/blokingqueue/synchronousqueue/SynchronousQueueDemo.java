package com.hdp.juc.blokingqueue.synchronousqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author HDP
 * @ClassName: SynchronousQueueDemo
 * @Description:
 * @date 2022/11/24 11:13
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        //新建一个SynchronousQueue同步队列
        final BlockingQueue<Integer> queue = new SynchronousQueue<>(true);
        //启动一个生产者线程插入对象
        SynchronousQueueProducer synchronousQueueProducer = new SynchronousQueueProducer(queue);
        new Thread(synchronousQueueProducer).start();
        //启动两个消费者线程移除对象
        SynchronousQueueConsumer synchronousQueueConsumer = new SynchronousQueueConsumer(queue);
        new Thread(synchronousQueueConsumer).start();
        SynchronousQueueConsumer synchronousQueueConsumer1 = new SynchronousQueueConsumer(queue);
        new Thread(synchronousQueueConsumer1).start();
    }
}
