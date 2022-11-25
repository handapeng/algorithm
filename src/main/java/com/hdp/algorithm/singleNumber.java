package com.hdp.algorithm;

/**
 * @author HDP
 * @ClassName: singleNumber
 * @Description:
 * 可使用异或运算⊕。异或运算有以下三个性质。
 * 任何数和 0 做异或运算，结果仍然是原来的数，即a⊕0=a。
 * 任何数和其自身做异或运算，结果是0，即 a⊕a=0。
 * 异或运算满足交换律和结合律，即 a⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b。
 * @date 2022/11/25 10:39
 */
public class singleNumber {
    public static int singleNumber(int[] nums) {
        int x = 0;
        for (int num : nums) {
            x ^= num;
        }
        return x;
    }
    public static void main(String[] args) throws InterruptedException {
        int[] nums = new int[]{1,1,2};
        System.out.println(singleNumber(nums));
    }
}
