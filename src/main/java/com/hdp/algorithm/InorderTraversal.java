package com.hdp.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HDP
 * @ClassName: InorderTraversal
 * @Description:二叉树的中序遍历 按照访问左子树——根节点——右子树的方式遍历这棵树，而在访问左子树或者右子树的时候我们按照同样的方式遍历，直到遍历完整棵树。
 * @date 2022/10/31 13:54
 */
public class InorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> objects = new ArrayList<>();
        if (root == null) {
            return objects;
        }
        objects.addAll(inorderTraversal(root.left));
        objects.add(root.val);
        objects.addAll(inorderTraversal(root.right));
        return objects;
    }
}
