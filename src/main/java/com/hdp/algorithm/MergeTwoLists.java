package com.hdp.algorithm;

import java.util.Objects;

/**
 * @author HDP
 * @ClassName: MergeTwoLists
 * @Description:
 * @date 2022/10/17 16:10
 */
public class MergeTwoLists {
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (Objects.isNull(list1) && Objects.nonNull(list2)) {
            list1 = new ListNode(list2.val, list2.next);
            return list1;
        }
        if (Objects.nonNull(list1) && Objects.isNull(list2)) {
            return list1;
        }
        if (Objects.isNull(list1)) {
            return null;
        }

        if (list1.val > list2.val) {
            ListNode next = new ListNode(list1.val, list1.next);
            list1.val = list2.val;
            list1.next = next;
            list2 = list2.next;
        }
        if (Objects.nonNull(list1.next)) {
            mergeTwoLists(list1.next, list2);
        } else {
            list1.next = new ListNode(list2.val, list2.next);
        }
        return list1;
    }

    public static void main(String[] args) {
        ListNode list3 = new ListNode(4, null);
        ListNode list2 = new ListNode(2, list3);
        ListNode list1 = new ListNode(1, list2);
        ListNode list31 = new ListNode(4, null);
        ListNode list21 = new ListNode(3, list31);
        ListNode list11 = new ListNode(1, list21);
        ListNode listNode = mergeTwoLists(list1, list11);
        System.out.println(listNode);
    }

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
}
