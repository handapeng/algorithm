package com.hdp.juc.sync;

import com.hdp.juc.factory.UnsafeFactory;
import sun.misc.Unsafe;

import java.nio.ByteOrder;

/**
 * @author HDP
 * @ClassName: EndianTest
 * @Description:
 *  判断当前环境字节是大端还是小端字节序存储
 *  Little-Endian 高位字节在前，低位字节在后。
 *  Big-Endian 低位字节在前，高位字节在后。
 *  在x86的计算机中，一般采用的是小端字节序
 *  比如十六进制数字0x12345678，它总共占4个字节（1个字节8位，2个16进制占8位，所以1个字节最大表示0xFF）。这个数据分别在大小端模式下的内存存储布局为：
 *
 *
 * 大端模式
 * 低地址 -----------------> 高地址
 * 0x12 | 0x34 | 0x56 | 0x78
 *
 * 小端模式
 * 低地址 ------------------> 高地址
 * 0x78 | 0x56 | 0x34 | 0x12
 * @date 2022/11/4 14:23
 */
public class EndianTest {
    public static void main(String[] args) {
        Unsafe unsafe = UnsafeFactory.getUnsafe();
        long a = unsafe.allocateMemory(8);
        try {
            unsafe.putLong(a, 0x0102030405060708L);
            byte b = unsafe.getByte(a);
            ByteOrder byteOrder;
            switch (b) {
                case 0x01:
                    byteOrder = ByteOrder.BIG_ENDIAN;
                    break;
                case 0x08:
                    byteOrder = ByteOrder.LITTLE_ENDIAN;
                    break;
                default:
                    byteOrder = null;
            }
            System.out.println(byteOrder);
        }finally {
            unsafe.freeMemory(a);
        }

    }
}
