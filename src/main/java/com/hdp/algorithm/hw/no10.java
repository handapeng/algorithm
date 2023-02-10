package com.hdp.algorithm.hw;

import java.util.Scanner;

/**
 * @author HDP
 * @ClassName: no19
 * @Description:求解连续数列
 * 已知连续正整数数列 {K}=K1,K2,K3…Ki 的各个数相加之和为 S ， i=N
 * (0<S<100000, 0<N<100000), 求此数列 K
 * 输入包含两个参数: 1）连续正整数数列和 S 2）数列里数的个数 N
 * 如果有解输出数列 K，如果无解输出-1
 * 525 6
 * 85 86 87 88 89 90
 * 3 5
 * -1
 * @date 2023/2/10 10:14
 */
public class no10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int tar = sc.nextInt();
            int len = sc.nextInt();
            int[] arr = new int[len];
            arr[len - 1] = tar / len;
            for (int i = len - 2; i >= 0; i--) {
                arr[i] = arr[i + 1] - 1;
            }
            int[] ans = fun(arr, tar);
            if (ans != null) {
                for (int i : ans) {
                    System.out.print(i+" ");
                }
            } else {
                System.out.println(-1);
            }

        }
    }

    static int[] fun(int[] arr, int tar) {
        int sum = 0;
        while(sum < tar) {
            for (int i = 0; i < arr.length; i++) {
                arr[i]++;
            }
            sum=0;
            for (int i : arr) {
                sum += i;
            }
            if (sum == tar) {
                return arr;
            }
        }
        return null;
    }
}
