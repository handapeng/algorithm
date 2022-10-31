package com.hdp.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HDP
 * @ClassName: GetKthFromEnd
 * @Description:链表中倒数第k个节点
 * @date 2022/10/28 17:59
 */
public class GetKthFromEnd {
    public static ReverseList.ListNode getKthFromEnd(ReverseList.ListNode head, int k) {
        if (head == null) {
            return head;
        }
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        ReverseList.ListNode next = new ReverseList.ListNode();
        head = new ReverseList.ListNode(list.get(list.size() - k), next);
        if(k==1){
            head.next=null;
        }
        for (int i = list.size() - k + 1; i < list.size(); i++) {
            next.val = list.get(i);
            if (i != list.size()-1) {
                next.next = new ReverseList.ListNode();
                next = next.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ReverseList.ListNode list3 = new ReverseList.ListNode(3, null);
        ReverseList.ListNode list2 = new ReverseList.ListNode(2, list3);
        ReverseList.ListNode list1 = new ReverseList.ListNode(1, list2);
        ReverseList.ListNode listNode = getKthFromEnd(list1, 2);
        System.out.println(listNode);
    }
}
