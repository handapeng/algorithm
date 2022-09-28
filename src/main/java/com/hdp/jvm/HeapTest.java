package com.hdp.jvm;

import java.util.ArrayList;

/**
 * @author HDP
 * @ClassName: HeapTest
 * @Description:
 * @date 2022/9/27 22:16
 */
public class HeapTest {
    byte[] a = new byte[1024 * 100];
    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        while (true) {
            heapTests.add(new HeapTest());
            Thread. sleep(10);
        }
    }
}
