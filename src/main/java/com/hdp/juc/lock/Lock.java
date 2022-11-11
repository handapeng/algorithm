package com.hdp.juc.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author HDP
 * @ClassName: Lock
 * @Description:
 * @date 2022/11/11 10:53
 */
public class Lock extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(int unused) {
        if (compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

}
