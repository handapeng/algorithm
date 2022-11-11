package com.hdp.juc.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author HDP
 * @ClassName: ReentrantLockDemo6
 * @Description:条件变量
 * @date 2022/11/9 22:09
 */
@Slf4j
public class ReentrantLockDemo6 {
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition cigCon = lock.newCondition();
    private static Condition takeCon = lock.newCondition();
    private static boolean hascig = false;
    private static boolean hastakeout = false;

    public void cigratee() {
        lock.lock();
        try {
            while (!hascig) {
                try {
                    log.debug("没有烟，歇一会");
                    cigCon.await();
                } catch (
                        InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            log.debug("有烟了，干活");
        }finally {
            lock.unlock();
        }
    }

    public void takeout() {
        lock.lock();
        try {
            while (!hastakeout) {
                try {
                    log.debug("没饭了，歇一会");
                    takeCon.await();
                } catch (
                        InterruptedException e) {
                    e.printStackTrace();
                }
            }
            log.debug("有饭了，干活");
        }finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        ReentrantLockDemo6 demo6 = new ReentrantLockDemo6();
        new Thread(() -> {
            demo6.cigratee();
        }).start();
        new Thread(() -> {
            demo6.takeout();
        }).start();
        new Thread(() -> {
            lock.lock();
            try {
                hascig = true;
                log.debug("唤醒烟的等待线程");
                cigCon.signal();
            } finally {
                lock.unlock();
            }
        }).start();
        new Thread(() -> {
            lock.lock();
            try {
                hastakeout = true;
                log.debug("唤醒饭的等待线程");
                takeCon.signal();
            }finally {
                lock.unlock();
            }
        }).start();
    }
}
