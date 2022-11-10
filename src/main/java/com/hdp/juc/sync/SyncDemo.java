package com.hdp.juc.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @author HDP
 * @ClassName: SyncDemo
 * @Description:
 * @date 2022/11/8 15:52
 */
@Slf4j
public class SyncDemo {

    private static volatile int count = 0;

    public static void increment() {
        count++;
    }

    public static void decrement() {
        count--;
    }
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        });
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                decrement();
            }
        });
        t.start();
        t1.start();
        t.join();
        t1.join();

        log.info("count={}", count);
    }
}
