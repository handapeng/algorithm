package com.hdp.algorithm.hw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author HDP
 * @ClassName: Dereordering
 * @Description:
 * 给定一个乱序的数组，删除所有的重复元素，使得每个元素只出现一次，并且按
 * 照出现的次数从高到低进行排序，
 * 相同出现次数按照第一次出现顺序进行先后排序。
 * 1 3 3 3 2 4 4 4 5
 * 3 4 1 2 5
 * @date 2023/2/9 18:06
 */
public class Dereordering {

    public static void main(String[] args) {
        int[] arr = {};
        List list = sortRemovle(arr);
        System.out.println(list.toString());
    }

    public static List sortRemovle(int[] arr) {
        // 将数组顺序存放到LinkedHashMap中，并将每个元素进行计数
        Map<Integer, Integer> linkedHashMap = new LinkedHashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (linkedHashMap.get(arr[i]) != null) {
                linkedHashMap.put(arr[i], linkedHashMap.get(arr[i]) + 1);
            } else {
                linkedHashMap.put(arr[i], 1);
            }
        }
        //将linkedHashMap 通过value值进行排序操作 从高到低排序
        List<Map.Entry<Integer, Integer>> list = new ArrayList(linkedHashMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        //获取排序后的数据
        List value = new ArrayList();
        for (Map.Entry s : list) {
            value.add(s.getKey());
        }
        return value;
    }


}
