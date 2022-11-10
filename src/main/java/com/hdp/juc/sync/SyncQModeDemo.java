package com.hdp.juc.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @author HDP
 * @ClassName: SyncQModeDemo
 * @Description:根据QMode的不同，会执行不同的唤醒策略
 * @date 2022/11/10 14:23
 */
@Slf4j
public class SyncQModeDemo {
    public static void main(String[] args) throws InterruptedException {
        SyncQModeDemo demo = new SyncQModeDemo();
        demo.startThreadA();
        Thread.sleep(100);
        demo.startThreadB();
        Thread.sleep(100);
        demo.startThreadC();
    }

    final Object lock = new Object();

    public void startThreadA() {
        new Thread(() -> {
            synchronized (lock) {
                log.debug("A get lock");
                try {
                    Thread.sleep(300);
                    lock.wait(300);
                } catch (
                        InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("A release lock");
            }
        },"thread-A").start();
    }
    public void startThreadB() {
        new Thread(() -> {
            synchronized (lock) {
                log.debug("B get lock");
                try {
                    Thread.sleep(300);
                } catch (
                        InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("B release lock");
            }
        },"thread-B").start();
    }
    public void startThreadC() {
        new Thread(() -> {
            synchronized (lock) {
                log.debug("C get lock");
            }
        }, "thread-C").start();
    }
}
