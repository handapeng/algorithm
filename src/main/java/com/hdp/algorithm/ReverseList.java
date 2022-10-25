package com.hdp.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author HDP
 * @ClassName: ReverseList
 * @Description:反转链表
 * @date 2022/10/25 11:48
 */
public class ReverseList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        while (head.next != null) {
            list.add(head.val);
            head = head.next;
        }
        ListNode next = new ListNode();
        if (list.size() > 0) {
            head.next = next;
            for (int i = list.size() - 1; i >= 0; i--) {
                next.val = list.get(i);
                if (i != 0) {
                    next.next = new ListNode();
                    next = next.next;
                }
            }
        }
        return head;
    }
    public static void main(String[] args) {
        ListNode list3 = new ListNode(3, null);
        ListNode list2 = new ListNode(2, list3);
        ListNode list1 = new ListNode(1, list2);
        ListNode listNode = reverseList(list1);
        System.out.println(listNode);
    }
}
