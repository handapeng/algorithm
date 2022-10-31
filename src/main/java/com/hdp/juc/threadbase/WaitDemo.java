package com.hdp.juc.threadbase;

/**
 * @author HDP
 * @ClassName: WaitDemo
 * @Description:
 * @date 2022/10/31 15:01
 */
public class WaitDemo {
    private static Object lock = new Object();
    private static boolean flag = true;
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                while (flag) {
                    System.out.println("wait start ......");
                    try {
                        //只能放在synchronized代码块里
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("wait end ......");
                }
            }
        }).start();
        new Thread(() -> {
            if (flag) {
                synchronized (lock) {
                    if (flag) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        lock.notifyAll();
                        System.out.println("notify ......");
                        flag = false;
                    }
                }
            }
        }).start();
    }
}
