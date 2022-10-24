package com.hdp.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author HDP
 * @ClassName: DetectCycle
 * @Description:环形链表二
 * @date 2022/10/19 10:07
 */
public class DetectCycle {
    public HasCycle.ListNode detectCycle(HasCycle.ListNode head) {
        List<HasCycle.ListNode> list = new ArrayList<>();
        do
        {
            if (list.contains(head)) {
                return head;
            }
            list.add(head);
            if (Objects.nonNull(head)) {
                head = head.next;
            }
        } while (Objects.nonNull(head));
        return null;
    }
}
