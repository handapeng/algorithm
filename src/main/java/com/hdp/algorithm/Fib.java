package com.hdp.algorithm;

/**
 * @author HDP
 * @ClassName: fib
 * @Description:
 * @date 2022/9/27 10:48
 */
public class Fib {

    public static int fib(int n) {
        if (n < 2) {
            return n;
        }
        int a=0, b=0, c = 1;
        for (int i = 2; i <= n; i++) {
            a = b;
            b = c;
            c = a + b;
        }
        return c;
    }
    public static void main(String[] args) {
        System.out.println(fib(30));
    }
}
