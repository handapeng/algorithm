package com.hdp.juc.lock;

import java.util.concurrent.Semaphore;

/**
 * @author HDP
 * @ClassName: SemaphoreTest
 * @Description: Semaphore是一个计数信号量, Semaphore经常用于限制获取资源的线程数量
 * @date 2022/11/14 10:18
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        //声明3个窗口 state 资源数
        Semaphore window = new Semaphore(3);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    //占用窗口，加锁
                    window.acquire();
                    System.out.println(Thread.currentThread().getName() + ":开始购票");
                    //模拟买票线程
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName()+":购票成功");
                } catch (
                        InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 释放窗口
                    window.release();
                }
            }).start();
        }
    }
}
