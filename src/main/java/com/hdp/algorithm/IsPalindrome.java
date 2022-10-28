package com.hdp.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * @author HDP
 * @ClassName: IsPalindrome
 * @Description:
 * @date 2022/10/28 15:26
 */
public class IsPalindrome {
    public boolean isPalindrome(HasCycle.ListNode head) {
        Set<Integer> set = new HashSet<>();
        if(head.next==null){
            return true;
        }
        while (head != null) {
            if (!set.add(head.val)&&set.size()>2&&head.next!=null) {
                return true;
            }
            head = head.next;
        }
        return false;
    }
}
