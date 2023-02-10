package com.hdp.algorithm.hw;

import java.util.TreeSet;

/**
 * @author HDP
 * @ClassName: Final
 * @Description:一个正整数数组设为 nums, 最大为 100 个成员。求从第一个成员开始正好走到数
 * 组最后一个成员所使用的最小步骤数
 * 要求：1.第一步必须从第一元素起，且 1<=第一步步长<len/2 (len 为数组长
 * 度)
 * 2.从第二步开始只能以所在成员的数字走相应的步数，不能多不能少，如果目标
 * 不可达返回-1，只输出最小的步骤数量
 * 3.只能向数组的尾部走不能向回走
 * 输入描述：有正整数数组，空格分割
 * 输出描述 ：最小步数，不存在输出-1
 * 7 5 9 4 2 6 8 3 5 4 3 9
 * 2
 * 1 2 3 7 1 5 9 3 2 1
 * -1
 * @date 2023/2/9 18:15
 */
public class Final {

    public static void main(String[] args) {
        Final solution3 = new Final();
        int[] ints = new int[]{7, 5, 9, 4, 2, 6, 8, 3, 5, 4, 3, 9};
        TreeSet treeSet = solution3.run(ints);
        //若treeSet无数据，则证明目标不可达
        if (treeSet.isEmpty())
            System.out.println(-1);
        else
            System.out.println(treeSet.iterator().next());
    }

    public TreeSet run(int[] ints) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        int len = ints.length / 2;
        //runLength可理解为：从1遍历到当前位置的数组长度
        for (int stepIndex = 1, runLength = stepIndex; stepIndex < len; stepIndex++) {
            int count = 1;
            //赋予首次步长值
            int step = stepIndex;
            while (true) {
                //将取到的元素值 更新为新的步长值
                step = ints[step];
                //以step步长运动后，更新当前数组位置
                runLength = runLength + step;
                //记录运动次数
                count++;
                //将能到达原数组尾部的运动次数写入treeSet
                //循环结束后，依据treeSet的自排序特性，其首位即最小运动次数
                if (runLength >= ints.length) {
                    treeSet.add(count);
                    break;
                }
            }
        }
        return treeSet;
    }

}
