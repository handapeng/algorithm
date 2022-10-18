package com.hdp.algorithm;

import java.util.Objects;

/**
 * @author HDP
 * @ClassName: DeleteDuplicates
 * @Description:
 * @date 2022/10/18 14:47
 */
public class DeleteDuplicates {

    public static MergeTwoLists.ListNode deleteDuplicates(MergeTwoLists.ListNode head) {
        if (Objects.isNull(head) || Objects.isNull(head.next)) {
            return null;
        }
        if (Objects.equals(head.val, head.next.val)) {
            head.next = head.next.next;
            deleteDuplicates(head);
        }else {
            deleteDuplicates(head.next);
        }
        return head;
    }

    public static void main(String[] args) {
        MergeTwoLists.ListNode list5 = new MergeTwoLists.ListNode(3, null);
        MergeTwoLists.ListNode list4 = new MergeTwoLists.ListNode(3, list5);
        MergeTwoLists.ListNode list3 = new MergeTwoLists.ListNode(2, list4);
        MergeTwoLists.ListNode list2 = new MergeTwoLists.ListNode(1, list3);
        MergeTwoLists.ListNode list1 = new MergeTwoLists.ListNode(1, list2);
        MergeTwoLists.ListNode listNode = deleteDuplicates(list1);
        System.out.println(listNode);
    }

}
