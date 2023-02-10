package com.hdp.algorithm.hw;

import java.util.Scanner;

/**
 * @author HDP
 * @ClassName: MinExchanges
 * @Description:最小交换次数
 * 整数数组 nums，整数 k。输出将数组中小于 k 的整数组合到一起的最小交换次数
 * 组合在一起是指满足条件的数字相邻，不要求相邻后在数组中的位置
 * 1 3 1 4 0 k=2
 * 1 解析：交换第一个 1 和 4
 * 0 0 0 1 0 k=2
 * 0 解析：已经满足条件
 * 2 3 2 k=1
 * 0 解析：不存在小于 k 的数，无需交换
 * @date 2023/2/9 18:30
 */
public class MinExchanges {
        public static void main(String[] args) {
            //首先把接收到的字符串转化为int数组
            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                String[] str = sc.nextLine().split("\\s");
                int target = sc.nextInt();
                int[] arr = new int[str.length];
                int totalLess = 0;
                for (int i = 0 ; i < arr.length ; i++) {
                    arr[i] = Integer.parseInt(str[i]);
                    if (arr[i] < target) {
                        totalLess++;
                    }
                }
                //有一个滑动窗口，窗口大小固定为小于target的数，然后但凡在窗口内不小于的，就是需要交换的
                int res = Integer.MAX_VALUE;
                for (int i = 0 ; i < arr.length + 1 - totalLess ; i++) {
                    int count = 0;
                    for (int j = 0 ; j < totalLess ; j++) {
                        if (arr[i + j] > target) {
                            count++;
                        }
                    }
                    res = Math.min(res, count);
                }
                System.out.println(res);
            }
        }

}
