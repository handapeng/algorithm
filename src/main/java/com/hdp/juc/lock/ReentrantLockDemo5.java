package com.hdp.juc.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author HDP
 * @ClassName: ReentrantLockDemo5
 * @Description:公平锁
 * @date 2022/11/11 10:07
 */
@Slf4j
public class ReentrantLockDemo5 {
    public static void main(String[] args) throws InterruptedException {
//        ReentrantLock lock = new ReentrantLock(true);//公平锁
        ReentrantLock lock = new ReentrantLock();//非公平锁
        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    try {
                        Thread.sleep(10);
                    } catch (
                            InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    log.debug(Thread.currentThread().getName() + "running...");
                } finally {
                    lock.unlock();
                }
            }, "t" + i).start();
        }
        //1s后去争抢锁
        Thread.sleep(1000);
        for (int i = 0; i < 500; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    log.debug(Thread.currentThread().getName() + "runing...");
                } finally {
                    lock.unlock();
                }
            }, "强行插入" + i).start();
        }
    }
}
