package com.hdp.juc.sync;

/**
 * @author HDP
 * @ClassName: LockEliminationTest
 * @Description:
 * @date 2022/11/10 15:09
 */
public class LockEliminationTest {
    StringBuffer buffer = new StringBuffer();

    /**
     * 锁粗化
     */
    public void append() {
        buffer.append("aaa").append("bbb").append("66");
    }


    /**
     * 锁消除
     * -XX:+EliminateLocks 开启锁消除(jdk8默认开启）
     * -XX:-EliminateLocks 关闭锁消除
     * @param str1
     * @param str2
     */
    public void append(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str).append(str2);
    }
    public static void main(String[] args) {
        LockEliminationTest test = new LockEliminationTest();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            test.append("aaa","bbb");
        }
        long end = System.currentTimeMillis();
        System.out.println("执行时间：" + (end - start) + " ms");
    }

}
