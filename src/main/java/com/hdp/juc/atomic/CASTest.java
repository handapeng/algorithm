package com.hdp.juc.atomic;

import com.hdp.juc.factory.UnsafeFactory;
import sun.misc.Unsafe;

/**
 * @author HDP
 * @ClassName: CASTest
 * @Description:
 * @date 2022/11/1 15:13
 */
public class CASTest {
    public static void main(String[] args) {
        Entity entity = new Entity();
        Unsafe unsafe = UnsafeFactory.getUnsafe();
        long offset = UnsafeFactory.getFieldOffset(unsafe, Entity.class, "x");
        System.out.println(offset);
        boolean successfull;

        // 4个参数分别是：对象实例、字段的内存偏移量、字段期望值、字段更新值
        successfull = unsafe.compareAndSwapInt(entity, offset, 0, 2);
        System.out.println(successfull + "\t" + entity.x);
        successfull = unsafe.compareAndSwapInt(entity, offset, 2, 1);
        System.out.println(successfull + "\t" + entity.x);
        successfull = unsafe.compareAndSwapInt(entity, offset, 2, 1);
        System.out.println(successfull + "\t" + entity.x);

    }
}
class Entity{
    //偏移量16 对象头的Mark Word8个字节(64bit=8个字节)，加上long 8个字节
    public long x;
    public int y;
}
