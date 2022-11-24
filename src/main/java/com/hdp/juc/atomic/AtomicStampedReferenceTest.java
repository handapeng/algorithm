package com.hdp.juc.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @author HDP
 * @ClassName: AtomicStampedReferenceTest
 * @Description:
 * @date 2022/11/4 11:32
 */
@Slf4j
public class AtomicStampedReferenceTest {
    public static void main(String[] args) {
        // 定义AtomicStampedReference    Pair.reference值为2, Pair.stamp为1
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(2, 1);

        new Thread(() -> {
            // stampHolder一个大小至少为 1 的数组。返回时， stampholder[0]将保存邮票stamp的价值。
            int[] stampHolder = new int[1];
            int value = (int) atomicStampedReference.get(stampHolder);
            int stamp = stampHolder[0];
            log.debug("Thread1 read value:" + value + ",stamp:" + stamp);
            // 阻塞1s
            LockSupport.parkNanos(1000000000L);
            // Thread1通过CAS修改value值为3   stamp是版本，每次修改可以通过+1保证版本唯一性
            if (atomicStampedReference.compareAndSet(value, 3, stamp, stamp + 1)) {
                log.debug("Thread1 update from " + value + " to 3");
            } else {
                log.debug("Thread1 update fail!");
            }
        },"Thread1").start();
        new Thread(() -> {
            int[] stampHolder = new int[1];
            int value = (int) atomicStampedReference.get(stampHolder);
            int stamp = stampHolder[0];
            log.debug("Thread2 read value:" + value + ",stamp:" + stamp);
            // Thread2通过CAS修改value值为2
            if (atomicStampedReference.compareAndSet(value, 2, stamp, stamp + 1)) {
                log.debug("Thread2 update from " + value + " to 2");
                value = (int) atomicStampedReference.get(stampHolder);
                stamp = stampHolder[0];
                log.debug("Thread2 read value: " + value + ", stamp: " + stamp);
                // Thread2通过CAS修改value值为1
                if (atomicStampedReference.compareAndSet(value, 1, stamp, stamp + 1)) {
                    log.debug("Thread2 update from " + value + " to 1");
                }
            }
        },"Thread2").start();
    }
}