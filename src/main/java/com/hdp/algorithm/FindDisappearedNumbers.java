package com.hdp.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HDP
 * @ClassName: FindDisappearedNumbers
 * @Description:
 * @date 2022/10/10 11:24
 */
public class FindDisappearedNumbers {
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1});

        System.out.println((5 - 1) % 8);
    }
}
