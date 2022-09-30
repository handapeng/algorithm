package com.hdp.algorithm;

import java.util.HashMap;

/**
 * @author HDP
 * @ClassName: TwoSum
 * @Description:
 * @date 2022/9/30 9:14
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] a =new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    a[0] = i;
                    a[1] = j;
                }
            }
        }
        return a;



//        HashMap<Integer, Integer> map = new HashMap();
//        for (int i = 0; i < nums.length; i++) {
//            if (map.containsKey(target - nums[i])) {
//                return new int[]{map.get(target - nums[i]),i};
//            }
//            map.put(nums[i], i);
//        }
//        return new int[2];
    }

}
