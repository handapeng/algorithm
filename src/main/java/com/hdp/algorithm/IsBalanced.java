package com.hdp.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HDP
 * @ClassName: IsBalanced
 * @Description:
 * 二叉树的每个节点的左右子树的高度差的绝对值不超过 1，
 * 则二叉树是平衡二叉树。根据定义，一棵二叉树是平衡二叉树，当且仅当其所有子树也都是平衡二叉树，
 * 因此可以使用递归的方式判断二叉树是不是平衡二叉树，递归的顺序可以是自顶向下或者自底向上。

 * @date 2022/11/9 11:33
 */
public class IsBalanced {
    public boolean isBalanced(InorderTraversal.TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return Math.abs(height(root.left) - height(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
        }
    }

    public Integer height(InorderTraversal.TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.left), height(root.right)) + 1;
        }
    }
}
