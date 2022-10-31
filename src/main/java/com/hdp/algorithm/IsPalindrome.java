package com.hdp.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author HDP
 * @ClassName: IsPalindrome
 * @Description: 回文链表
 * @date 2022/10/28 15:26
 */
public class IsPalindrome {
    public boolean isPalindrome(HasCycle.ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            if (!list.get(i).equals(list.get(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
