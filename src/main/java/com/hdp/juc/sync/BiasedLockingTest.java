package com.hdp.juc.sync;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @author HDP
 * @ClassName: BiasedLockingTest
 * @Description:偏向锁批量重偏向测试
 * 无锁 001
 * 偏向锁 101
 * 轻量级锁 00
 * 重量级锁 10
 * @date 2022/11/10 14:43
 */
@Slf4j
public class BiasedLockingTest {
    public static void main(String[] args) throws InterruptedException {
        //延时产生可偏向对象
        Thread.sleep(5000);
        // 创建一个list，来存放锁对象
        List<Object> list = new ArrayList<>();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                //新建锁对象
                Object lock = new Object();
                synchronized (lock) {
                    list.add(lock);
                }
            }
            try {
                //为了防止JVM线程复用，在创建完对象后，保持线程thead1状态为存活
                Thread.sleep(100000);
            } catch (
                    InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread1").start();
        //睡眠3s钟保证线程thead1创建对象完成
        Thread.sleep(3000);

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                val obj = list.get(i);
                synchronized (obj) {
                    if (i >= 15 && i < 21 || i > 38) {
                        log.debug("thread2-第" + (i + 1) + "次加锁执行中\t" + ClassLayout.parseInstance(obj).toPrintable());
                    }
                }
                if (i == 17 || i == 19) {
                    log.debug("thread2-第" + (i + 1) + "次释放锁\t" + ClassLayout.parseInstance(obj).toPrintable());
                }
            }
            try {
                Thread.sleep(100000);
            } catch (
                    InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2").start();
        Thread.sleep(3000);

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                Object lock = list.get(i);
                if (i >= 17 && i <= 21 || i >= 35 && i <= 41) {
                    log.debug(Thread.currentThread().getName() + "-第" + (i + 1) + "准备加锁\t" + ClassLayout.parseInstance(lock).toPrintable());
                }
                synchronized (lock) {
                    if (i >= 17 && i <= 21 || i >= 35 && i <= 41) {
                        log.debug(Thread.currentThread().getName() + "-第" + (i + 1) + "加锁执行中\t" + ClassLayout.parseInstance(lock).toPrintable());
                    }
                }
            }
        }, "thread6").start();
        Thread.sleep(3000);
        log.debug("查看新创建对象");
        log.debug((ClassLayout.parseInstance(new Object()).toPrintable()));
        LockSupport.park();
    }
}
