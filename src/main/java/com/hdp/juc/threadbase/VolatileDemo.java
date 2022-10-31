package com.hdp.juc.threadbase;

/**
 * @author HDP
 * @ClassName: VolatileDemo
 * @Description:
 * @date 2022/10/31 15:01
 */
public class VolatileDemo {
    public static volatile boolean flag = true;
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                if (flag) {
                    System.out.println("trun on");
                    flag = false;
                }
            }
        }).start();
        new Thread((() -> {
            while (true) {
                if (!flag) {
                    System.out.println("trun off");
                    flag = true;
                }
            }
        })).start();
        //Java程序的内存可见性保证可以分为下列3类：
        //单线程程序。 单线程程序不会出现内存可见性问题。编译器、 runtime和处理器会共同确保单线程程序的执行结果与该程序在顺序一致性模型中的执行结果相同。
        //正确同步的多线程程序。正确同步的多线程程序的执行将具有顺序一致性（程序的 执行结果与该程序在顺序一致性内存模型中的执行结果相同） 。
        //  这是JMM关注的重点，JMM通过限制编译器和处理器的重排序来为程序员提供内存可见性保证。
        //未同步/未正确同步的多线程程序。 JMM为它们提供了最小安全性保障：线程执行时读取到的值，要么是之前某个线程写入的值，
        //  要么是默认值未同步程序在JMM中的执行时，整体上是无序的，其执行结果无法预知。JMM不保证未同步程序的执行结果与该程序在顺序一致性模型中的执行结果一致。
    }
}
