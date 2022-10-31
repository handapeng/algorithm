package com.hdp.juc.threadbase;

/**
 * @author HDP
 * @ClassName: SellTicketDemo
 * @Description:
 * @date 2022/10/31 16:03
 */
public class SellTicketDemo implements Runnable {
    private int ticket;

    public SellTicketDemo() {
        this.ticket = 100;
    }
    @Override
    public void run() {
        while (ticket > 0) {
            synchronized (this) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(20);
                    } catch (
                            InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":正在执行操作，余票："+ticket--);
                }
            }
            Thread.yield();
        }
    }
    public static void main(String[] args) {
        SellTicketDemo demo = new SellTicketDemo();
        Thread thread = new Thread(demo,"thread");
        Thread thread1 = new Thread(demo,"thread1");
        Thread thread2 = new Thread(demo, "thread2");
        //priority优先级默认是5，最低1，最高10
        thread.setPriority(Thread.MIN_PRIORITY);
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread.start();
        thread1.start();
        thread2.start();
    }
}
