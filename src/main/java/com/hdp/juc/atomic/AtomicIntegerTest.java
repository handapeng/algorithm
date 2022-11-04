package com.hdp.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author HDP
 * @ClassName: AtomicIntegerTest
 * @Description:
 * @date 2022/11/4 11:03
 */
public class AtomicIntegerTest {
    static AtomicInteger sum = new AtomicInteger(0);
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    sum.incrementAndGet();
                }
            });
            thread.start();
        }
        try {
            Thread.sleep(3000);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sum.get());
    }
}
