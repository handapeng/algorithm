package com.hdp.juc.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author HDP
 * @ClassName: LongAdderTest
 * @Description:
 * @date 2022/11/3 11:48
 */
public class LongAdderTest {
    public static void main(String[] args) {
     testAtomicLongVSLongAdder(10,10000);
     testAtomicLongVSLongAdder(10,200000);
     testAtomicLongVSLongAdder(100,200000);
    }
    static void testAtomicLongVSLongAdder(final int threadCount, final int timeslot) {
        try {
            long start = System.currentTimeMillis();
            testLongAdder(threadCount, timeslot);
            long end = System.currentTimeMillis() - start;
            System.out.println("条件>>>>>>线程数:" + threadCount + ", 单线程操作计数" + timeslot);
            System.out.println("结果>>>>>>LongAdder方式增加计数" + (threadCount * timeslot) + "次,共计耗时:" + end);
            long start1 = System.currentTimeMillis();
            testAtomicLong(threadCount, timeslot);
            long end1 = System.currentTimeMillis() - start1;
            System.out.println("条件>>>>>>线程数:" + threadCount + ", 单线程操作计数" + timeslot);
            System.out.println("结果>>>>>>AtomicLong方式增加计数" + (threadCount * timeslot) + "次,共计耗时:" + end1);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void testAtomicLong(final int threadCount, final int times) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        AtomicLong atomicLong = new AtomicLong();
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    atomicLong.incrementAndGet();
                }
                countDownLatch.countDown();
            }, "my-thread" + i).start();
        }
        countDownLatch.await();
    }

    static void testLongAdder(final int threadCount, final int times) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        LongAdder longAdder = new LongAdder();
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                for (int j = 0; j < times; j++) {
                    longAdder.add(1);
                }
                countDownLatch.countDown();
            }, "my-thread" + i).start();
        }
        countDownLatch.await();
    }
}
