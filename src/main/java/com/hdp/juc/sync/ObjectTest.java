package com.hdp.juc.sync;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author HDP
 * @ClassName: ObjectTest
 * @Description:关闭指针压缩（-XX:-UseCompressedOops）
 * @date 2022/11/9 17:07
 */
public class ObjectTest {

    public static void main(String[] args) throws InterruptedException {
        //jvm延迟偏向
        Thread.sleep(5000);
        Object obj = new Test();
//        Object obj1 = new Integer[4];
//        obj1.hashCode();
        //查看对象内部信息
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + "\n" + ClassLayout.parseInstance(obj).toPrintable());
            }
            System.out.println(Thread.currentThread().getName() + "释放锁\n" + ClassLayout.parseInstance(obj).toPrintable());
            //jvm优化
            try {
                Thread.sleep(1000);
            } catch (
                    InterruptedException e) {
                e.printStackTrace();
            }
        }, "Thread1").start();

        Thread.sleep(2000);

        new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName()+"\n"+ClassLayout.parseInstance(obj).toPrintable());
            }
        },"Thread2");
    }


    static class Test {
        private boolean flag;
        private long p;
    }
}
