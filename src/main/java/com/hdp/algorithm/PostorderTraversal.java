package com.hdp.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HDP
 * @ClassName: PostorderTraversal
 * @Description:二叉树的后续遍历 按照访问左子树——右子树——根节点的方式遍历这棵树，而在访问左子树或者右子树的时候，我们按照同样的方式遍历，直到遍历完整棵树。
 * @date 2022/11/3 15:54
 */
public class PostorderTraversal {
    public List<Integer> postorderTraversal(InorderTraversal.TreeNode root) {
        List<Integer> objects = new ArrayList<>();
        if (root == null) {
            return objects;
        }
        objects.addAll(postorderTraversal(root.left));
        objects.addAll(postorderTraversal(root.right));
        objects.add(root.val);
        return objects;
    }
}
