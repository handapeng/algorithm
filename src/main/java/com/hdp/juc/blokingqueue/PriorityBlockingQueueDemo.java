package com.hdp.juc.blokingqueue;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author HDP
 * @ClassName: PriorityBlockingQueueDemo
 * @Description:
 * @date 2022/11/24 11:28
 */
public class PriorityBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //创建优先级阻塞队列  Comparator为null,自然排序
        PriorityBlockingQueue queue = new PriorityBlockingQueue<>(5);
        PriorityBlockingQueue queue1=new PriorityBlockingQueue<Integer>(
                5, (o1, o2) -> o2-o1);
        Random random = new Random();
        System.out.println("put:");
        for (int i = 0; i < 5; i++) {
            int j = random.nextInt(100);
            System.out.println(j + "");
            queue.put(j);
        }
        System.out.println("take");
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.take()+" ");
        }
    }
}
