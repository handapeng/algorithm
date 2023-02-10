package com.hdp.algorithm.hw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author HDP
 * @ClassName: Friend
 * @Description:找到好朋友
 * N 个小朋友站成一队，第 i 个小朋友的身高为 height[i]，第 i 个小朋友可以
 * 看到第一个比自己身高更高的小朋友 j
 * 那么 j 是 i 的好朋友 (要求：j>i) 。
 * 请重新生成一个列表，对应位置的输出是每个小朋友的好朋友的位置。如果没有
 * 看到好朋友，请在该位置用 0 代替。
 * 第一行输入 N，N 表示有 N 个小朋友
 * 第二行输入 N 个小朋友的身高 height[i]，都是整数
 * 输出 N 个小朋友的好朋友的位置
 * 8
 * 123 124 125 121 119 122 126 123
 * 1 2 6 5 5 6 0 0
 * 123 的好朋友是 1 位置上的 124 ，124 的好朋友是 2 位置上的 125，125 的好朋
 * 友是 6 位置上的 126，依此类推
 * @date 2023/2/9 18:27
 */
public class Friend {
    public static void main(String[] args) {
        //i height[i] 100 95
        /*2
        100 95*/
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = sc.nextInt();
        }
        List<Integer> list = new ArrayList<>();
        int[] ints = Arrays.copyOf(arr, num);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < ints.length ; j++) {
                //遍历判断前面的值是否比后面的值大
                if(arr[i]<arr[j]){
                    list.add(j);
                    break;
                }//遍历完成后，如果找不到前面的值比后面的大，则赋值为0
                if(j==ints.length-1){
                    list.add(0);
                }
            }
        }
        for (int a:list) {
            System.out.print(a+" ");
        }

    }
}
