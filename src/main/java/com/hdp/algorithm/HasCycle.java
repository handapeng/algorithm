package com.hdp.algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author HDP
 * @ClassName: HasCycle
 * @Description:环形链表
 * @date 2022/10/19 9:56
 */
public class HasCycle {
    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
    public boolean hasCycle(ListNode head) {
        Set<ListNode> list = new HashSet<>();
        do
        {
            if (!list.add(head)) {
                return true;
            }
            list.add(head);
            if (head!=null) {
                head = head.next;
            }
        } while (head!=null);
        return false;
    }
}
