package com.hdp.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author HDP
 * @ClassName: HasCycle
 * @Description:
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
        List<ListNode> list = new ArrayList<>();
        do
        {
            if (list.contains(head)) {
                return true;
            }
            list.add(head);
            if (Objects.nonNull(head)) {
                head = head.next;
            }
        } while (Objects.nonNull(head));
        return false;
    }
}
