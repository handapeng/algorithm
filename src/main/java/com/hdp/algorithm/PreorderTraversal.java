package com.hdp.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HDP
 * @ClassName: PreorderTraversal
 * @Description:二叉树的前序遍历 按照访问根节点——左子树——右子树的方式遍历这棵树，而在访问左子树或者右子树的时候，我们按照同样的方式遍历，直到遍历完整棵树。
 * @date 2022/11/1 14:53
 */
public class PreorderTraversal {
    public List<Integer> preorderTraversal(InorderTraversal.TreeNode root) {
        List<Integer> objects = new ArrayList<>();
        if (root == null) {
            return objects;
        }
        objects.add(root.val);
        objects.addAll(preorderTraversal(root.left));
        objects.addAll(preorderTraversal(root.right));
        return objects;
    }
}
