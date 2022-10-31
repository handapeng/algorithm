package com.hdp.juc.threadbase;

/**
 * @author HDP
 * @ClassName: ThreadJoinDemo
 * @Description:
 * @date 2022/10/31 15:59
 */
public class ThreadJoinDemo {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println("t begin");
            try {
                Thread.sleep(5000);
            } catch (
                    InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t finished");
        });
        long start = System.currentTimeMillis();
        thread.start();
        //主线程等待线程thread执行完成
        thread.join();
        System.out.println("执行时间：" + (System.currentTimeMillis() - start));
        System.out.println("Main finished");
    }
}
