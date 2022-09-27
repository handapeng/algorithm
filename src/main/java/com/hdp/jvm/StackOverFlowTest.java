package com.hdp.jvm;

/**
 * @author HDP
 * @ClassName: StackOverFlow
 * @Description: 栈溢出
 * @date 2022/9/27 22:31
 */
// jvm设置 -Xss128k(默认1M)  设置的是栈里的每个线程的栈大小
public class StackOverFlowTest {
    static int count = 0;

    static void redo() {
        count++;
        redo();
    }

    public static void main(String[] args) {
        try {
            redo();
        } catch (
                Throwable t) {
            t.printStackTrace();
            System.out.println(count);
        }
    }
}
