package com.hdp.juc.threadactiveness;

import lombok.extern.slf4j.Slf4j;

/**
 * @author HDP
 * @ClassName: Philosopher
 * @Description:
 * @date 2022/11/16 14:37
 */
@Slf4j
public class Philosopher extends Thread {
    private Chopstick left;
    private Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        super(name);
        this.left = left;
        this.right = right;
    }

    public void eat() {
        log.debug("eating...");
        try {
            Thread.sleep(10);
        } catch (
                InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void think() {
        log.debug("thinking...");
        try {
            Thread.sleep(2000);
        } catch (
                InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized (left) {
                log.debug("获得左手筷子" + left.getNumber());
                synchronized (right) {
                    log.debug("获得右手筷子" + right.getNumber());
                    eat();
                }
            }
            log.debug("吃完了，把筷子放回了原处，开始thinking");
            think();
        }
    }
}
