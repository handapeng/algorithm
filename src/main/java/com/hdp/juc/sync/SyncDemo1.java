package com.hdp.juc.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @author HDP
 * @ClassName: SyncDemo1
 * @Description:
 * @date 2022/11/8 19:41
 */
@Slf4j
public class SyncDemo1 {
    private static volatile int count = 0;

    public static synchronized void increment() {
        count++;
    }

    public static synchronized void decrement() {
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
