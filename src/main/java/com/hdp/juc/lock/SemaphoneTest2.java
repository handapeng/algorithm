package com.hdp.juc.lock;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author HDP
 * @ClassName: SemaphoneTest2
 * @Description:
 * @date 2022/11/14 10:26
 */
public class SemaphoneTest2 {
    /**
     * 实现一个同时只能处理5个请求的限流器
     */
    private static Semaphore semaphore = new Semaphore(5);

    /**
     * 定义一个线程池
     */
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor
            (10, 50, 60, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(200));

    /**
     * 模拟执行方法
     */
    public static void exec() {
        try {
            //占用一个资源
            semaphore.acquire(1);
            System.out.println("执行exec方法");
            Thread.sleep(2000);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }finally {
            /**
             * 释放一个资源
             */
            semaphore.release(1);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i=0; i<10; i++) {
            Thread.sleep(100);
            //模拟请求以10个/s 的速度
            executor.execute(SemaphoneTest2::exec);
        }
    }
}
