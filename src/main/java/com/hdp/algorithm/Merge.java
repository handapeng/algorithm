package com.hdp.algorithm;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author HDP
 * @ClassName: Merge
 * @Description:
 * @date 2022/10/10 10:32
 */
public class Merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0,j=0; i < m + n; i++) {
            if (nums1[i] == 0&&j<n) {
                nums1[i] = nums2[j];
                j++;
            }
        }
        Arrays.sort(nums1);
    }
}
