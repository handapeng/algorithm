package com.hdp.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author HDP
 * @ClassName: ClimbStairs
 * @Description:
 * @date 2022/9/25 21:37
 */
public class ClimbStairs {
    //f(n)= 1           当n=1
    //      2           当n=2
    //      f(n-1)+f(n-2)        当n>=3
    private static Map<Integer, Integer> map = new HashMap<>();
    public static int count(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (Objects.nonNull(map.get(n))) {
            return map.get(n);
        } else {
            int anInt = count(n - 1) + count(n - 2);
            map.put(n, anInt);
            return anInt;
        }
    }
    public static void main(String[] args) {
        System.out.println(count(45));
    }
}
