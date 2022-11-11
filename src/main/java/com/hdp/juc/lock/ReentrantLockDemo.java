package com.hdp.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author HDP
 * @ClassName: ReentrantLockDemo
 * @Description:同步执行
 * @date 2022/11/11 9:43
 */
public class ReentrantLockDemo {
    private static int sum = 0;
    private static Lock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    for (int j = 0; j < 10000; j++) {
                        sum++;
                    }
                } finally {
                    lock.unlock();
                }
            });
            thread.start();
        }
        Thread.sleep(2000);
        System.out.println(sum);
    }
}
