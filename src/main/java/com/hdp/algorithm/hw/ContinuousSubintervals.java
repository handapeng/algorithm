package com.hdp.algorithm.hw;

import java.util.Scanner;

/**
 * @author HDP
 * @ClassName: continuousSubintervals
 * @Description:连续子区间和
 * 一串含有 c 个正整数的数组，求出有多少个下标的连续区间，它们的和大于等于
 * x
 * 第一行两个整数 c x
 * 第二行有 c 个正整数
 * 输出一个整数，表示所求的个数
 * 3 6
 * 2 4 7
 * 输出：4
 * 对于有 3 个整数构成的数组而言，总共有 6 个下标连续的区间，他们的和分别
 * 为：
 * 2 = 2
 * 4 = 4
 * 7 = 7
 * 2 + 4 = 6
 * 4 + 7 = 11
 * 2 + 4 + 7 = 13
 * 其中有 4 个和大于等于 6，所以答案等于 4
 * @date 2023/2/9 18:04
 */
public class ContinuousSubintervals {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int x = s.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = s.nextInt();
        }

        int left = 0;
        int right = 0;
        long count = 0;
        int sum = 0;
        while (right < n) {
            sum += nums[right];

            while (sum >= x) {
                if (sum >= x) {
                    count += n - right;
                }

                sum -= nums[left];
                left += 1;
            }

            right += 1;
        }

        System.out.println(count);
    }
}
