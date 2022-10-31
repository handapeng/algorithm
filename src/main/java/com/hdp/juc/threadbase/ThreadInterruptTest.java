package com.hdp.juc.threadbase;

/**
 * @author HDP
 * @ClassName: ThreadInterruptTest
 * @Description:中断机制
 * @date 2022/10/31 14:10
 */
public class ThreadInterruptTest {
    static int i = 0;
    public static void main(String[] args) {
        System.out.println("begin");
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    i++;
                    System.out.println(i);
//                    try {
//                        //清除中断标志位
//                        Thread.sleep(10000);
//                    } catch (
//                            InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    //Thread.interrupted() 清除中断标志位
//                    Thread.currentThread().isInterrupted() 不会清除终端标志位
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("======");
                    }
                    if (i == 10) {
                        break;
                    }
                }
            }
        });
        //interrupt():将中断标志位置位true，不会暂停线程
        //isInterrupted()：判断当前线程中断标志位是否为true，不会清除中断标志位
        //interrupted():判断当前线程中断标志位是否位true，会清除中断标志位，重置位false
        thread.start();
        thread.interrupt();
    }
}
