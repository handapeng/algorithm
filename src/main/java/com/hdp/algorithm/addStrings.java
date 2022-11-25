package com.hdp.algorithm;

/**
 * @author HDP
 * @ClassName: addStrings
 * @Description:
 * 我们定义两个指针 i 和 j 分别指向 num1和 num2的末尾，即最低位，同时定义一个变量 add 维护当前是否有进位，然后从末尾到开头逐位相加即可。
 * 你可能会想两个数字位数不同怎么处理，这里我们统一在指针当前下标处于负数的时候返回 0，等价于对位数较短的数字进行了补零操作，这样就可以除去两个数字位数不同情况的处理
 *
 * @date 2022/11/25 11:28
 */
public class addStrings {
    class Solution {
        public String addStrings(String num1, String num2) {
            int i = num1.length() - 1, j = num2.length() - 1, add = 0;
            StringBuffer ans = new StringBuffer();
            while (i >= 0 || j >= 0 || add != 0) {
                int x = i >= 0 ? num1.charAt(i) - '0' : 0;
                int y = j >= 0 ? num2.charAt(j) - '0' : 0;
                int result = x + y + add;
                ans.append(result % 10);
                add = result / 10;
                i--;
                j--;
            }
            // 计算完以后的答案需要翻转过来
            ans.reverse();
            return ans.toString();
        }
    }

}
