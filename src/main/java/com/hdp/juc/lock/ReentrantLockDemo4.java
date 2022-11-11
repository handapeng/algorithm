package com.hdp.juc.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author HDP
 * @ClassName: ReentrantLockDemo4
 * @Description:锁超时
 * @date 2022/11/11 9:56
 */
@Slf4j
public class ReentrantLockDemo4 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            //注意：即使是设置的公平锁，此方法也会立即返回获取锁成功或失败，公平策略不生效
//            if (!lock.tryLock()) {
//                log.debug("t1获取锁失败，返回");
//                return;
//            }

            log.debug("t1启动。。。");
            try {
                if (!lock.tryLock(1, TimeUnit.SECONDS)) {
                    log.debug("等待1s后获取锁失败，返回");
                    return;
                }
            } catch (
                    InterruptedException e) {
                e.printStackTrace();
                return;
            }
            try {
                log.debug("t1获得了锁");
            } finally {
                lock.unlock();
            }
        }, "t1");

        lock.lock();
        try {
            log.debug("main线程获得了锁");
            t1.start();
            //先让t1执行
            Thread.sleep(1000);
//            t1.interrupt();
//            log.debug("线程t1执行中断");
        } finally {
            lock.unlock();
        }
    }
}
