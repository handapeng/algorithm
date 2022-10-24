package com.hdp.algorithm;

import java.util.Objects;

/**
 * @author HDP
 * @ClassName: GetIntersectionNode
 * @Description:相交链表
 * @date 2022/10/24 14:11
 */
public class GetIntersectionNode {
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pointA = headA;
        for (; ; ) {
            ListNode pointB = headB;
            for (; ; ) {
                if (pointA == pointB) {
                    return pointA;
                }
                if (Objects.nonNull(pointB)) {
                    pointB = pointB.next;
                }else {
                    break;
                }
            }
            if (Objects.nonNull(pointA.next)) {
                pointA = pointA.next;
            }else {
                break;
            }
        }
        return null;
    }
}
