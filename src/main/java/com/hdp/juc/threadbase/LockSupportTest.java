package com.hdp.juc.threadbase;

import java.util.concurrent.locks.LockSupport;

/**
 * @author HDP
 * @ClassName: LockSupportTest
 * @Description:
 * @date 2022/10/31 15:37
 */
public class LockSupportTest {
    static class ParkThread implements Runnable {
        @Override
        public void run() {
            System.out.println("ParkThread开始执行");
            //等待许可
            LockSupport.park();
            System.out.println("ParkThread执行完成");
        }
    }
    public static void main(String[] args) {
        Thread thread = new Thread(new ParkThread());
        thread.start();
        System.out.println("唤醒ParkThread");
        //为指定线程thread提供许可
        LockSupport.unpark(thread);
    }
}
