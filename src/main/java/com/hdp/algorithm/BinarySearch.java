package com.hdp.algorithm;

/**
 * @author HDP
 * @ClassName: BinarySearch
 * @Description:
 * @date 2023/2/8 14:35
 */
public class BinarySearch {
   static int binarySearch(int[] nums,int target,int low,int high){

        while(low<=high){

            int middle = low+(high-low)/2;

            if(nums[middle]==target){
                return middle;
            }

            if(target<nums[middle]){
                high=middle-1;
            }else{
                low=middle+1;
            }

        }
        return -1;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3, 5, 6, 6};

        System.out.println(binarySearch(ints, 5, 0, 6));
    }

}
