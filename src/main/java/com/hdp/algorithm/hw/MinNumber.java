package com.hdp.algorithm.hw;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author HDP
 * @ClassName: MinNumber
 * @Description:最小数字
 * 给定一个整型数组，请从该数组中选择 3 个元素组成最小数字并输出（如果数组
 * 长度小于 3，则选择数组中所有元素来组成最小数字）
 * 21,30,62,5,31
 * 21305
 * 5,21
 * 215
 * @date 2023/2/9 18:25
 */
public class MinNumber {
    public static void main(String[] args) {

        // 给定一个int数组 请从改数组中选择3个最小数字并输出
        // 输入 21,30,62,5,31
        // 输出 21305
        // 5,21
        // 215
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] split = str.split(",");
        int[] newArr = null;
        if (split.length < 3) {
            newArr = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                newArr[i] = Integer.parseInt(split[i]);
            }
        } else {
            int[] intArr = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                intArr[i] = Integer.parseInt(split[i]);
            }
            Arrays.sort(intArr);
            newArr = Arrays.copyOf(intArr, 3);
        }

        // 新数组最小组合
        String minCombine = printMinNumber(newArr);
        System.out.println(minCombine);
    }

    private static String printMinNumber(int[] newArr) {
        if (newArr == null || newArr.length == 0) {
            return "";
        }
        int length = newArr.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = String.valueOf(newArr[i]);
        }
        StringBuilder sb = new StringBuilder();
        Arrays.sort(strArr, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String c1 = o1 + o2;
                String c2 = o2 + o1;
                return c1.compareTo(c2);
            }
        });

        for (int i = 0; i < length; i++) {
            sb.append(strArr[i]);
        }
        return sb.toString();
    }
}
