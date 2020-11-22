//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组 
// 👍 765 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder, int preStart, int preEnd,
                              int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int rootValue = preorder[preStart];
        int rootAtInorderIndex = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootValue) {
                rootAtInorderIndex = i;
            }
        }
        int nextLeftLength = rootAtInorderIndex - inStart;
        int nextRightLength = inEnd - rootAtInorderIndex;
        TreeNode root = new TreeNode(preorder[preStart]);
        root.left = buildTree(preorder, inorder, preStart + 1, preStart + rootAtInorderIndex - inStart, inStart, rootAtInorderIndex - 1);
        root.right = buildTree(preorder, inorder, preStart + rootAtInorderIndex - inStart + 1, preEnd, rootAtInorderIndex + 1, inEnd);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
