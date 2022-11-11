package com.hdp.juc.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author HDP
 * @ClassName: ReentrantLockDemo2
 * @Description:可重入
 * @date 2022/11/11 9:43
 */
@Slf4j
public class ReentrantLockDemo2 {
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        method1();
    }
    public static void method1() {
        lock.lock();
        try {
            log.debug("execute method1");
            method2();
        }finally {
            lock.unlock();
        }
    }
    public static void method2() {
        lock.lock();
        try {
            log.debug("execute method2");
            method3();
        }finally {
            lock.unlock();
        }
    }
    public static void method3() {
        lock.lock();
        try {
            log.debug("execute method3");
        }finally {
            lock.unlock();
        }
    }
}
