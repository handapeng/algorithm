package com.hdp.juc.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @author HDP
 * @ClassName: SyncWaitTest
 * @Description:
 * @date 2022/11/4 14:54
 */
@Slf4j
public class SyncWaitTest {
    private Object lock = new Object();

    public void test() {
        log.debug(Thread.currentThread().getName() + "start");
        synchronized (lock) {
            log.debug(Thread.currentThread().getName() + "execute");
            try {
                lock.wait(5000);
            } catch (
                    InterruptedException e) {
                e.printStackTrace();
            }
            log.debug(Thread.currentThread().getName()+"end");
        }
    }
    public static void main(String[] args) {
        SyncWaitTest test = new SyncWaitTest();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                test.test();
            },"thread"+i).start();
        }
    }
}
