package com.hdp.jvm;

/**
 * @author HDP
 * @ClassName: Math
 * @Description:
 * @date 2022/9/27 21:38
 */
public class Math {
    public static final int initData = 666;
    public static User user = new User();

    public int compute() { //一个方法对应栈中的一块栈帧内存区域
        // 生成可读的字节码文件 javap -c Math.class > Math.txt
        int a = 1;
        int b = 2;
        int c = (a + b) * 10;
        return c;
    }

    public static void main(String[] args) {
        Math math = new Math();
        System.out.println(math.compute());
    }

    static class User {

    }
}
