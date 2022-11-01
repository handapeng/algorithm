package com.hdp.juc.lock;

import com.hdp.juc.factory.UnsafeFactory;
import sun.misc.Unsafe;

/**
 * @author HDP
 * @ClassName: CASLock
 * @Description:
 * @date 2022/11/1 11:48
 */
public class CASLock {
    //加锁标记
    private volatile int state;
    private static final Unsafe UNSAFE;
    private static final long OFFSET;
    static {
        try {
            UNSAFE = UnsafeFactory.getUnsafe();
            OFFSET = UnsafeFactory.getFieldOffset(UNSAFE, CASLock.class, "state");
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public boolean cas() {
        return UNSAFE.compareAndSwapInt(this, OFFSET, 0, 1);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
