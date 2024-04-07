package al.binarytree;

import java.util.Stack;

public class SuccessorNodeJ {

    /**
     * leetcode-面试题 04.06. 后继者
     * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
     *
     * 如果指定节点没有对应的“下一个”节点，则返回null。
     *
     * 示例 1:
     *
     * 输入: root = [2,1,3], p = 1
     *
     *   2
     *  / \
     * 1   3
     *
     * 输出: 2
     * 示例 2:
     *
     * 输入: root = [5,3,6,2,4,null,null,1], p = 6
     *
     *       5
     *      / \
     *     3   6
     *    / \
     *   2   4
     *  /
     * 1
     *
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        boolean hasFound = false;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                if (hasFound) {
                    return cur;
                } else if (cur == p) {
                    hasFound = true;
                }
                cur = cur.right;
            }
        }
        return null;
    }
}
