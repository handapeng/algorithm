package com.hdp.juc.factory;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author HDP
 * @ClassName: UnsafeFactory
 * @Description:
 * @date 2022/10/25 14:45
 */
public class UnsafeFactory {
    /**
     * 获取Unsafe对象
     * @return
     */
    public static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (
                Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取字段的内存偏移量
     * @param unsafe
     * @param clazz
     * @param fieldName
     * @return
     */
    public static long getFieldOffset(Unsafe unsafe, Class clazz, String fieldName) {
        try {
            return unsafe.objectFieldOffset(clazz.getDeclaredField(fieldName));
        } catch (NoSuchFieldException e) {
            throw new Error(e);
        }
    }

}
